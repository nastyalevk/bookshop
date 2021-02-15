package com.nastya.bookShop.model.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {

    private Integer id;
    private String bookName;
    private String author;
    private String genre;
    private Integer publicationYear;
    private Integer pages;
    private String description;

}
