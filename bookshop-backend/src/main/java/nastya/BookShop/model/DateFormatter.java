package nastya.BookShop.model;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateFormatter {

    static ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_MM_SS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    static ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_MM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
    };
    static ThreadLocal<SimpleDateFormat> YYYY_MM_DD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };


    public String formatDate(Date date) {
        return YYYY_MM_DD_HH_MM.get().format(date);
    }

    public Date formatDate(String date) throws ParseException {
        return YYYY_MM_DD_HH_MM.get().parse(date);
    }

    public Date formatString(String date) throws ParseException {
        return YYYY_MM_DD.get().parse(date);
    }

}
