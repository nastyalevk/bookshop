package nastya.BookShop.service.impl;

import nastya.BookShop.dto.book.BookDto;
import nastya.BookShop.dto.response.PageResponse;
import nastya.BookShop.model.Assortment;
import nastya.BookShop.model.Book;
import nastya.BookShop.repository.AssortmentRepository;
import nastya.BookShop.repository.BookRepository;
import nastya.BookShop.service.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AssortmentRepository assortmentRepository;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AssortmentRepository assortmentRepository) {
        this.bookRepository = bookRepository;
        this.assortmentRepository = assortmentRepository;
    }

    @Override
    public BookDto findById(Integer id) {
        return transfer(bookRepository.getOne(id));
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<BookDto>();
        for (Book i : books) {
            bookDtos.add(transfer(i));
        }
        return bookDtos;
    }

    @Override
    public BookDto saveBook(BookDto bookDto) {
        return transfer(bookRepository.save(transfer(bookDto)));
    }

    @Override
    public PageResponse getAllBooksPage(String bookName, int page, int size, String[] sort) {
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(sortType(sort)));
        Page<Book> pageBook;
        if (bookName == null) {
            pageBook = bookRepository.findAll(pagingSort);
        } else {
            pageBook = bookRepository.findByBookNameContaining(bookName, pagingSort);
        }

        return transfer(pageBook);
    }

    @Override
    public PageResponse getBookByShop(int page, int size, Integer shopId) {
        Pageable paging = PageRequest.of(page, size);
        Page<Assortment> assortments = assortmentRepository.findAllByAssortmentIdShopId(shopId, paging);
        List<BookDto> bookDtos = new ArrayList<>();
        for (Assortment i : assortments) {
            bookDtos.add(transfer(i.getAssortmentId().getBook()));
        }
        PageResponse<BookDto> pageResponse = new PageResponse<>();
        pageResponse.setContent(bookDtos);
        pageResponse.setCurrentPage(assortments.getNumber());
        pageResponse.setTotalElements(assortments.getTotalElements());
        pageResponse.setTotalPages(assortments.getTotalPages());
        return pageResponse;
    }

    @Override
    public Boolean isBook(Integer id) {
        return bookRepository.existsBookById(id);
    }

    private List<Sort.Order> sortType(String[] fieldsort) {
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        for (String i : fieldsort) {
            String[] split = i.split("_");
            Sort sort = Sort.unsorted();
            if ("asc".equalsIgnoreCase(split[1])) {
                orders.add(new Sort.Order(Sort.Direction.ASC, split[0]));
            } else if ("desc".equalsIgnoreCase(split[1])) {
                orders.add(new Sort.Order(Sort.Direction.DESC, split[0]));
            }
        }
        return orders;
    }

    private List<BookDto> transfer(List<Book> books) {
        List<BookDto> booksDto = new ArrayList<>();
        for (Book i : books) {
            booksDto.add(transfer(i));
        }
        return booksDto;
    }

    private BookDto transfer(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setBookName(book.getBookName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setGenre(book.getGenre());
        bookDto.setPages(book.getPages());
        bookDto.setPublicationYear(book.getPublicationYear());
        bookDto.setDescription(book.getDescription());
        return bookDto;
    }

    private Book transfer(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setBookName(bookDto.getBookName());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setPages(bookDto.getPages());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setDescription(bookDto.getDescription());
        return book;
    }

    private PageResponse transfer(Page page) {
        PageResponse<BookDto> pageResponse = new PageResponse<>();
        pageResponse.setContent(transfer(page.getContent()));
        pageResponse.setCurrentPage(page.getNumber());
        pageResponse.setTotalElements(page.getTotalElements());
        pageResponse.setTotalPages(page.getTotalPages());
        return pageResponse;
    }

}
