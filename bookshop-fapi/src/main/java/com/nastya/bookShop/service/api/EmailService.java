package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.user.ConfirmationTokenDto;
import com.nastya.bookShop.model.user.UserDto;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    void sendVerificationEmail(UserDto user)
            throws MessagingException, UnsupportedEncodingException;

    void sendCreation(UserDto user, String randomCode)
            throws MessagingException, UnsupportedEncodingException;

    ResponseEntity verify(String verificationCode) throws UnsupportedEncodingException, MessagingException;

    ConfirmationTokenDto saveToken(UserDto user);

}
