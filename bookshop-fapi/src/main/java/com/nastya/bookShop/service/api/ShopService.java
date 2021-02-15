package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.shop.ShopDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShopService {
    List<ShopDto> getShopByBook(int bookId);

    List<ShopDto> getShopByUser();

    ShopDto getOne(Integer id);

    ResponseEntity<ShopDto> createShop(ShopDto shopDto);
}
