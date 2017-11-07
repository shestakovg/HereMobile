package Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sc4.here.R;

import java.text.ParseException;
import java.util.ArrayList;

import Common.Util;
import Model.CalendarRow;
import db.DatabaseHelper;

/**
 * Created by gshestakov on 11/7/2017.
 */

public class CalendarListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater lInflater;
    private ArrayList<CalendarRow> calendarRows = new ArrayList<>();
    private boolean todayList = true;
    private DatabaseHelper dbHelper;

    public CalendarListAdapter(Context context, boolean todayList) throws ParseException {
        this.context = context;
        this.todayList = todayList;
        this.dbHelper = new DatabaseHelper(context);
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.fillCalendarList();
    }

    private void fillCalendarList() throws ParseException {
        String query = "select id, Title ,StartDate  ,EndDate   ,TextDate   ,Duration   ,Address   ,GuestCount   ," +
                "Status   ,PickupPoint   ,Destination from Gathering ";
        if (todayList) {
            query+= " where StartDate ='2017-01-01 00:00:00.000'";
        }
        else {

        }
        SQLiteDatabase db = dbHelper.open();
        Cursor cursor =      db.rawQuery( query , null);
        cursor.moveToFirst();
        for (int i=0; i < cursor.getCount(); i++ ) {
            CalendarRow row = new CalendarRow();
            row.setId(cursor.getInt(cursor.getColumnIndex("id")));
            row.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
            row.setStartDate(Util.getCalendarFromString(cursor.getString(cursor.getColumnIndex("StartDate"))));
            row.setEndDate(Util.getCalendarFromString(cursor.getString(cursor.getColumnIndex("EndDate"))));
            row.setDuration(cursor.getString(cursor.getColumnIndex("Duration")));
            row.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
            row.setGuestCount(cursor.getInt(cursor.getColumnIndex("GuestCount")));
            row.setStatus(cursor.getString(cursor.getColumnIndex("Status")));
            row.setDestination(cursor.getString(cursor.getColumnIndex("Destination")));
            row.setPickupPoint(cursor.getString(cursor.getColumnIndex("PickupPoint")));
            this.calendarRows.add(row);
            cursor.moveToNext();
        }

        db.close();

    }


    @Override
    public int getCount() {
        return calendarRows.size();
    }

    @Override
    public Object getItem(int i) {
        return this.calendarRows.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = lInflater.inflate(R.layout.calendar_row,viewGroup, false);
        }
        CalendarRow calendarRow = (CalendarRow) getItem(i);
        ((TextView) view.findViewById(R.id.calendarRowTvDate)).setText(Util.getDateForCalendarPage(calendarRow.getStartDate()));
        ((TextView) view.findViewById(R.id.calendarRowTvTitle)).setText(calendarRow.getTitle());
        ((TextView) view.findViewById(R.id.calendarRowTvPickupPoint)).setText(calendarRow.getPickupPoint());
        ((TextView) view.findViewById(R.id.calendarRowTvDestination)).setText(calendarRow.getDestination());
        return view;
    }


}
