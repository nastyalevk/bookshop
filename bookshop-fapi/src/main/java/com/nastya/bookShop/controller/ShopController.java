package com.nastya.bookShop.controller;

import com.nastya.bookShop.model.shop.ShopDto;
import com.nastya.bookShop.service.api.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    public final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<List<ShopDto>> getOne(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(shopService.getShopByBook(id), HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<List<ShopDto>> getShopByUser() {
        return new ResponseEntity<>(shopService.getShopByUser(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDto> getShop(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(shopService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity createShop(@RequestBody ShopDto ShopDto) {
        return new ResponseEntity<>(shopService.createShop(ShopDto), HttpStatus.OK);
    }
}
