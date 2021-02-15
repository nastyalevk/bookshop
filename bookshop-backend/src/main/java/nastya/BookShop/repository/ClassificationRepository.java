package nastya.BookShop.repository;

import nastya.BookShop.model.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationRepository extends JpaRepository<Classification, Integer> {

    Classification getClassificationById(Integer id);

    Classification getClassificationByNameAndAndClassificationName(String name, String parentName);

}
