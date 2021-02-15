package nastya.BookShop.service.api;

import nastya.BookShop.dto.book.BookDto;
import nastya.BookShop.dto.response.PageResponse;

import java.util.List;

public interface BookService {

    BookDto findById(Integer id);

    List<BookDto> findAll();

    BookDto saveBook(BookDto book);

    PageResponse getAllBooksPage(String bookName, int page, int size, String[] sort);

    PageResponse getBookByShop(int page, int size, Integer shopId);

    Boolean isBook(Integer id);
}
