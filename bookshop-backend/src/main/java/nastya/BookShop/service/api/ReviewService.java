package nastya.BookShop.service.api;

import nastya.BookShop.dto.review.BookReviewDto;
import nastya.BookShop.dto.review.ShopReviewDto;

import java.text.ParseException;
import java.util.List;

public interface ReviewService {

    void deleteBookReview(BookReviewDto bookReviewDto) throws ParseException;

    void deleteShopReview(ShopReviewDto shopReviewDto) throws ParseException;

    List<BookReviewDto> getBookReview(Integer bookId);

    List<ShopReviewDto> getShopReview(Integer bookId);

    List<BookReviewDto> getBookReviewAdmin();

    List<ShopReviewDto> getShopReviewAdmin();

    BookReviewDto saveBookReview(BookReviewDto bookReviewDto) throws ParseException;

    ShopReviewDto saveShopReview(ShopReviewDto shopReviewDto) throws ParseException;

    BookReviewDto getOneBookReview(Integer reviewId);

    ShopReviewDto getOneShopReview(Integer reviewId);

}
