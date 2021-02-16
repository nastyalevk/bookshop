package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.service.api.StatisticsService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final RestTemplate restTemplate;

    public StatisticsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<Long> allProceedByShop(Integer shopId, String beginning, String ending) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlConst.StatsUrl+"proceed/")
                .queryParam("shopId", shopId)
                .queryParam("beginning", beginning)
                .queryParam("ending", ending);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Long.class);
    }

    @Override
    public Map getTopShops() {
        return restTemplate.getForObject(UrlConst.StatsUrl+"shops/", Map.class);
    }

    @Override
    public List getOrdersStats(Integer shopId) {
        return restTemplate.getForObject(UrlConst.StatsUrl+"shop/order/"+shopId, List.class);
    }
}
