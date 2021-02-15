package nastya.BookShop.controller;

import nastya.BookShop.dto.book.BookDto;
import nastya.BookShop.dto.response.PageResponse;
import nastya.BookShop.service.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<PageResponse<BookDto>> findAll(@RequestParam(required = false) String bookName,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "3") int size,
                                                @RequestParam(required = false) String[] sort) {
        return new ResponseEntity<>(bookService.getAllBooksPage(bookName, page, size, sort), HttpStatus.OK);
    }


    @PostMapping(path = "/create")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.saveBook(bookDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/shop/")
    public ResponseEntity<PageResponse<BookDto>> getBooksByShop(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "9") int size,
                                                       @RequestParam() int id) {
        return new ResponseEntity<>(bookService.getBookByShop(page, size, id), HttpStatus.OK);

    }

    @GetMapping("exist/{id}")
    public ResponseEntity<Boolean> isBook(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(bookService.isBook(id), HttpStatus.OK);
    }
}
