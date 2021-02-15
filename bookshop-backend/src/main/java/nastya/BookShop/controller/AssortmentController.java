package nastya.BookShop.controller;

import nastya.BookShop.dto.assortment.AssortmentDto;
import nastya.BookShop.service.api.AssortmentService;
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
@RequestMapping("/assortment")
public class AssortmentController {

    private final AssortmentService assortmentService;

    @Autowired
    public AssortmentController(AssortmentService assortmentService) {
        this.assortmentService = assortmentService;
    }

    @GetMapping("/price/{bookId}/{shopId}")
    public ResponseEntity<Integer> getPriceByBookShop(@PathVariable("bookId") Integer bookId,
                                                      @PathVariable("shopId") Integer shopId) {
        return new ResponseEntity<>(assortmentService.getPriceByBookShop(bookId, shopId), HttpStatus.OK);
    }

    @PostMapping(path = "/create/")
    public ResponseEntity<AssortmentDto> createAssortment(@RequestBody AssortmentDto assortmentDto)
            throws ParseException {
        return new ResponseEntity<>(assortmentService.save(assortmentDto), HttpStatus.OK);
    }

    @PostMapping(path = "/update/{username}")
    public ResponseEntity<AssortmentDto> updateAssortment(@RequestBody AssortmentDto assortmentDto,
                                                          @PathVariable("username") String username)
            throws ParseException {
        return new ResponseEntity<>(assortmentService.update(assortmentDto, username), HttpStatus.OK);
    }

    @GetMapping("/exists/{bookId}/{shopId}")
    public ResponseEntity<Boolean> existsByBook(@PathVariable("bookId") Integer bookId,
                                                @PathVariable("shopId") Integer shopId) {
        return new ResponseEntity<>(assortmentService.existsByBook(bookId, shopId), HttpStatus.OK);
    }

    @GetMapping(path = "/delete/{bookId}/{shopId}")
    public ResponseEntity<HttpStatus> deleteAssortment(@PathVariable("bookId") Integer bookId,
                                           @PathVariable("shopId") Integer shopId) throws ParseException {
        assortmentService.delete(bookId, shopId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{bookId}/{shopId}")
    public ResponseEntity<AssortmentDto> getOne(@PathVariable("bookId") Integer bookId,
                                                @PathVariable("shopId") Integer shopId) {
        return new ResponseEntity<>(assortmentService.getOne(bookId, shopId), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<List<AssortmentDto>> getOne(@PathVariable("bookId") Integer bookId) {
        return new ResponseEntity<>(assortmentService.getByBook(bookId), HttpStatus.OK);
    }
}
