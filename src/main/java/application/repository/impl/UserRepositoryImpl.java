package application.repository.impl;

import application.config.DataBase;
import application.entity.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public class UserRepositoryImpl extends JpaRepositoryImpl<User, Long> implements UserRepository {
    @Autowired
    public UserRepositoryImpl(DataBase dataBase) {
        super(dataBase);
        setClazz(User.class);
    }
}
