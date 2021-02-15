package com.nastya.bookShop.model.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookReviewDto {

    private Integer id;
    private String username;
    private String comment;
    private Integer rating;
    private Integer bookId;
    private String datetime;
    private Boolean approved;

}
