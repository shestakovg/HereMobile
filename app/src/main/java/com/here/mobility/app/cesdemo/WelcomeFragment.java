package com.here.mobility.app.cesdemo;


import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.Interface.IMainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment implements View.OnClickListener {
    public IMainActivity mainActivity;
    private static  WelcomeFragment _instance;
    public static  WelcomeFragment GetInstance(IMainActivity mainActivity)
    {
        if (_instance == null) {
            _instance = new WelcomeFragment();
            _instance.mainActivity =     mainActivity;
        }
        return _instance;
    }


    public WelcomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        RelativeLayout mapContainerLayout = view.findViewById(R.id.welcome_container);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        //ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroup.LayoutParams paramsContainer = mapContainerLayout.getLayoutParams();
        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics());
        paramsContainer.width=width;
        mapContainerLayout.setX(0);
        paramsContainer.height= height+200;

        mapContainerLayout.setLayoutParams(paramsContainer);
        mapContainerLayout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (this.mainActivity != null) {
            this.mainActivity.showFragment();
        }
    }

//    private void OnClick(View v)
//    {
//        if (this.mainActivity != null) {
//            this.mainActivity.showFragment();
//        }
//    }

}
