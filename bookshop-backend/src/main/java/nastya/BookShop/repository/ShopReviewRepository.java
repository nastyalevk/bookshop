package nastya.BookShop.repository;

import nastya.BookShop.model.ShopReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopReviewRepository extends JpaRepository<ShopReview, Integer> {

    List<ShopReview> findAllByShopIdAndApproved(Integer shopId, Boolean approved);

    List<ShopReview> findAllByApproved(Boolean approved);

}
