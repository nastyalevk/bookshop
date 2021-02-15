package nastya.BookShop.controller;

import nastya.BookShop.dto.orderContent.OrderContentDto;
import nastya.BookShop.service.api.OrderContentService;
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
@RequestMapping("/order/content")
public class OrderContentController {

    private final OrderContentService orderContentService;

    @Autowired
    public OrderContentController(OrderContentService orderContentService) {
        this.orderContentService = orderContentService;
    }

    @GetMapping()
    public ResponseEntity<List<OrderContentDto>> findAll() {
        return new ResponseEntity<>(orderContentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<OrderContentDto>> getOrderContent(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(orderContentService.getOrderContent(id), HttpStatus.OK);
    }

    @GetMapping("/{orderId}/{bookId}")
    public ResponseEntity<OrderContentDto> getOrderContent(@PathVariable("orderId") Integer orderId,
                                                           @PathVariable("bookId") Integer bookId) {
        return new ResponseEntity<>(orderContentService.getOrderContent(orderId, bookId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderContentDto> saveContent(@RequestBody OrderContentDto orderContentDto) throws Exception {
        return new ResponseEntity<>(orderContentService.saveOrderContent(orderContentDto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<OrderContentDto> updateContent(@RequestBody OrderContentDto orderContentDto) throws Exception {
        return new ResponseEntity<>(orderContentService.updateOrderContent(orderContentDto), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<OrderContentDto> deleteContent(@RequestBody OrderContentDto orderContentDto) {
        orderContentService.deleteOrderContent(orderContentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
