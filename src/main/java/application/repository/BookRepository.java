package application.repository;

import application.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    void save(Book book);

    void delete(Long id);

    void update(Book book);

    Optional<Book> findById(Long id);

    List<Book> findAll();
}
