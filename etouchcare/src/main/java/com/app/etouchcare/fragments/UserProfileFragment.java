/**
 * TeamOne
 */
package com.app.etouchcare.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import com.app.etouchcare.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    private RatingBar progressBar;
    private TextInputLayout pwd_cur,pwd_new,pwd_new2;
    private Button submit;
    public UserProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        progressBar = (RatingBar) view.findViewById(R.id.user_ratingBar);
        pwd_cur = (TextInputLayout) view.findViewById(R.id.user_cur_pwd);
        pwd_new = (TextInputLayout) view.findViewById(R.id.user_new_pwd);
        pwd_new2 = (TextInputLayout) view.findViewById(R.id.user_new_pwd2);
        submit = (Button) view.findViewById(R.id.user_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainContentFragment mainContent = new MainContentFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main, mainContent, mainContent.getTag()).commit();
            }
        });

        progressBar.setRating(3.5f);

        return view;
    }

}
