/**
 * TeamOne
 */
package com.app.etouchcare.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;


import com.android.volley.RequestQueue;
import com.app.etouchcare.activity.MainPatientListActivity;
import com.app.etouchcare.R;
import com.app.etouchcare.callbacks.PatientListLoadedListener;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.extra.PatientUtils;
import com.app.etouchcare.network.VolleySingleton;
//import com.app.etouchcare.tasks.TaskLoadPatientList;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import static com.app.etouchcare.R.id.fingerprint_button;


public class LoginActivity extends AppCompatActivity {

	public final static String USER_ID = "com.app.etouchcare.ID";

	private static final String[] DUMMY_CREDENTIALS = new String[]{
			"foo@example.com:hello", "bar@example.com:world"
	};
	private UserLoginTask mAuthTask = null;
	//Email pattern validator
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
	private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	private Matcher matcher;
	private boolean Dumb;

	//UI elements
	private TextInputLayout mEmailView;
	private TextInputLayout mPasswordView;
	private View mProgressView;
	private View mLoginFormView;

	//Fingerprint Log In
	private static final String TAG = LoginActivity.class.getSimpleName();

	private static final String DIALOG_FRAGMENT_TAG = "myFragment";
	private static final String SECRET_MESSAGE = "Very secret message";
	private static final String KEY_NAME_NOT_INVALIDATED = "key_not_invalidated";
	static final String DEFAULT_KEY_NAME = "default_key";

	private KeyStore mKeyStore;
	private KeyGenerator mKeyGenerator;
	private SharedPreferences mSharedPreferences;

