package com.nastya.bookShop.service.impl;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.model.user.ConfirmationTokenDto;
import com.nastya.bookShop.service.api.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConfirmationTokenImpl implements ConfirmationTokenService {

    private final RestTemplate restTemplate;

    @Autowired
    public ConfirmationTokenImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ConfirmationTokenDto findByToken(String token) {
        return restTemplate.getForObject(UrlConst.TokenUrl + token, ConfirmationTokenDto.class);
    }

    @Override
    public ConfirmationTokenDto save(ConfirmationTokenDto confirmationTokenDto) {
        return restTemplate.postForEntity(UrlConst.TokenUrl + "create", confirmationTokenDto,
                ConfirmationTokenDto.class).getBody();
    }

}
