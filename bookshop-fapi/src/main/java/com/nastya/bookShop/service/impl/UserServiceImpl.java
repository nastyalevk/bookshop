package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.user.UserDto;
import com.nastya.bookShop.service.api.EmailService;
import com.nastya.bookShop.service.api.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
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
    public void saveUser(UserDto userDto){
        restTemplate.postForEntity(UrlConst.UserUrl + "create/", userDto, String.class);
    }

    @Override
    public void updateUser(UserDto userDto) {
        restTemplate.postForEntity(UrlConst.UserUrl + "update/" +
                SecurityContextHolder.getContext().getAuthentication().getName(), userDto, String.class);
    }

    @Override
    public List<UserDto> findAll() {
        return restTemplate.getForObject(UrlConst.UserUrl, List.class);
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
