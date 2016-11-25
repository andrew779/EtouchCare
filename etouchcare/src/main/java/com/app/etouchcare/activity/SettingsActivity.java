package com.app.etouchcare.activity;

/**
 * Created by joseapablaza on 2016-11-22.
 */

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.app.etouchcare.R;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Display the fragment as the main content.
		getFragmentManager().beginTransaction().replace(android.R.id.content,
				new SettingsFragment()).commit();
	}

	/**
	 * Fragment for settings.
	 */
	public static class SettingsFragment extends PreferenceFragment {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences);
		}
	}
}