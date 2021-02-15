package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.user.ConfirmationTokenDto;

public interface ConfirmationTokenService {

    ConfirmationTokenDto findByToken(String token);

    ConfirmationTokenDto save(ConfirmationTokenDto confirmationTokenDto);

}
