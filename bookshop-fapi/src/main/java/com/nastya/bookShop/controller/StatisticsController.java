package com.nastya.bookShop.controller;

import com.nastya.bookShop.service.api.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stat")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/proceed/")
    public ResponseEntity<Long> getUser(@RequestParam Integer shopId,
                                        @RequestParam String beginning,
                                        @RequestParam String ending) {
        return new ResponseEntity(statisticsService.allProceedByShop(shopId, beginning, ending), HttpStatus.OK);
    }

    @GetMapping("/shops")
    public ResponseEntity<Map> getTopShops() {
        return new ResponseEntity<>(statisticsService.getTopShops(), HttpStatus.OK);
    }

    @GetMapping("/shop/order/{shopId}")
    public ResponseEntity<List> getOrdersStats(@PathVariable("shopId") Integer shopId) {
        return new ResponseEntity<>(statisticsService.getOrdersStats(shopId), HttpStatus.OK);
    }

}
