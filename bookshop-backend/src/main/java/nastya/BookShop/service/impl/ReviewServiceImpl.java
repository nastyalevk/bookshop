package nastya.BookShop.service.impl;

import nastya.BookShop.config.DateFormatter;
import nastya.BookShop.dto.review.BookReviewDto;
import nastya.BookShop.dto.review.ShopReviewDto;
import nastya.BookShop.model.BookReview;
import nastya.BookShop.model.ShopReview;
import nastya.BookShop.repository.BookRepository;
import nastya.BookShop.repository.BookReviewRepository;
import nastya.BookShop.repository.ShopRepository;
import nastya.BookShop.repository.ShopReviewRepository;
import nastya.BookShop.repository.UserRepository;
import nastya.BookShop.service.api.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {

    private final BookReviewRepository bookReviewRepository;
    private final ShopReviewRepository shopReviewRepository;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReviewServiceImpl(BookReviewRepository bookReviewRepository, ShopReviewRepository shopReviewRepository, ShopRepository shopRepository,
                             UserRepository userRepository, BookRepository bookRepository) {
        this.bookReviewRepository = bookReviewRepository;
        this.shopReviewRepository = shopReviewRepository;
        this.shopRepository = shopRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<ShopReviewDto> getShopReview(Integer id) {
        List<ShopReview> reviews = shopReviewRepository.findAllByShopIdAndApproved(id, true);
        List<ShopReviewDto> reviewDtos = new ArrayList<>();
        for (ShopReview i : reviews) {
            reviewDtos.add(transfer(i));
        }
        return reviewDtos;
    }

    @Override
    public List<ShopReviewDto> getShopReviewAdmin() {
        List<ShopReview> reviews = shopReviewRepository.findAllByApproved(false);
        List<ShopReviewDto> reviewDtos = new ArrayList<>();
        for (ShopReview i : reviews) {
            reviewDtos.add(transfer(i));
        }
        return reviewDtos;
    }

    @Override
    public BookReviewDto saveBookReview(BookReviewDto bookReviewDto) throws ParseException {
        return transfer(bookReviewRepository.save(transfer(bookReviewDto)));
    }

    @Override
    public ShopReviewDto saveShopReview(ShopReviewDto shopReviewDto) throws ParseException {
        return transfer(shopReviewRepository.save(transfer(shopReviewDto)));
    }

    @Override
    public BookReviewDto getOneBookReview(Integer reviewId) {
        return transfer(this.bookReviewRepository.getOne(reviewId));
    }

    @Override
    public ShopReviewDto getOneShopReview(Integer reviewId) {
        return transfer(this.shopReviewRepository.getOne(reviewId));
    }

    @Override
    public void deleteBookReview(BookReviewDto bookReviewDto) throws ParseException {
        bookReviewRepository.delete(transfer(bookReviewDto));
    }

    @Override
    public void deleteShopReview(ShopReviewDto shopReviewDto) throws ParseException {
        shopReviewRepository.delete(transfer(shopReviewDto));
    }

    @Override
    public List<BookReviewDto> getBookReview(Integer id) {
        List<BookReview> reviews = bookReviewRepository.findAllByBookIdAndApproved(id, true);
        List<BookReviewDto> reviewDtos = new ArrayList<>();
        for (BookReview i : reviews) {
            reviewDtos.add(transfer(i));
        }
        return reviewDtos;
    }

    @Override
    public List<BookReviewDto> getBookReviewAdmin() {
        List<BookReview> reviews = bookReviewRepository.findAllByApproved(false);
        List<BookReviewDto> reviewDtos = new ArrayList<>();
        for (BookReview i : reviews) {
            reviewDtos.add(transfer(i));
        }
        return reviewDtos;
    }

    private BookReviewDto transfer(BookReview review) {
        BookReviewDto reviewDto = new BookReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setUsername(review.getUser().getUsername());
        reviewDto.setComment(review.getComment());
        reviewDto.setRating(review.getRating());
        reviewDto.setBookId(review.getBook().getId());
        reviewDto.setDatetime(new DateFormatter().formatDate(review.getDatetime()));
        reviewDto.setApproved(review.getApproved());
        return reviewDto;
    }

    private ShopReviewDto transfer(ShopReview review) {
        ShopReviewDto reviewDto = new ShopReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setUsername(review.getUser().getUsername());
        reviewDto.setComment(review.getComment());
        reviewDto.setRating(review.getRating());
        reviewDto.setShopId(review.getShop().getId());
        reviewDto.setDatetime(new DateFormatter().formatDate(review.getDatetime()));
        reviewDto.setApproved(review.getApproved());
        return reviewDto;
    }

    private BookReview transfer(BookReviewDto reviewDto) throws ParseException {
        BookReview review = new BookReview();
        review.setId(reviewDto.getId());
        review.setUser(userRepository.findByUsername(reviewDto.getUsername()));
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        review.setBook(bookRepository.getOne(reviewDto.getBookId()));
        review.setDatetime(new DateFormatter().formatDate(reviewDto.getDatetime()));
        review.setApproved(reviewDto.isApproved());
        return review;
    }

    private ShopReview transfer(ShopReviewDto reviewDto) throws ParseException {
        ShopReview review = new ShopReview();
        review.setId(reviewDto.getId());
        review.setUser(userRepository.findByUsername(reviewDto.getUsername()));
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        review.setShop(shopRepository.getOne(reviewDto.getShopId()));
        review.setDatetime(new DateFormatter().formatDate(reviewDto.getDatetime()));
        review.setApproved(reviewDto.isApproved());
        return review;
    }
}
