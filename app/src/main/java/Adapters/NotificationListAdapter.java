package Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.here.mobility.app.cesdemo.R;

import java.text.ParseException;
import java.util.ArrayList;

import Common.Util;
import Model.CalendarRow;
import db.DatabaseHelper;

/**
 * Created by gshestakov on 11/8/2017.
 */

public class NotificationListAdapter  extends BaseAdapter {
    private Context context;
    private LayoutInflater lInflater;
    private ArrayList<CalendarRow> calendarRows = new ArrayList<>();
    private boolean newNotification = true;
    private DatabaseHelper dbHelper;

    public NotificationListAdapter(Context context, boolean newNotification) throws ParseException {
        this.context = context;
        this.newNotification = newNotification;
        this.dbHelper = new DatabaseHelper(context);
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.fillList();
    }

    private void fillList() throws ParseException {
        String query = "select id, Title ,StartDate  ,EndDate   ,Duration   ,Address   ,GuestCount   ," +
                "Status   ,PickupPoint   ,Destination, StartingTime from Gathering ";
        if (newNotification) {
            query+= "\nwhere Status='new'";
        }
        else {
            query+= "\n where Status<>'new'";
        }
        //query+= "\norder by strftime('yyyy-MM-dd HH:mm:ss.SSS', StartDate)";
        // query+= "\norder by strftime('yyyy-MM-dd',substr( StartDate,0,10)) desc";
        query+="\norder by strftime('%s', StartDate )";
        SQLiteDatabase db = dbHelper.open();
        Cursor cursor =      db.rawQuery( query , null);
        cursor.moveToFirst();
        for (int i=0; i < cursor.getCount(); i++ ) {
            CalendarRow row = new CalendarRow();
            row.setId(cursor.getInt(cursor.getColumnIndex("id")));
            row.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
            row.setStartDate(Util.getCalendarFromString(cursor.getString(cursor.getColumnIndex("StartDate"))));
            row.setEndDate(Util.getCalendarFromString(cursor.getString(cursor.getColumnIndex("EndDate"))));
            row.setStartingTime(Util.getCalendarFromString(cursor.getString(cursor.getColumnIndex("StartingTime"))));
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
            view = lInflater.inflate(R.layout.notification_row,viewGroup, false);
        }
        CalendarRow calendarRow = (CalendarRow) getItem(i);
        ((TextView) view.findViewById(R.id.calendarRowTvDate)).setText(Util.getPeriodForNotificationRow(calendarRow.getStartDate(), calendarRow.getEndDate()));
        ((TextView) view.findViewById(R.id.calendarRowTvTime)).setText(Util.getMonthName(calendarRow.getStartDate()));
        ((TextView) view.findViewById(R.id.calendarRowTvTitle)).setText(calendarRow.getTitle());
        ((TextView) view.findViewById(R.id.calendarRowTvDestinationPoint)).setText(calendarRow.getAddress());

        return view;
    }
}
