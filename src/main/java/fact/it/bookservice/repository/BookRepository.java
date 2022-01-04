package fact.it.bookservice.repository;

import fact.it.bookservice.model.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends Neo4jRepository<Book,Long> {
    Book findBookByISBN(String ISBN);
    List<Book> findBookByCategory(String category);
    @Query("select b from Book b where lower(b.title) = lower(:titleToFind)")
    Book findbyTitleFree(@Param("titleToFind") String title);
    Book findBookByTitleContainingIgnoreCase(String title);
    Book findBookById(Long Id);
}