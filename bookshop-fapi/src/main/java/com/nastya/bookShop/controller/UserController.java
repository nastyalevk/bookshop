package com.nastya.bookShop.controller;

import com.nastya.bookShop.model.user.UserDto;
import com.nastya.bookShop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody UserDto userDto)
            throws UnsupportedEncodingException, MessagingException {
        userService.saveUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/update-roles/")
    public ResponseEntity<HttpStatus> updateRoles(@RequestParam String[] roles,
                                                  @RequestParam Integer id,
                                                  @RequestParam String message) {
        userService.updateUserRoles(roles, id, message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOne(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/find/username/{username}")
    public ResponseEntity<UserDto> findByUserName(@PathVariable("username") String userName) {
        return new ResponseEntity<>(userService.findByUserName(userName), HttpStatus.OK);
    }

}
