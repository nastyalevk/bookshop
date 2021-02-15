package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.userRoles.UserRolesDto;
import com.nastya.bookShop.service.api.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final RestTemplate restTemplate;

    @Autowired
    public UserRoleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void save(UserRolesDto userRolesDto) {
        restTemplate.postForEntity(UrlConst.UserRoleUrl + "create/", userRolesDto, String.class);
    }
}
