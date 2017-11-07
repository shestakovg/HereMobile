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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dt = sdf.parse(dateTimeString.substring(0,10)); //replace 4 with the column index
        t.setTime(dt);
        return t;
    }
}
