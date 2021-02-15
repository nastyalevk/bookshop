package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.Assortment.AssortmentDto;
import com.nastya.bookShop.service.api.AssortmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AssortmentServiceImpl implements AssortmentService {

    private final RestTemplate restTemplate;

    @Autowired
    public AssortmentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<AssortmentDto> save(AssortmentDto assortmentDto) {
        return restTemplate.postForEntity(UrlConst.AssortmentUrl + "create/", assortmentDto, AssortmentDto.class);
    }

    @Override
    public ResponseEntity<AssortmentDto> update(AssortmentDto assortmentDto) {
        return restTemplate.postForEntity(UrlConst.AssortmentUrl + "update/" +
                SecurityContextHolder.getContext().getAuthentication().getName(), assortmentDto, AssortmentDto.class);
    }

    @Override
    public boolean existsByBook(Integer bookId, Integer shopId) {
        return restTemplate.
                getForObject(UrlConst.AssortmentUrl + "exists/" + bookId + "/" + shopId, Boolean.class);
    }

    @Override
    public void delete(Integer bookId, Integer shopId) {
        restTemplate.getForObject(UrlConst.AssortmentUrl + "delete/" + bookId + "/" + shopId, HttpStatus.class);
    }

    @Override
    public AssortmentDto getOne(Integer bookId, Integer shopId) {
        return restTemplate.getForObject(UrlConst.AssortmentUrl + bookId + "/" + shopId, AssortmentDto.class);
    }

    @Override
    public List<AssortmentDto> getByBook(Integer bookId) {
        return restTemplate.getForObject(UrlConst.AssortmentUrl + bookId, List.class);
    }
}
