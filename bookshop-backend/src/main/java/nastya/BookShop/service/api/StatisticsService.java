package nastya.BookShop.service.api;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface StatisticsService {

    Long allProceedByShop(Integer shopId, String beginning, String ending) throws ParseException;

    Map getTopShops();

    List getOrdersStats(Integer shopId);
}
