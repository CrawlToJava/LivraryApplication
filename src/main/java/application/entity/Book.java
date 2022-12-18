package application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "library_books")
@Setter
@Getter
@ToString
public class Book {
    public Book(String title, String author, int yearOfIssue) {
        this.title = title;
        this.author = author;
        this.yearOfIssue = yearOfIssue;
    }

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name = "order_generator", sequenceName = "books_seq", allocationSize = 1)
    private Long id;

    @Size(min = 1, max = 50, message = "The title of a book should be between 2 and 50 characters")
    @NotEmpty(message = "Name must not be empty")
    @Column(name = "title")
    @Value("${book.name}")
    private String title;
    @Size(min = 2, max = 50, message = "Author name should be between 2 and 50 characters")
    @NotEmpty(message = "Author name shouldn`t be empty")
    @Column(name = "author")
    @Value("${book.author}")
    private String author;

    @Min(value = 0, message = "Year of issue must not be be a negative number")
    @Max(value = 2040)
    @Column(name = "year_of_issue")
    @Value("${book.yearOfIssue}")
    private int yearOfIssue;
}
