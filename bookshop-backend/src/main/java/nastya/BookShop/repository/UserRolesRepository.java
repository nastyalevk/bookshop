package nastya.BookShop.repository;

import nastya.BookShop.model.User;
import nastya.BookShop.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {

    List<UserRoles> findUserRolesByUserRolesIdUser(User user);

}
