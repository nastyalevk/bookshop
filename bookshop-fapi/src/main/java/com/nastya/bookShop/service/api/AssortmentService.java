package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.Assortment.AssortmentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssortmentService {

    ResponseEntity<AssortmentDto> update(AssortmentDto assortmentDto);

    ResponseEntity<AssortmentDto> save(AssortmentDto assortmentDto);

    boolean existsByBook(Integer bookId, Integer shopId);

    void delete(Integer bookId, Integer shopId);

    AssortmentDto getOne(Integer bookId, Integer shopId);

    List<AssortmentDto> getByBook(Integer bookId);

}
