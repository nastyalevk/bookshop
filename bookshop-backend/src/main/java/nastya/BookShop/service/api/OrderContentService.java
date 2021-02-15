package nastya.BookShop.service.api;

import nastya.BookShop.dto.orderContent.OrderContentDto;

import java.util.List;

public interface OrderContentService {

    List<OrderContentDto> findAll();

    List<OrderContentDto> getOrderContent(Integer orderId);

    OrderContentDto saveOrderContent(OrderContentDto orderContent) throws Exception;

    OrderContentDto updateOrderContent(OrderContentDto orderContent) throws Exception;

    OrderContentDto getOrderContent(Integer orderId, Integer bookId);

    void deleteOrderContent(OrderContentDto orderContent);

}
