package com.nastya.bookShop.model.book;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BooksDto {

    private List<BookDto> items;

}
