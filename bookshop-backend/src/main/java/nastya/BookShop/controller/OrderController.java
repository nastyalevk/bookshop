package nastya.BookShop.controller;

import nastya.BookShop.dto.order.OrderDto;
import nastya.BookShop.dto.response.PageResponse;
import nastya.BookShop.service.api.OrderService;
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

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<OrderDto>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws ParseException {
        return new ResponseEntity<>(orderService.saveOrder(orderDto), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) throws ParseException {
        return new ResponseEntity<>(orderService.updateOrder(orderDto), HttpStatus.OK);
    }

    @GetMapping("/client/")
    public ResponseEntity<PageResponse<OrderDto>> getOrdersByClient(@RequestParam(required = false) int orderNumber,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "9") int size,
                                                                    @RequestParam() String username) {
        return new ResponseEntity<>
                (orderService.findByClientUsername(orderNumber, page, size, username), HttpStatus.OK);
    }

    @GetMapping("/shop/")
    public ResponseEntity<PageResponse<OrderDto>> getOrdersByShop(@RequestParam(required = false) int orderNumber,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "9") int size,
                                                                  @RequestParam() int shopId,
                                                                  @RequestParam(name = "usernameRequested") String username) {
        return new ResponseEntity<>(orderService.getOrderByShop(orderNumber, page, size, shopId, username), HttpStatus.OK);
    }
}
