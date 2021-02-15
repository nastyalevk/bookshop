package nastya.BookShop.controller;

import nastya.BookShop.dto.shop.ShopDto;
import nastya.BookShop.service.api.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping(path = "/create/{username}")
    public ResponseEntity<ShopDto> createShop(@RequestBody ShopDto shopDto,
                                              @PathVariable("username") String username) {
        return new ResponseEntity<>(shopService.saveShop(shopDto, username), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<ShopDto>> findUserShops(@PathVariable("username") String username) {
        return new ResponseEntity<>(shopService.userShops(username), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<List<ShopDto>> findBookShops(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(shopService.getShopsByBook(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDto> getShop(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(shopService.getOne(id), HttpStatus.OK);
    }

}
