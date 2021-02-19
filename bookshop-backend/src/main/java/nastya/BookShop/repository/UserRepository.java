package nastya.BookShop.repository;

import nastya.BookShop.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String userName);

    Page<User> findAll(Pageable pageable);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
