package com.nastya.bookShop.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CredentialsException extends RuntimeException{

    private String message;

    public CredentialsException(String message){
        super(message);
        this.message = message;
    }
}
