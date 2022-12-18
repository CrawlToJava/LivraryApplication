package application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

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
        this.userStatus = UserStatus.FRIENDLY;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name = "order_generator", sequenceName = "users_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "Last name must not be empty")
    @Size(min = 2, max = 30, message = "Valid character value: 30")
    @Column(name = "last_name")
    private String lastName;
    @NotEmpty(message = "First name must not be empty")
    @Size(min = 2, max = 30, message = "Valid character value: 30")
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty(message = "Second name must not be empty")
    @Size(min = 2, max = 30, message = "Valid character value: 30")
    @Column(name = "second_name")
    private String secondName;

    @Min(value = 0, message = "Age must not be be a negative number")
    @Max(value = 120)
    @Column(name = "age")
    private Integer age;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Invalid email value")
    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @Column(name = "user_status")
    @NotNull
    private UserStatus userStatus;
}
