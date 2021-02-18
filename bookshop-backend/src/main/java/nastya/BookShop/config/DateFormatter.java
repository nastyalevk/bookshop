package nastya.BookShop.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    static ThreadLocal<SimpleDateFormat> format1 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    static ThreadLocal<SimpleDateFormat> format2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
    };
    static ThreadLocal<SimpleDateFormat> format3 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };


    public String formatDate(Date date) {
        return format2.get().format(date);
    }

    public Date formatDate(String date) throws ParseException {
        return format1.get().parse(date);
    }

    public Date formatString(String date) throws ParseException {
        return format3.get().parse(date);
    }

    public String formatString(Date date) throws ParseException {
        return format3.get().format(date);
    }
}
