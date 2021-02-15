package nastya.BookShop.repository;

import nastya.BookShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String userName);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
