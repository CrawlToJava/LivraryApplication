package application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "library_books")
@Setter
@Getter
@ToString
public class Book {
    public Book(String name, String author, int yearOfIssue) {
        this.name = name;
        this.author = author;
        this.yearOfIssue = yearOfIssue;
    }

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name = "order_generator", sequenceName = "books_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    @Value("${book.name}")
    private String name;

    @Column(name = "author")
    @Value("${book.author}")
    private String author;

    @Column(name = "year_of_issue")
    @Value("${book.yearOfIssue}")
    private int yearOfIssue;
}
