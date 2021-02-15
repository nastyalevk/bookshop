package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.book.BookDto;
import com.nastya.bookShop.model.response.PageResponse;
import com.nastya.bookShop.service.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BookServiceImpl implements BookService {

    private final RestTemplate restTemplate;

    @Autowired
    public BookServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public BookDto save(BookDto bookDto) {
        return restTemplate.postForEntity(UrlConst.BookUrl + "create", bookDto, BookDto.class).getBody();
    }

    @Override
    public ResponseEntity getAllBook(String bookName, int page, int size, String[] sort) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlConst.BookUrl)
                .queryParam("bookName", bookName)
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("sort", sort);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, PageResponse.class);
    }

    @Override
    public BookDto getOne(Integer id) {
        return restTemplate.getForObject(UrlConst.BookUrl + id, BookDto.class);
    }

    @Override
    public ResponseEntity getBooksByShop(Integer page, Integer size, Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlConst.BookUrl + "shop/")
                .queryParam("id", id)
                .queryParam("page", page)
                .queryParam("size", size);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, PageResponse.class);
    }

    @Override
    public Boolean isBook(Integer id) {
        return restTemplate.getForObject(UrlConst.BookUrl+"exist/"+id, Boolean.class);
    }
}
