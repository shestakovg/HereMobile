package com.sc4.here;


import android.Manifest;
import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    private MapFragmentView m_mapFragmentView;

    private LinearLayout mapContainerLayout;

    private static CalendarFragment _instance;
    public static  CalendarFragment GetInstance()
    {
        if (_instance == null) _instance = new CalendarFragment();
        return _instance;
    }
    public CalendarFragment() {
        // Required empty public constructor
        //requestPermissions();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        mapContainerLayout = (LinearLayout) view.findViewById(R.id.mapContainerLayout);

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width=width;
        params.height= ((Double) ( height / 3.0)).intValue();
        mapContainerLayout.setLayoutParams(params);
        m_mapFragmentView = new MapFragmentView(getActivity(), this);

        return view;
    }

}
