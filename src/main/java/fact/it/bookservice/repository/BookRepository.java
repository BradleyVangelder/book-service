package fact.it.bookservice.repository;

import fact.it.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findBookByISBN(String ISBN);
    List<Book> findBookByCategory(String category);
}
