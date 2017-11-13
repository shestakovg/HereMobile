package com.here.mobility.app.cesdemo;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private static ProfileFragment _instance;
    public static  ProfileFragment GetInstance()
    {
        if (_instance == null) _instance = new ProfileFragment();
        return _instance;
    }
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(com.here.mobility.app.cesdemo.R.layout.fragment_profile, container, false);
    }

}
