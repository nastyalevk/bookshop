package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.Order.OrderContentDto;
import com.nastya.bookShop.model.Order.OrderDto;
import com.nastya.bookShop.model.response.PageResponse;
import com.nastya.bookShop.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final RestTemplate restTemplate;

    @Autowired
    public OrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public OrderDto saveOrder(OrderDto orderDto) {
        return restTemplate.postForEntity(UrlConst.OrderUrl + "/create", orderDto, OrderDto.class).getBody();
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        return restTemplate.postForEntity(UrlConst.OrderUrl + "/update", orderDto, OrderDto.class).getBody();
    }

    @Override
    public OrderContentDto saveOrderContent(OrderContentDto orderContentDto) {
        return restTemplate.postForEntity
                (UrlConst.OrderContentUrl + "/create", orderContentDto, OrderContentDto.class).getBody();
    }

    @Override
    public OrderContentDto updateOrderContent(OrderContentDto orderContentDto) {
        return restTemplate.postForEntity
                (UrlConst.OrderContentUrl + "/update", orderContentDto, OrderContentDto.class).getBody();
    }

    @Override
    public ResponseEntity deleteOrderContent(OrderContentDto orderContentDto) {
        return restTemplate.postForEntity
                (UrlConst.OrderContentUrl + "/delete", orderContentDto, ResponseEntity.class);
    }

    @Override
    public ResponseEntity getOrdersByClientUsername(int orderNumber, int page, int size) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlConst.OrderUrl + "client/")
                .queryParam("username", SecurityContextHolder.getContext().getAuthentication().getName())
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("orderNumber",orderNumber);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, PageResponse.class);

    }

    @Override
    public ResponseEntity getOrderByShop(Integer orderNumber, int page, int size, int shopId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlConst.OrderUrl + "/shop/")
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("shopId", shopId)
                .queryParam("usernameRequested", SecurityContextHolder.getContext().getAuthentication().getName())
                .queryParam("orderNumber",orderNumber);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, PageResponse.class);
    }

    @Override
    public OrderDto getOrder(Integer id) {
        return restTemplate.getForObject(UrlConst.OrderUrl + "/" + id, OrderDto.class);
    }

    @Override
    public List<OrderContentDto> getOrderContent(Integer orderId) {
        return restTemplate.getForObject(UrlConst.OrderContentUrl + "/" + orderId, List.class);
    }

    @Override
    public OrderContentDto getOrderContent(Integer orderId, Integer bookId) {
        return restTemplate.getForObject(UrlConst.OrderContentUrl + "/" + orderId + "/" + bookId,
                OrderContentDto.class);
    }
}
