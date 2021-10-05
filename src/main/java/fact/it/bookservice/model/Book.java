package fact.it.bookservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Book {
    @Id
    private long id;
    private String title;
    private String ISBN;
    private String category;

    public Book(){}

    public Book(String title,String category,String ISBN){
        setISBN(ISBN);
        setCategory(category);
        setTitle(title);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
