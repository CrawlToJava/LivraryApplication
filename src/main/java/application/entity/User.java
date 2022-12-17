package application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "library_users")
@Setter
@Getter
public class User {
    public User(String lastName, String firstName, String secondName, String email, Book book) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.book = book;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name = "order_generator", sequenceName = "users_seq", allocationSize = 1)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
}
