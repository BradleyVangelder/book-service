package fact.it.bookservice.controller;

import fact.it.bookservice.model.Book;
import fact.it.bookservice.repository.BookRepository;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@RestController

@RequestMapping("/book")
public class BookController {

    private BookRepository bookRepository;
    public BookController(BookRepository bookRepository){

        this.bookRepository = bookRepository;
    }
    @PostConstruct
    public void fillDB(){
        if( bookRepository.count()==0){
            bookRepository.save(new Book("Zero to one","Finance", "687468434568"));
            bookRepository.save(new Book("The LEAN startup", "Finance", "687468434578"));
        }
    }
    @GetMapping("/")
    public List<Book> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books;
    }
    @GetMapping("/{ISBN}")
    public Book getBooksbyISBN(@PathVariable String ISBN){
        return bookRepository.findBookByISBN(ISBN);
    }

    @GetMapping("/category/{category}")
    public List<Book> getBooksByCategory(@PathVariable String category){
        return bookRepository.findBookByCategory(category);
    }

    @GetMapping("/guess/{bookTitleGuess}")
    public Book bookTitleGuesser(@PathVariable String bookTitleGuess){
        return bookRepository.findBookByTitleContainingIgnoreCase(bookTitleGuess);
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book){
        bookRepository.save(book);
        return book;
    }

    @GetMapping("/random")
    public Book giveRandomBook() {
        List<Book> books = bookRepository.findAll();
        Random rand = new Random();
        Book randomBook = books.get(rand.nextInt(books.size()));
        return randomBook;
    }

    @PutMapping("/{id}")
    public Book editBook(@PathVariable String id,@RequestBody Book book){
        Book b = bookRepository.findBookById(id);
        b.setTitle(book.getTitle());
        b.setISBN(book.getISBN());
        bookRepository.save(b);
        return b;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        bookRepository.deleteById(id);
    }
}
