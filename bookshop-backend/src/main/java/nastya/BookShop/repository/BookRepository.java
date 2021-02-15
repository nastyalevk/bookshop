package nastya.BookShop.repository;

import nastya.BookShop.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findAll(Pageable pageable);

    Page<Book> findByBookNameContaining(String bookName, Pageable pageable);

    Boolean existsBookById(Integer id);
}
