package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.book.BookDto;
import org.springframework.http.ResponseEntity;

public interface BookService {

    BookDto save(BookDto bookDto);

    ResponseEntity getAllBook(String bookName, int page, int size, String[] sort);

    BookDto getOne(Integer id);

    ResponseEntity getBooksByShop(Integer page, Integer size, Integer shopId);

    Boolean isBook(Integer id);

}
