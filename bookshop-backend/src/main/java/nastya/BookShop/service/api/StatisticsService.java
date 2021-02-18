package nastya.BookShop.service.api;

import java.text.ParseException;
import java.util.List;

public interface StatisticsService {

    Long allProceedByShop(Integer shopId, String beginning, String ending) throws ParseException;

    List<Object[]> getTopShops();

    List getOrdersStats(Integer shopId);
}