	@Override
	@TargetApi(23)
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);


		mProgressView = findViewById(R.id.login_progress);
		mLoginFormView = findViewById(R.id.login_form);

		mEmailView = (TextInputLayout) findViewById(R.id.usernameWrapper);
		mPasswordView = (TextInputLayout) findViewById(R.id.passwordWrapper);

		Button submitBtn = (Button) findViewById(R.id.email_sign_in_button);
		submitBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				hideKeyboard();
				attemptLogin();

			}
		});

		Button submitBtnDumb = (Button) findViewById(R.id.email_sign_in_button_dumb);
		submitBtnDumb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dumb = true;
				attemptLogin();
			}
		});

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

			//Fingerprint Log In
			try {
				mKeyStore = KeyStore.getInstance("AndroidKeyStore");
			} catch (KeyStoreException e) {
				throw new RuntimeException("Failed to get an instance of KeyStore", e);
			}
			try {
				mKeyGenerator = KeyGenerator
						.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
			} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
				throw new RuntimeException("Failed to get an instance of KeyGenerator", e);
			}
			Cipher defaultCipher;
			Cipher cipherNotInvalidated;
			try {
				defaultCipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
						+ KeyProperties.BLOCK_MODE_CBC + "/"
						+ KeyProperties.ENCRYPTION_PADDING_PKCS7);
				cipherNotInvalidated = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
						+ KeyProperties.BLOCK_MODE_CBC + "/"
						+ KeyProperties.ENCRYPTION_PADDING_PKCS7);
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				throw new RuntimeException("Failed to get an instance of Cipher", e);
			}
			mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

			KeyguardManager keyguardManager = getSystemService(KeyguardManager.class);
			FingerprintManager fingerprintManager = getSystemService(FingerprintManager.class);
			Button fingerprintButton = (Button) findViewById(fingerprint_button);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

				fingerprintButton.setVisibility(View.VISIBLE);
			}else {
				fingerprintButton.setVisibility(View.GONE);
			}


			Button fingerprintButtonNotInvalidated = (Button) findViewById(
					R.id.fingerprint_button_not_invalidated);

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				fingerprintButtonNotInvalidated.setEnabled(true);
				fingerprintButtonNotInvalidated.setOnClickListener(
						new FingerprintButtonClickListener(cipherNotInvalidated,
								KEY_NAME_NOT_INVALIDATED));
			} else {
				// Hide the purchase button which uses a non-invalidated key
				// if the app doesn't work on Android N preview
				fingerprintButtonNotInvalidated.setVisibility(View.GONE);
				findViewById(R.id.fingerprint_button_not_invalidated_description)
						.setVisibility(View.GONE);
			}

			if (!keyguardManager.isKeyguardSecure()) {
				// Show a message that the user hasn't set up a fingerprint or lock screen.
				Toast.makeText(this,
						"Secure lock screen hasn't set up.\n"
								+ "Go to 'Settings -> Security -> Fingerprint' to set up a fingerprint",
						Toast.LENGTH_LONG).show();
				fingerprintButton.setEnabled(false);
				fingerprintButtonNotInvalidated.setEnabled(false);
				return;
			}

			// Now the protection level of USE_FINGERPRINT permission is normal instead of dangerous.
			// See http://developer.android.com/reference/android/Manifest.permission.html#USE_FINGERPRINT
			// The line below prevents the false positive inspection from Android Studio
			// noinspection ResourceType
			if (!fingerprintManager.hasEnrolledFingerprints()) {
				fingerprintButton.setEnabled(false);
				// This happens when no fingerprints are registered.
				Toast.makeText(this,
						"Go to 'Settings -> Security -> Fingerprint' and register at least one fingerprint",
						Toast.LENGTH_LONG).show();
				return;
			}
			createKey(DEFAULT_KEY_NAME, true);
			createKey(KEY_NAME_NOT_INVALIDATED, false);
			fingerprintButton.setEnabled(true);
			fingerprintButton.setOnClickListener(
					new FingerprintButtonClickListener(defaultCipher, DEFAULT_KEY_NAME));
		}
	}

	//Manual Log in
	private void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);


		// Store values at the time of the login attempt.
		String email = mEmailView.getEditText().getText().toString();
		String password = mPasswordView.getEditText().getText().toString();

		if (Dumb)
		{
			email = "dumb@dumb.com";
			password ="xpto";
		}

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password, if the user entered one.
		if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(email)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} else if (!isEmailValid(email)) {
			mEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			showProgress(true);
			mAuthTask = new UserLoginTask(email, password);
			mAuthTask.execute();
		}
	}

	public void doLogin() {
		//TODO LOGIN INTENT

//        Toast.makeText(getApplicationContext(), "OK! I'm performing login.", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, MainPatientListActivity.class);
//        intent.putExtra("PatientPosition", USER_ID);
//        intent.putParcelableArrayListExtra("patients",list);
//        Toast.makeText(this,"In doLogin: "+list.toString(),Toast.LENGTH_SHORT).show();
		startActivity(intent);


	}

	public boolean isEmailValid(String email) {
		matcher = pattern.matcher(email);
		//return matcher.matches();
		return true;
	}

	public boolean isPasswordValid(String password) {
		//return password.length() >= patienticon;
		return true;
	}

	private void hideKeyboard() {
		View view = getCurrentFocus();
		if (view != null) {
			((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
					hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime).alpha(
					show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
				}
			});

			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mProgressView.animate().setDuration(shortAnimTime).alpha(
					show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
				}
			});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}




	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

		private final String mEmail;
		private final String mPassword;
		private ArrayList<Patients> patientList;
		UserLoginTask(String email, String password) {
			mEmail = email;
			mPassword = password;
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			try {
				// Simulate network access.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return false;
			}

			for (String credential : DUMMY_CREDENTIALS) {
				String[] pieces = credential.split(":");
				if (pieces[0].equals(mEmail)) {
					// Account exists, return true if the password matches.


					return true;
				}
			}

			//  register the new account here if needed.
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {

				doLogin();
				// finish();
			} else {
				mPasswordView.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}

	//Fingerprint Log In

	/**
	 * Initialize the {@link Cipher} instance with the created key in the
	 * {@link #createKey(String, boolean)} method.
	 *
	 * @param keyName the key name to init the cipher
	 * @return {@code true} if initialization is successful, {@code false} if the lock screen has
	 * been disabled or reset after the key was generated, or if a fingerprint got enrolled after
	 * the key was generated.
	 */
	@TargetApi(23)
	private boolean initCipher (Cipher cipher, String keyName){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			try {
				mKeyStore.load(null);
				SecretKey key = (SecretKey) mKeyStore.getKey(keyName, null);
				cipher.init(Cipher.ENCRYPT_MODE, key);
				return true;
			} catch (KeyPermanentlyInvalidatedException e) {
				return false;
			} catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException
					| NoSuchAlgorithmException | InvalidKeyException e) {
				throw new RuntimeException("Failed to init Cipher", e);
			}
		}
		return false;
	}

	/**
	 * Proceed the purchase operation
	 *
	 * @param withFingerprint {@code true} if the purchase was made by using a fingerprint
	 * @param cryptoObject the Crypto object
	 */
	@TargetApi(23)
	public void onFingerprint ( boolean withFingerprint,
								@Nullable FingerprintManager.CryptoObject cryptoObject){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (withFingerprint) {
				// If the user has authenticated with fingerprint, verify that using cryptography and
				// then show the confirmation message.
				assert cryptoObject != null;
				tryEncrypt(cryptoObject.getCipher());
			} else {
				// Authentication happened with backup password. Just show the confirmation message.
				showConfirmation(null);
			}
		}
	}




	// Show confirmation, if fingerprint was used show crypto information.

	private void showConfirmation(byte[] encrypted) {
		findViewById(R.id.confirmation_message).setVisibility(View.GONE);
		if (encrypted != null) {
			TextView v = (TextView) findViewById(R.id.encrypted_message);
			v.setVisibility(View.GONE);
			v.setText(Base64.encodeToString(encrypted, 0 /* flags */));
			doLogin();
		}
	}

	/**
	 * Tries to encrypt some data with the generated key in {@link #createKey} which is
	 * only works if the user has just authenticated via fingerprint.
	 */
	private void tryEncrypt(Cipher cipher) {
		try {
			byte[] encrypted = cipher.doFinal(SECRET_MESSAGE.getBytes());
			showConfirmation(encrypted);
		} catch (BadPaddingException | IllegalBlockSizeException e) {
			Toast.makeText(this, "Failed to encrypt the data with the generated key. "
					+ "Retry the purchase", Toast.LENGTH_LONG).show();
			Log.e(TAG, "Failed to encrypt the data with the generated key." + e.getMessage());
		}
	}

	/**
	 * Creates a symmetric key in the Android Key Store which can only be used after the user has
	 * authenticated with fingerprint.
	 *
	 * @param keyName                          the name of the key to be created
	 * @param invalidatedByBiometricEnrollment if {@code false} is passed, the created key will not
	 *                                         be invalidated even if a new fingerprint is enrolled.
	 *                                         The default value is {@code true}, so passing
	 *                                         {@code true} doesn't change the behavior
	 *                                         (the key will be invalidated if a new fingerprint is
	 *                                         enrolled.). Note that this parameter is only valid if
	 *                                         the app works on Android N developer preview.
	 */
	@TargetApi(23)
	public void createKey(String keyName, boolean invalidatedByBiometricEnrollment) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			// The enrolling flow for fingerprint. This is where you ask the user to set up fingerprint
			// for your flow. Use of keys is necessary if you need to know if the set of
			// enrolled fingerprints has changed.
			try {
				mKeyStore.load(null);
				// Set the alias of the entry in Android KeyStore where the key will appear
				// and the constrains (purposes) in the constructor of the Builder

				KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(keyName,
						KeyProperties.PURPOSE_ENCRYPT |
								KeyProperties.PURPOSE_DECRYPT)
						.setBlockModes(KeyProperties.BLOCK_MODE_CBC)
						// Require the user to authenticate with a fingerprint to authorize every use
						// of the key
						.setUserAuthenticationRequired(true)
						.setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);

				// This is a workaround to avoid crashes on devices whose API level is < 24
				// because KeyGenParameterSpec.Builder#setInvalidatedByBiometricEnrollment is only
				// visible on API level +24.
				// Ideally there should be a compat library for KeyGenParameterSpec.Builder but
				// which isn't available yet.
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
					builder.setInvalidatedByBiometricEnrollment(invalidatedByBiometricEnrollment);
				}
				mKeyGenerator.init(builder.build());
				mKeyGenerator.generateKey();
			} catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException
					| CertificateException | IOException e) {
				throw new RuntimeException(e);
			}
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class FingerprintButtonClickListener implements View.OnClickListener {

		Cipher mCipher;
		String mKeyName;

		FingerprintButtonClickListener(Cipher cipher, String keyName) {
			mCipher = cipher;
			mKeyName = keyName;
		}

		@Override
		@TargetApi(23)
		public void onClick(View view) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				findViewById(R.id.confirmation_message).setVisibility(View.GONE);
				findViewById(R.id.encrypted_message).setVisibility(View.GONE);

				// Set up the crypto object for later. The object will be authenticated by use
				// of the fingerprint.
				if (initCipher(mCipher, mKeyName)) {

					// Show the fingerprint dialog. The user has the option to use the fingerprint with
					// crypto, or you can fall back to using a server-side verified password.
					FingerprintAuthenticationDialogFragment fragment
							= new FingerprintAuthenticationDialogFragment();
					fragment.setCryptoObject(new FingerprintManager.CryptoObject(mCipher));
					boolean useFingerprintPreference = mSharedPreferences
							.getBoolean(getString(R.string.use_fingerprint_to_authenticate_key),
									true);
					if (useFingerprintPreference) {
						fragment.setStage(
								FingerprintAuthenticationDialogFragment.Stage.FINGERPRINT);
					} else {
						fragment.setStage(
								FingerprintAuthenticationDialogFragment.Stage.PASSWORD);
					}
					fragment.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
				} else {
					// This happens if the lock screen has been disabled or or a fingerprint got
					// enrolled. Thus show the dialog to authenticate with their password first
					// and ask the user if they want to authenticate with fingerprints in the
					// future
					FingerprintAuthenticationDialogFragment fragment
							= new FingerprintAuthenticationDialogFragment();
					fragment.setCryptoObject(new FingerprintManager.CryptoObject(mCipher));
					fragment.setStage(
							FingerprintAuthenticationDialogFragment.Stage.NEW_FINGERPRINT_ENROLLED);
					fragment.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
				}
			}
		}
	}

}


