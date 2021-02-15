package nastya.BookShop.repository;

import nastya.BookShop.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findByUserUsername(String username, Pageable pageable);

    Page<Order> findByShopId(Integer shopId, Pageable pageable);

}
