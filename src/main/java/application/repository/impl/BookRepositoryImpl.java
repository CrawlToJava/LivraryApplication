package application.repository.impl;

import application.config.DataBase;
import application.entity.Book;
import application.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("bookRepository")
public class BookRepositoryImpl extends JpaRepositoryImpl<Book, Long> implements BookRepository {
    @Autowired
    public BookRepositoryImpl(DataBase dataBase) {
        super(dataBase);
        setClazz(Book.class);
    }
}
