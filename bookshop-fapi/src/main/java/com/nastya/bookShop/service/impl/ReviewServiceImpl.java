package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.review.BookReviewDto;
import com.nastya.bookShop.model.review.ShopReviewDto;
import com.nastya.bookShop.service.api.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final RestTemplate restTemplate;

    @Autowired
    public ReviewServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ShopReviewDto> getShopReviewClient(Integer shopId) {
        return restTemplate.getForObject(UrlConst.ReviewUrl + "shop/" + shopId, List.class);
    }

    @Override
    public List<BookReviewDto> getBookReviewClient(Integer bookId) {
        return restTemplate.getForObject(UrlConst.ReviewUrl + "book/" + bookId, List.class);
    }

    @Override
    public List<ShopReviewDto> getShopReviewAdmin() {
        return restTemplate.getForObject(UrlConst.ReviewUrl + "approve/shop/", List.class);
    }

    @Override
    public List<BookReviewDto> getBookReviewAdmin() {
        return restTemplate.getForObject(UrlConst.ReviewUrl + "approve/book/", List.class);
    }

    @Override
    public ShopReviewDto saveShopReview(ShopReviewDto shopReviewDto) {
        if (shopReviewDto.getUsername() == null) {
            shopReviewDto.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return this.restTemplate.
                postForEntity(UrlConst.ReviewUrl + "shop/create/", shopReviewDto, ShopReviewDto.class).getBody();
    }

    @Override
    public BookReviewDto saveBookReview(BookReviewDto bookReviewDto) {
        if (bookReviewDto.getUsername() == null) {
            bookReviewDto.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return this.restTemplate.
                postForEntity(UrlConst.ReviewUrl + "book/create/", bookReviewDto, BookReviewDto.class).getBody();
    }

    @Override
    public ShopReviewDto getOneShopReview(Integer reviewId) {
        return restTemplate.getForObject(UrlConst.ReviewUrl + "one/shop/" + reviewId, ShopReviewDto.class);
    }

    @Override
    public BookReviewDto getOneBookReview(Integer reviewId) {
        return restTemplate.getForObject(UrlConst.ReviewUrl + "one/book/" + reviewId, BookReviewDto.class);
    }

    @Override
    public ResponseEntity<BookReviewDto> deleteBookReview(BookReviewDto bookReviewDto) {
        return restTemplate.postForEntity(UrlConst.ReviewUrl + "delete/book", bookReviewDto, BookReviewDto.class);
    }

    @Override
    public ResponseEntity<ShopReviewDto> deleteShopReview(ShopReviewDto shopReviewDto) {
        return restTemplate.postForEntity(UrlConst.ReviewUrl + "delete/shop", shopReviewDto, ShopReviewDto.class);
    }
}
