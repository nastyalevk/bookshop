package nastya.BookShop.service.impl;

import nastya.BookShop.model.DateFormatter;
import nastya.BookShop.dto.order.OrderClassification;
import nastya.BookShop.dto.order.OrderDto;
import nastya.BookShop.dto.response.PageResponse;
import nastya.BookShop.exception.NoAccessException;
import nastya.BookShop.model.Order;
import nastya.BookShop.model.Shop;
import nastya.BookShop.repository.ClassificationRepository;
import nastya.BookShop.repository.OrderRepository;
import nastya.BookShop.repository.ShopRepository;
import nastya.BookShop.repository.UserRepository;
import nastya.BookShop.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ShopRepository shopRepository;
    private final ClassificationRepository classificationRepository;
    private final UserRepository userRepository;
    private final DateFormatter dateFormatter;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ShopRepository shopRepository,
                            ClassificationRepository classificationRepository, UserRepository userRepository,
                            DateFormatter dateFormatter) {
        this.orderRepository = orderRepository;
        this.shopRepository = shopRepository;
        this.classificationRepository = classificationRepository;
        this.userRepository = userRepository;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public PageResponse<OrderDto> findByClientUsername(Integer orderNumber, int page, int size, String username) {
        Pageable paging = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Order> orders;
        if (orderNumber==-1) {
            orders = orderRepository.findByUserUsername(username, paging);
        } else {
            orders = orderRepository.findByOrderNumberAndUserUsername(orderNumber, username, paging);
        }
        return transfer(orders);
    }

    @Override
    public OrderDto findById(Integer id) {
        return transfer(orderRepository.getOne(id));
    }

    @Override
    public List<OrderDto> findAll() {
        return transfer(orderRepository.findAll());
    }

    @Override
    public OrderDto saveOrder(OrderDto orderDto) throws ParseException {
        Order order = transfer(orderDto);
        if (order.getOrderSubmitDate().after(order.getOrderCompleteDate())) {
            throw new IllegalArgumentException("Invalid date!");
        }
        return transfer(orderRepository.save(transfer(orderDto)));
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) throws ParseException {
        return transfer(orderRepository.save(transfer(orderDto)));
    }

    @Override
    public PageResponse<OrderDto> getOrderByShop
            (Integer orderNumber, int page, int size, int shopId, String username) {
        Shop shop = shopRepository.getOne(shopId);
        if (!shop.getUser().getUsername().equals(username)) {
            throw new NoAccessException("You dont have access for this page!");
        }
        Pageable paging = PageRequest.of(page, size,
                Sort.by("id").descending());
        Page<Order> orders;
        if (orderNumber==-1) {
            orders = orderRepository.findByShopId(shopId, paging);
        } else {
            orders = orderRepository.findByOrderNumberAndShopId(orderNumber, shopId, paging);
        }
        return transfer(orders);
    }

    private List<OrderDto> transfer(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order i : orders) {
            orderDtos.add(transfer(i));
        }
        return orderDtos;
    }

    private OrderDto transfer(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getId());
        orderDto.setOrderNumber(order.getOrderNumber());
        orderDto.setShopId(order.getShop().getId());
        orderDto.setDeliveryAddress(order.getDeliveryAddress());
        orderDto.setDescription(order.getDescription());
        orderDto.setOrderSubmitDate(dateFormatter.formatDate(order.getOrderSubmitDate()));
        orderDto.setClassification(OrderClassification.valueOf(order.getClassification().getName().toUpperCase()));
        orderDto.setCost(order.getCost());
        orderDto.setOrderCompleteDate(dateFormatter.formatDate(order.getOrderCompleteDate()));
        orderDto.setUsername(order.getUser().getUsername());
        return orderDto;
    }

    private Order transfer(OrderDto orderDto) throws ParseException {
        Order order = new Order();
        order.setId(orderDto.getOrderId());
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setShop(shopRepository.getShopById(orderDto.getShopId()));
        order.setDeliveryAddress(orderDto.getDeliveryAddress());
        order.setDescription(orderDto.getDescription());
        order.setOrderSubmitDate(dateFormatter.formatString(orderDto.getOrderSubmitDate()));
        order.setClassification(classificationRepository.getClassificationByNameAndAndClassificationName(
                orderDto.getClassification().toString(), "order"));
        order.setCost(orderDto.getCost());
        order.setOrderCompleteDate(dateFormatter.formatString(orderDto.getOrderCompleteDate()));
        order.setUser(userRepository.findByUsername(orderDto.getUsername()));
        return order;
    }

    private PageResponse<OrderDto> transfer(Page<Order> page) {
        PageResponse<OrderDto> pageResponse = new PageResponse<>();
        pageResponse.setContent(transfer(page.getContent()));
        pageResponse.setCurrentPage(page.getNumber());
        pageResponse.setTotalElements(page.getTotalElements());
        pageResponse.setTotalPages(page.getTotalPages());
        return pageResponse;
    }
}
