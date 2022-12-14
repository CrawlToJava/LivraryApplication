package application.repository.impl;

import application.config.DataBase;
import application.entity.Book;
import org.springframework.stereotype.Repository;

@Repository("bookRepository")
public class BookRepositoryImpl extends JpaRepositoryImpl<Book, Long> {
    public BookRepositoryImpl(DataBase dataBase) {
        super(dataBase);
        setClazz(Book.class);
    }
}
