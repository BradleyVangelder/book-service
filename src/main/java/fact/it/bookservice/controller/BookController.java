package fact.it.bookservice.controller;

import fact.it.bookservice.model.Book;
import fact.it.bookservice.repository.BookRepository;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController


public class BookController {
    private final Driver driver;
    private BookRepository bookRepository;
    public BookController(Driver driver,BookRepository bookRepository){
        this.driver = driver;
        this.bookRepository = bookRepository;
    }
    @PostMapping("/book")
        public Book addBook(@RequestBody Book book){
        bookRepository.save(book);
        return book;
    }


    @GetMapping("/")
    public List<Book> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books;

    }
}
