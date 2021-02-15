package com.nastya.bookShop.config;

import com.nastya.bookShop.model.error.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Object> handleHttpClientError(
            HttpClientErrorException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        logger.error("Server error: {}", ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthentication(
            AuthenticationException ex) {
        ApiError apiError = new ApiError(UNAUTHORIZED);
        apiError.setMessage(ex.getMessage());
        logger.error("Server error: {}", ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    protected ResponseEntity<Object> handleInternalAuthenticationService(
            InternalAuthenticationServiceException ex) {
        ApiError apiError = new ApiError(UNAUTHORIZED);
        apiError.setMessage("You are not registered!");
        logger.error("Server error: {}", ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntime(
            RuntimeException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        logger.error("Server error: {}", ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
