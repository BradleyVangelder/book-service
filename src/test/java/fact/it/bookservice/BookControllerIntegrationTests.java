package fact.it.bookservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.bookservice.model.Book;
import fact.it.bookservice.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    private Book book1 = new Book("Book1","Action","ISBN1");
    private Book book2 = new Book("Book2", "Action","ISBN2");

    @BeforeEach
    public void beforeAllTests() {
        bookRepository.deleteAll();
        bookRepository.save(book1);
        bookRepository.save(book2);
    }

    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        bookRepository.deleteAll();
    }
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenBook_whenGetBookByISBN_thenReturnJsonBook() throws Exception {

        mockMvc.perform(get("/book/{ISBN}","ISBN1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",is("Book1")))
                .andExpect(jsonPath("$.category",is("Action")))
                .andExpect(jsonPath("$.isbn",is("ISBN1")));
    }

    @Test
    public void givenBooks_whenGetBooksByCategory_thenReturnJsonBooks() throws Exception {

        mockMvc.perform(get("/book/category/{category}","Action"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title",is("Book1")))
                .andExpect(jsonPath("$[0].category",is("Action")))
                .andExpect(jsonPath("$[0].isbn",is("ISBN1")))
                .andExpect(jsonPath("$[1].title",is("Book2")))
                .andExpect(jsonPath("$[1].category",is("Action")))
                .andExpect(jsonPath("$[1].isbn",is("ISBN2")));
    }
}
