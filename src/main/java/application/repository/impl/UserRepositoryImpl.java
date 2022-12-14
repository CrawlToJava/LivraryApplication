package application.repository.impl;

import application.config.DataBase;
import application.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl extends JpaRepositoryImpl<User, Long> {

    public UserRepositoryImpl(DataBase dataBase) {
        super(dataBase);
        setClazz(User.class);
    }

    @Override
    public List<User> findAll() {
        DataBase dataBase = new DataBase();
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        List<User> listOfUsers = session.createQuery("from User u join fetch u.book", User.class).getResultList();
        transaction.commit();
        return listOfUsers;
    }
}
