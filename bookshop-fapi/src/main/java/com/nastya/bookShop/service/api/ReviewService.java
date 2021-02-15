package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.review.BookReviewDto;
import com.nastya.bookShop.model.review.ShopReviewDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {

    List<ShopReviewDto> getShopReviewClient(Integer shopId);

    List<BookReviewDto> getBookReviewClient(Integer bookId);

    List<ShopReviewDto> getShopReviewAdmin();

    List<BookReviewDto> getBookReviewAdmin();

    ShopReviewDto saveShopReview(ShopReviewDto shopReviewDto);

    BookReviewDto saveBookReview(BookReviewDto bookReviewDto);

    ShopReviewDto getOneShopReview(Integer shopId);

    BookReviewDto getOneBookReview(Integer bookId);

    ResponseEntity<BookReviewDto> deleteBookReview(BookReviewDto bookReviewDto);

    ResponseEntity<ShopReviewDto> deleteShopReview(ShopReviewDto shopReviewDto);
}
