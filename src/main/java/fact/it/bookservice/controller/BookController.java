package fact.it.bookservice.controller;

import fact.it.bookservice.model.Book;
import fact.it.bookservice.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book")
     public List<Book> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books;

    }
    @GetMapping("/{ISBN}")
    public List<Book> getBooksbyISBN(@PathVariable String ISBN){
        return bookRepository.findBookByISBN(ISBN);
    }
    @GetMapping("/category/{category}")
    public List<Book> getBooksByCategory(@PathVariable String category){
        return bookRepository.findBookByCategory(category);
    }

    @PostMapping("/")
    public Book addBook(@RequestBody Book book){
        bookRepository.save(book);
        return book;
    }
}
