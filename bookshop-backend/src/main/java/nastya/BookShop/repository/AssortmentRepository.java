package nastya.BookShop.repository;

import nastya.BookShop.model.Assortment;
import nastya.BookShop.model.AssortmentId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssortmentRepository extends JpaRepository<Assortment, Integer> {

    List<Assortment> findByAssortmentIdBookIdAndAndAssortmentIdShopClassificationName(Integer id, String classification);

    Assortment findByAssortmentIdBookIdAndAssortmentIdShopId(Integer bookId, Integer shopId);

    Boolean existsByAssortmentId(AssortmentId assortmentId);

    void deleteByAssortmentId(AssortmentId assortmentId);

    Assortment getAssortmentByAssortmentId(AssortmentId assortmentId);

    Page<Assortment> findAllByAssortmentIdShopId(Integer shopId, Pageable pageable);

    Integer getQuantityByAssortmentId(AssortmentId assortmentId);
}
