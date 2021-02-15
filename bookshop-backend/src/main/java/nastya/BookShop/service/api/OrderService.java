package nastya.BookShop.service.api;

import nastya.BookShop.dto.order.OrderDto;
import nastya.BookShop.dto.response.PageResponse;

import java.text.ParseException;
import java.util.List;

public interface OrderService {

    PageResponse findByClientUsername(int page, int size, String username);

    OrderDto findById(Integer id);

    List<OrderDto> findAll();

    OrderDto saveOrder(OrderDto order) throws ParseException;

    PageResponse getOrderByShop(int page, int size, int shopId, String useraname);

}
