package nastya.BookShop.service.api;

import nastya.BookShop.dto.userRoles.UserRolesDto;
import nastya.BookShop.model.UserRoles;

import java.util.List;

public interface UserRolesService {

    UserRoles saveUserRole(UserRolesDto userRoles);

    List<UserRolesDto> findAll();

    UserRolesDto findById(Integer id);

}
