package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.Order.OrderContentDto;
import com.nastya.bookShop.model.Order.OrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    OrderDto saveOrder(OrderDto orderDto);

    OrderContentDto saveOrderContent(OrderContentDto orderContentDto);

    OrderContentDto updateOrderContent(OrderContentDto orderContentDto);

    ResponseEntity deleteOrderContent(OrderContentDto orderContentDto);

    ResponseEntity getOrdersByClientUsername(int page, int size);

    ResponseEntity getOrderByShop(int page, int size, int id);

    OrderDto getOrder(Integer id);

    List<OrderContentDto> getOrderContent(Integer orderId);

    OrderContentDto getOrderContent(Integer orderId, Integer bookId);

}
