package Common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by gshestakov on 11/7/2017.
 */

public class Util {
    public static Calendar getCalendarFromString(String dateTimeString) throws ParseException {
        Calendar t = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //java.util.Date dt = sdf.parse(dateTimeString.substring(0,10)); //replace 4 with the column index
        java.util.Date dt = sdf.parse(dateTimeString);
        t.setTime(dt);
        return t;
    }

    public static String getDateForCalendarPage(Calendar calendar) {
        return Integer.toString(calendar.get(Calendar.DAY_OF_MONTH))+" "+ GetMonthName(calendar.get(Calendar.MONTH));
    }

    public static String getTimeForCalendarPage(Calendar calendar) {
        final SimpleDateFormat format = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
        return format.format(calendar.getTime());
        //return Integer.toString(calendar.get(Calendar.DAY_OF_MONTH))+" "+ GetMonthName(calendar.get(Calendar.MONTH));
    }

    public static String getPeriodForNotificationRow(Calendar startDate, Calendar endDate) {
        String result;
        int firstDate = startDate.get(Calendar.DAY_OF_MONTH);
        int secondDate = endDate.get(Calendar.DAY_OF_MONTH);
        if (firstDate == secondDate) {
            result = Integer.toString(firstDate);
        }
        else {
            result = Integer.toString(firstDate)+"-"+Integer.toString(secondDate);
        }
//        if (result.length()<5) {
//            for (int i = 0; i<5-result.length(); i++)  result+=" ";
//        }
        return result;
    }

    public static String getMonthName(Calendar calendar) {
        return GetMonthName(calendar.get(Calendar.MONTH));
    }
    private static String GetMonthName(int month) {
        switch (month+1 ) {
            case 1: return "Jan";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Apr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Aug";
            case 9: return "Sep";
            case 10: return "Oct";
            case 11: return "Nov";
            case 12: return "Dec";
        }
        return "";
    }
}
