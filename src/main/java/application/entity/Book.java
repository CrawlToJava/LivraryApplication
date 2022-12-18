package application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Entity
@Table(name = "library_books")
@Setter
@Getter
@ToString
public class Book {
    public Book(String title, String author, int yearOfIssue, BookStatus bookStatus) {
        this.yearOfIssue = yearOfIssue;
        this.author = author;
        this.title = title;
        this.bookStatus = bookStatus;
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
    private String title;
    @Size(min = 2, max = 50, message = "Author name should be between 2 and 50 characters")
    @NotEmpty(message = "Author name shouldn`t be empty")
    @Column(name = "author")
    private String author;

    @Min(value = 0, message = "Year of issue must not be be a negative number")
    @Max(value = 2040)
    @Column(name = "year_of_issue")
    private int yearOfIssue;

    @Column(name = "book_status")
    @NotNull
    private BookStatus bookStatus;
}
