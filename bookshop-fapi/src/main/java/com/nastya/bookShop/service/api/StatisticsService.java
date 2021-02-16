package com.nastya.bookShop.service.api;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface StatisticsService {

    ResponseEntity<Long> allProceedByShop(Integer shopId, String beginning, String ending);

    Map getTopShops();

    List getOrdersStats(Integer shopId);

}
