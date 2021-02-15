package nastya.BookShop.controller;

import nastya.BookShop.dto.review.BookReviewDto;
import nastya.BookShop.dto.review.ShopReviewDto;
import nastya.BookShop.service.api.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("shop/{id}")
    public ResponseEntity<List<ShopReviewDto>> getShopReviewClient(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(reviewService.getShopReview(id), HttpStatus.OK);
    }

    @GetMapping("book/{id}")
    public ResponseEntity<List<BookReviewDto>> getBookReviewClient(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(reviewService.getBookReview(id), HttpStatus.OK);
    }

    @GetMapping("approve/shop/")
    public ResponseEntity<List<ShopReviewDto>> getShopReviewAdmin() {
        return new ResponseEntity<>(reviewService.getShopReviewAdmin(), HttpStatus.OK);
    }

    @GetMapping("one/book/{reviewId}")
    public ResponseEntity<BookReviewDto> getOneBookReview(@PathVariable("reviewId") Integer reviewId) {
        return new ResponseEntity<>(reviewService.getOneBookReview(reviewId), HttpStatus.OK);
    }

    @GetMapping("one/shop/{reviewId}")
    public ResponseEntity<ShopReviewDto> getOneShopReview(@PathVariable("reviewId") Integer reviewId) {
        return new ResponseEntity<>(reviewService.getOneShopReview(reviewId), HttpStatus.OK);
    }

    @GetMapping("approve/book/")
    public ResponseEntity<List<BookReviewDto>> getBookReviewAdmin() {
        return new ResponseEntity<>(reviewService.getBookReviewAdmin(), HttpStatus.OK);
    }

    @PostMapping("book/create")
    public ResponseEntity<BookReviewDto> createBookReview(@RequestBody BookReviewDto BookReviewDto)
            throws ParseException {
        return new ResponseEntity<>(reviewService.saveBookReview(BookReviewDto), HttpStatus.OK);
    }

    @PostMapping("shop/create")
    public ResponseEntity<ShopReviewDto> createShopReview(@RequestBody ShopReviewDto ShopReviewDto)
            throws ParseException {
        return new ResponseEntity<>(reviewService.saveShopReview(ShopReviewDto), HttpStatus.OK);
    }

    @PostMapping("delete/book")
    public ResponseEntity deleteBookReview(@RequestBody BookReviewDto BookReviewDto)
            throws ParseException {
        reviewService.deleteBookReview(BookReviewDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("delete/shop")
    public ResponseEntity deleteShopReview(@RequestBody ShopReviewDto ShopReviewDto)
            throws ParseException {
        reviewService.deleteShopReview(ShopReviewDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
