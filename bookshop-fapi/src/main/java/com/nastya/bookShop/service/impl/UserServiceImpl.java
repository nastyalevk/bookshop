package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.response.PageResponse;
import com.nastya.bookShop.model.user.UserDto;
import com.nastya.bookShop.service.api.UserService;
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
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDto findByUserName(String userName) {
        return restTemplate.getForObject(UrlConst.UserUrl + "find/username/" + userName, UserDto.class);
    }

    @Override
    public Boolean existsByUserName(String userName) {
        return restTemplate.getForObject(UrlConst.UserUrl + "exist/username/" + userName, Boolean.class);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return restTemplate.getForObject(UrlConst.UserUrl + "exist/email/" + email, Boolean.class);
    }

    @Override
    public void saveUser(UserDto userDto) {
        restTemplate.postForEntity(UrlConst.UserUrl + "create/", userDto, String.class);
    }

    @Override
    public void updateUser(UserDto userDto) {
        restTemplate.postForEntity(UrlConst.UserUrl + "update/" +
                SecurityContextHolder.getContext().getAuthentication().getName(), userDto, String.class);
    }

    @Override
    public ResponseEntity<PageResponse> findAll(int page, int size) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlConst.UserUrl)
                .queryParam("page", page)
                .queryParam("size", size);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, PageResponse.class);
    }

    @Override
    public UserDto getOne(Integer id) {
        return restTemplate.getForObject(UrlConst.UserUrl + id, UserDto.class);
    }

    @Override
    public void updateUserRoles(String[] roles, Integer id, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlConst.UserUrl + "update-roles/")
                .queryParam("roles", roles)
                .queryParam("userId", id)
                .queryParam("message", message)
                .queryParam("adminUsername", SecurityContextHolder.getContext().getAuthentication().getName());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);
    }

}
