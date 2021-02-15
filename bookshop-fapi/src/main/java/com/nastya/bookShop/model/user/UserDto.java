package com.nastya.bookShop.model.user;

import com.nastya.bookShop.model.role.RoleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Set<RoleDto> roles;
    private Boolean activated;
    private Boolean isEnabled;
    private String message;

}
