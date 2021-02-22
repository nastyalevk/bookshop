package nastya.BookShop.service.impl;

import nastya.BookShop.model.DateFormatter;
import nastya.BookShop.service.api.StatisticsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.util.List;

import static java.util.Collections.reverseOrder;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long allProceedByShop(Integer shopId, String beginning, String ending) throws ParseException {
        Long proceed = entityManager.createQuery(
                "SELECT SUM(o.cost)\n" +
                        "FROM Shop s , Order o\n" +
                        "WHERE s.id = o.shop.id AND o.orderSubmitDate >= ?2 " +
                        "AND o.orderSubmitDate <= ?3 " +
                        "AND s.id=?1 \n" +
                        "GROUP BY s.id", Long.class)
                .setParameter(1, shopId)
                .setParameter(2, new DateFormatter().formatString(beginning))
                .setParameter(3,  new DateFormatter().formatString(ending)).getSingleResult();
        return proceed;
    }

    @Override
    public List<Object[]> getTopShops() {
        List<Object[]> top = entityManager.createQuery(
                "SELECT s.id as shopId, s.shopName, " +
                        "FLOOR(COALESCE(SUM(rrs.rating)/COUNT(rrs.rating), -1)) as sumRateing\n" +
                        "FROM ShopReview rrs RIGHT JOIN Shop s ON rrs.shop.id = s.id \n" +
                        "GROUP BY s.id " +
                        "ORDER BY sumRateing DESC")
                .getResultList();
        return top;
    }

    @Override
    public List getOrdersStats(Integer shopId) {
        List<Object[]> stats = entityManager.createQuery(
                "SELECT o.classification.name , COUNT(o.id ) \n" +
                "FROM Order o \n" +
                "WHERE o.shop.id = ?1\n" +
                "GROUP BY o.classification.id  \n").setParameter(1, shopId)
                .getResultList();
        return stats;
    }


}
