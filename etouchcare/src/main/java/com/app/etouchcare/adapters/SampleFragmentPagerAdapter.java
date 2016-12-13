/**
 * TeamOne
 */
package com.app.etouchcare.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.etouchcare.R;
import com.app.etouchcare.datamodel.Patients;
import com.app.etouchcare.fragments.MainContentFragment;
import com.app.etouchcare.fragments.PatientBasicFragment;
import com.app.etouchcare.fragments.PatientDiagnosisFragment;
import com.app.etouchcare.activity.PatientDetailActivity;
import com.app.etouchcare.fragments.PatientTestsFragment;
import com.app.etouchcare.fragments.PatientTreatmentFragment;
import com.app.etouchcare.fragments.UserProfileFragment;

/**
 * Created by wenzhongzheng on 2016-11-21.
 */

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Basic", "Diagnosis", "Tests", "Treatment" };
    private Context context;
    private String id;
    private Patients theOne;
    private PatientDetailActivity patientDetailActivity;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        patientDetailActivity = (PatientDetailActivity) context;
        this.id = patientDetailActivity.getPatientID();
        this.theOne = patientDetailActivity.getTheOne();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0) {
            return PatientBasicFragment.newInstance(id,theOne);
        }
        if (position == 1){
            return PatientDiagnosisFragment.newInstance(id,theOne);
        }
        if (position == 2){
            return PatientTestsFragment.newInstance(id,theOne);
        }
        if (position == 3){
            return PatientTreatmentFragment.newInstance(id,theOne);
        }

        return null;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
