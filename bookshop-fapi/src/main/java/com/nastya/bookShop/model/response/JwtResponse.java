package com.nastya.bookShop.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String username;
    private String email;
    private List<String> roles;
    private Boolean activated;
    private Boolean isEnabled;

}
