package com.nastya.bookShop.controller;

import com.nastya.bookShop.model.book.BookDto;
import com.nastya.bookShop.service.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    public final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping("/create")
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.save(bookDto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.save(bookDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getOne(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(bookService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("exist/{id}")
    public ResponseEntity<Boolean> isBook(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(bookService.isBook(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> findAll(@RequestParam(required = false) String bookName,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "3") int size,
                                  @RequestParam(required = false) String[] sort) {
        return new ResponseEntity<>(bookService.getAllBook(bookName, page, size, sort), HttpStatus.OK);
    }

    @GetMapping("/shop/")
    public ResponseEntity<?> getBooksByShop(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "9") int size,
                                         @RequestParam() Integer id) {
        return new ResponseEntity<>(bookService.getBooksByShop(page, size, id), HttpStatus.OK);
    }
}
