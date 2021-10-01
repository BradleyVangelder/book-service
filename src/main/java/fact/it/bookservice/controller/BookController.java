package fact.it.bookservice.controller;

import fact.it.bookservice.model.Book;
import fact.it.bookservice.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @PostConstruct
    public void fillDB(){
        if( bookRepository.count()==0){
            bookRepository.save(new Book("Harry Potter","Finance", "687468434567"));
            bookRepository.save(new Book("Harry Potter 2", "Finance", "687468434567"));
        }

        //System.out.println("Reviews test: " + quoteRepository.findReviewsByISBN("687468435454").size());
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
