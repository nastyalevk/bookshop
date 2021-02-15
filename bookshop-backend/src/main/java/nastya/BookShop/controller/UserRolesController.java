package nastya.BookShop.controller;

import nastya.BookShop.dto.userRoles.UserRolesDto;
import nastya.BookShop.model.UserRoles;
import nastya.BookShop.service.api.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/role")
public class UserRolesController {

    private final UserRolesService userRolesService;

    @Autowired
    public UserRolesController(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserRoles> setRole(@RequestBody UserRolesDto userRolesDto) {
        return new ResponseEntity<>(userRolesService.saveUserRole(userRolesDto), HttpStatus.OK);
    }
}
