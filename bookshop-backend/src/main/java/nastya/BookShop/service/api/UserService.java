package nastya.BookShop.service.api;

import nastya.BookShop.dto.user.UserDto;
import nastya.BookShop.model.User;

import java.util.List;

public interface UserService {

    UserDto findById(Integer id);

    List<UserDto> findAll();

    User saveUser(UserDto user);

    User updateUser(UserDto user, String username);

    UserDto findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    void updateUserRoles(String[] roles, Integer id, String adminUsername, String message);

}
