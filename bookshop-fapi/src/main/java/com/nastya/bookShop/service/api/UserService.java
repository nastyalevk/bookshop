package com.nastya.bookShop.service.api;

import com.nastya.bookShop.model.user.UserDto;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    UserDto findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

    void saveUser(UserDto userDto) throws UnsupportedEncodingException, MessagingException;

    void updateUser(UserDto userDto);

    List<UserDto> findAll();

    UserDto getOne(Integer id);

    void updateUserRoles(String[] roles, Integer id, String message);
}
