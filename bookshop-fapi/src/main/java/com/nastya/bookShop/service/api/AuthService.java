package com.nastya.bookShop.service.api;

import com.nastya.bookShop.exception.CredentialsException;
import com.nastya.bookShop.model.request.LoginRequest;
import com.nastya.bookShop.model.request.SignUpRequest;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface AuthService {

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignUpRequest signUpRequest) throws UnsupportedEncodingException, MessagingException, CredentialsException;

    ResponseEntity<?> newUserByAdmin(SignUpRequest signUpRequest) throws UnsupportedEncodingException, MessagingException;
}
