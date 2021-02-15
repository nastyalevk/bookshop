package nastya.BookShop.repository;

import nastya.BookShop.dto.role.ERole;
import nastya.BookShop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepository extends JpaRepository<Role, Integer> {

    List<Role> findByRoleName(ERole name);

    Role getRoleByRoleName(String roleName);

}
