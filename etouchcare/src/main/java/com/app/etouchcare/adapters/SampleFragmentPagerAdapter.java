package com.app.etouchcare.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.etouchcare.PatientBasicFragment;
import com.app.etouchcare.PatientDiagnosisFragment;

/**
 * Created by wenzhongzheng on 2016-11-21.
 */

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Basic", "Diagnosis", "Tests", "Treatment" };
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
            return PatientBasicFragment.newInstance(position + 1);
        else
            return PatientDiagnosisFragment.newInstance("","");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
