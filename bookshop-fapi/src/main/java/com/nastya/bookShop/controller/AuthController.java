package com.nastya.bookShop.controller;

import com.nastya.bookShop.config.UrlConst;
import com.nastya.bookShop.exception.CredentialsException;
import com.nastya.bookShop.model.request.LoginRequest;
import com.nastya.bookShop.model.request.SignUpRequest;
import com.nastya.bookShop.service.api.AuthService;
import com.nastya.bookShop.service.api.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(UrlConst.AuthUrl)
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;

    @Autowired
    public AuthController(AuthService authService, EmailService emailService) {
        this.authService = authService;
        this.emailService = emailService;
    }

    @PostMapping(path = "signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping(path = "signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest)
            throws UnsupportedEncodingException, MessagingException, CredentialsException {
        return authService.registerUser(signUpRequest);
    }

    @GetMapping("verify/")
    public ResponseEntity<?> verifyUser(@RequestParam(name = "code") String code)
            throws UnsupportedEncodingException, MessagingException {
        return emailService.verify(code);
    }

    @PostMapping(path = "user/new")
    public ResponseEntity<?> newUserByAdmin(@RequestBody SignUpRequest signUpRequest)
            throws UnsupportedEncodingException, MessagingException, CredentialsException {
        return authService.newUserByAdmin(signUpRequest);
    }
}
