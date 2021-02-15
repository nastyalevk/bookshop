package nastya.BookShop.service.api;

import nastya.BookShop.dto.role.RoleDto;
import nastya.BookShop.model.Role;

import java.util.List;

public interface RolesService {

    void saveRole(RoleDto role);

    List<RoleDto> findAll();

    List<Role> findByName(String name);

}
