package fact.it.bookservice.repository;

import fact.it.bookservice.model.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface BookRepository extends Neo4jRepository<Book,Long> {
    Book findBookByISBN(String ISBN);
    List<Book> findBookByCategory(String category);
    Book findBookByTitle(String title);
    Book findBookById(Long Id);
}
