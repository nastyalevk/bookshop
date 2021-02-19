package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.user.UserDto;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    UserDto findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

    void saveUser(UserDto userDto) throws UnsupportedEncodingException, MessagingException;

    void updateUser(UserDto userDto);

    ResponseEntity findAll(int page, int size);

    UserDto getOne(Integer id);

    void updateUserRoles(String[] roles, Integer id, String message);
}
