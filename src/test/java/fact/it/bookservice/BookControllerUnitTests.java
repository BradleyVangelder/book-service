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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Autowired
    private BookRepository bookRepository;

    private Book book1 = new Book("Book1", "Action","ISBN1");
    private Book book2 = new Book("Book2","Action" ,"ISBN2");

    private List<Book> allBooks = Arrays.asList(book1, book2);
    @BeforeEach
    public void beforeAllTests() {
        bookRepository.deleteAll();
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
    private ObjectMapper mapper = new ObjectMapper();
// Testen kunnen niet uitgevoerd worden omdat de docker image van neo4j problemen geeft.
//    @Test
//    public void givenBook_whenGetBookByID_thenReturnJsonBook() throws Exception {
//
//        given(bookRepository.findBookById("1")).willReturn(book1);
//
//        mockMvc.perform(get("/quote/{id}",1))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.book",is("Book1")))
//                .andExpect(jsonPath("$.isbn",is("ISBN1")))
//                .andExpect(jsonPath("$.category",is("Action")));
//    }
//
//    @Test
//    public void givenBook_whenGetBookByISBN_thenReturnJsonBook() throws Exception {
//
//        given(bookRepository.findBookByISBN("ISBN1")).willReturn(book1);
//
//        mockMvc.perform(get("/book/{ISBN}","ISBN1"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].title",is("Book1")))
//                .andExpect(jsonPath("$[0].category",is("Action")))
//                .andExpect(jsonPath("$[0].isbn",is("ISBN1")));
//    }
//
//    @Test
//    public void givenBooks_whenGetBooksByCategory_thenReturnJsonBooks() throws Exception {
//
//        given(bookRepository.findBookByCategory("Book")).willReturn(allBooks);
//
//        mockMvc.perform(get("/book/category/{category}","Action"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].title",is("Book1")))
//                .andExpect(jsonPath("$[0].isbn",is("ISBN1")))
//                .andExpect(jsonPath("$[0].category",is("Action")))
//                .andExpect(jsonPath("$[1].title",is("Book2")))
//                .andExpect(jsonPath("$[1].category",is("Action")))
//                .andExpect(jsonPath("$[1].isbn",is("ISBN2")));
//    }
//    @Test
//    public void whenPostBook_thenReturnJsonBook() throws Exception{
//        mockMvc.perform(post("/book")
//                .content(mapper.writeValueAsString(book1))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.isbn",is("ISBN1")))
//                .andExpect(jsonPath("$.title",is("Book1")))
//                .andExpect(jsonPath("$.category",is("Action")));
//
//    }
//
//    @Test
//    public void givenBook_whenPutBook_thenReturnJsonBook() throws Exception{
//        given(bookRepository.findBookById("1")).willReturn(book1);
//
//        Book updatedBook = new Book("nieuwe boek","action","ISBN1");
//
//        mockMvc.perform(put("/book/{id}",1)
//                .content(mapper.writeValueAsString(updatedBook))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.isbn",is("ISBN1")))
//                .andExpect(jsonPath("$.title",is("nieuwe boek")))
//                .andExpect(jsonPath("$.category",is("action")));
//
//    }
//
//    @Test
//    public void givenBook_whenDeleteBook_thenStatusOk() throws Exception{
//        given(bookRepository.findBookById("1")).willReturn(book1);
//
//        mockMvc.perform(delete("/book/{id}",1)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
}
