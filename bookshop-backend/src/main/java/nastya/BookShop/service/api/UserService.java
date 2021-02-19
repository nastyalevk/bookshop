package nastya.BookShop.service.api;

import nastya.BookShop.dto.response.PageResponse;
import nastya.BookShop.dto.user.UserDto;

import java.util.List;

public interface UserService {

    UserDto findById(Integer id);

    PageResponse<UserDto> findAll(int page, int size);

    void saveUser(UserDto user);

    void updateUser(UserDto user, String username);

    UserDto findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    void updateUserRoles(String[] roles, Integer id, String adminUsername, String message);

}
