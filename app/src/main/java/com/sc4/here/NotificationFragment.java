package com.sc4.here;


import android.os.Bundle;
import android.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.ParseException;

import Adapters.CalendarListAdapter;
import Adapters.NotificationListAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    private MapFragmentView m_mapFragmentView;
    private static NotificationFragment _instance;
    private LinearLayout mapContainerLayout;


    public static  NotificationFragment GetInstance()
    {
        if (_instance == null) _instance = new NotificationFragment();
        return _instance;
    }
    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        /*********************************************************************************************/
        //Filling lists
        ListView todayList = view.findViewById(R.id.lvNotificationNew);
        ListView nextWeekList = view.findViewById(R.id.lvNotificationEarlier);
        try {
            NotificationListAdapter notificationListAdapterNew =  new NotificationListAdapter(getActivity(), true);
            todayList.setAdapter(notificationListAdapterNew);
            NotificationListAdapter notificationListAdapterEarlier =  new NotificationListAdapter(getActivity(), false);
            nextWeekList.setAdapter(notificationListAdapterEarlier);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /*********************************************************************************************/
        mapContainerLayout = view.findViewById(R.id.mapContainerLayout);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        int availableHeight = height;
        //ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroup.LayoutParams paramsContainer = mapContainerLayout.getLayoutParams();
        paramsContainer.width=width;
        paramsContainer.height= ((Double) ( height / 3.0)).intValue();
        availableHeight -= paramsContainer.height;
        mapContainerLayout.setLayoutParams(paramsContainer);
        m_mapFragmentView = new MapFragmentView(getActivity(), this);

        mapContainerLayout =  view.findViewById(R.id.mapContainerToday);
        paramsContainer = mapContainerLayout.getLayoutParams();
        paramsContainer.width=width;
        paramsContainer.height=  ((Double) (  height /(todayList.getCount()<= 1 ? 3.5 : 2.0))).intValue();
        availableHeight -= paramsContainer.height;
        mapContainerLayout.setLayoutParams(paramsContainer);

        mapContainerLayout =  view.findViewById(R.id.mapContainerNextWeek);
        paramsContainer = mapContainerLayout.getLayoutParams();
        paramsContainer.width=width;
        paramsContainer.height= availableHeight - 100; //((Double) ( height / 3.0)).intValue();
        mapContainerLayout.setLayoutParams(paramsContainer);
       // ((ImageView)view.findViewById(R.id.calendarRowTvDate)).setImageResource(R.drawable.);
        return view;
    }

}
