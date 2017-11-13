package com.here.mobility.app.cesdemo;


import android.os.Bundle;
import android.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.ParseException;

import Adapters.CalendarListAdapter;


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
        /*********************************************************************************************/
        //Filling lists
        ListView todayList = view.findViewById(com.here.mobility.app.cesdemo.R.id.lvCalendarToday);
        ListView nextWeekList = view.findViewById(com.here.mobility.app.cesdemo.R.id.lvCalendarNextWeek);
        try {
            CalendarListAdapter calendarListAdapter =  new CalendarListAdapter(getActivity(), true);
            todayList.setAdapter(calendarListAdapter);
            CalendarListAdapter calendarListNextWeekAdapter =  new CalendarListAdapter(getActivity(), false);
            nextWeekList.setAdapter(calendarListNextWeekAdapter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /*********************************************************************************************/
        mapContainerLayout = (LinearLayout) view.findViewById(com.here.mobility.app.cesdemo.R.id.mapContainerLayout);
        //TableRow mapContainerLayout =  view.findViewById(R.id.mapRowCalendar);
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

        mapContainerLayout =  view.findViewById(com.here.mobility.app.cesdemo.R.id.mapContainerToday);
        paramsContainer = mapContainerLayout.getLayoutParams();
        paramsContainer.width=width;
        paramsContainer.height= ((Double) (  height /(todayList.getCount()== 1 ? 4.5 : 3.0))).intValue();
        availableHeight -= paramsContainer.height;
        mapContainerLayout.setLayoutParams(paramsContainer);

        mapContainerLayout =  view.findViewById(com.here.mobility.app.cesdemo.R.id.mapContainerNextWeek);
        paramsContainer = mapContainerLayout.getLayoutParams();
        paramsContainer.width=width;
        paramsContainer.height= availableHeight - 100; //((Double) ( height / 3.0)).intValue();
        mapContainerLayout.setLayoutParams(paramsContainer);
//        btn = (Button) view.findViewById(R.id.testDbBtn);
//        btn.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {testDbClick(v);}
//                }
//        );

        return view;
    }

//    private void testDbClick(View v)
//    {
//        //Toast.makeText(getActivity(), "TETS", Toast.LENGTH_SHORT).show();
//        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
//        SQLiteDatabase db = databaseHelper.open();
//        //SQLiteDatabase db = getActivity().getBaseContext().openOrCreateDatabase("here.db", MODE_PRIVATE, null);
//        Cursor cursor =      db.rawQuery("select * from Gathering",null);
//        cursor.moveToFirst();
//        for (int i=0; i < cursor.getCount(); i++ ) {
//            String s = cursor.getString(1);
//            btn.setText(s);
//            cursor.moveToNext();
//        }
//
//        db.close();
//    }
}


