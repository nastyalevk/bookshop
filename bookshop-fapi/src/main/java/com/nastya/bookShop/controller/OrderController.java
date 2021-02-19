package com.nastya.bookShop.controller;

import com.nastya.bookShop.model.Order.OrderContentDto;
import com.nastya.bookShop.model.Order.OrderDto;
import com.nastya.bookShop.service.api.OrderService;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    public final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.saveOrder(orderDto), HttpStatus.OK);
    }

    @PostMapping("/content/create")
    public ResponseEntity<?> createOrderContent(@RequestBody OrderContentDto orderContentDto) {
        return new ResponseEntity<>(orderService.saveOrderContent(orderContentDto), HttpStatus.OK);
    }

    @PostMapping("/content/update")
    public ResponseEntity<OrderContentDto> updateOrderContent(@RequestBody OrderContentDto orderContentDto) {
        return new ResponseEntity<>(orderService.updateOrderContent(orderContentDto), HttpStatus.OK);
    }

    @GetMapping("/shop/")
    public ResponseEntity getOrdersByShop(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "9") int size,
                                          @RequestParam() int shopId) {
        return new ResponseEntity<>(orderService.getOrderByShop(page, size, shopId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @GetMapping("/content/{orderId}")
    public ResponseEntity<List<OrderContentDto>> getOrderContent(@PathVariable("orderId") Integer orderId) {
        return new ResponseEntity<>(orderService.getOrderContent(orderId), HttpStatus.OK);
    }

    @GetMapping("/client/")
    public ResponseEntity getOrdersByClient(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "9") int size) {
        return new ResponseEntity<>(orderService.getOrdersByClientUsername(page, size), HttpStatus.OK);
    }

    @GetMapping("/content/{orderId}/{bookId}")
    public ResponseEntity<OrderContentDto> getOrderContent(@PathVariable("orderId") Integer orderId,
                                                           @PathVariable("bookId") Integer bookId) {
        return new ResponseEntity<>(orderService.getOrderContent(orderId, bookId), HttpStatus.OK);
    }

    @PostMapping("/content/delete")
    public ResponseEntity deleteOrderContent(@RequestBody OrderContentDto orderContentDto) {
        orderService.deleteOrderContent(orderContentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
