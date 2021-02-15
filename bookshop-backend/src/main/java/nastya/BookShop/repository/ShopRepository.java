package nastya.BookShop.repository;

import nastya.BookShop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    List<Shop> findAllByUserUsername(String username);

    Shop getShopById(Integer id);

}
