package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.shop.ShopDto;
import com.nastya.bookShop.service.api.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    private final RestTemplate restTemplate;

    @Autowired
    public ShopServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ShopDto> getShopByBook(int bookId) {
        return restTemplate.getForObject(UrlConst.ShopUrl + "/book/" + bookId, List.class);
    }

    @Override
    public List<ShopDto> getShopByUser() {
        return restTemplate.getForObject(UrlConst.ShopUrl + "/username/" +
                SecurityContextHolder.getContext().getAuthentication().getName(), List.class);

    }

    @Override
    public ShopDto getOne(Integer id) {
        return restTemplate.getForObject(UrlConst.ShopUrl + "/" + id, ShopDto.class);
    }

    @Override
    public ResponseEntity<ShopDto> createShop(ShopDto shopDto) {
        return restTemplate.postForEntity(UrlConst.ShopUrl + "/create/" +
                SecurityContextHolder.getContext().getAuthentication().getName(), shopDto, ShopDto.class);
    }

}
