package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.role.RoleDto;
import com.nastya.bookShop.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RestTemplate restTemplate;

    @Autowired
    public RoleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<RoleDto> findByName(String name) {
        return restTemplate.getForObject(UrlConst.RoleUrl + name, List.class);
    }
}
