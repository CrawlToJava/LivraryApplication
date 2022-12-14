package application.repository.impl;

import application.config.DataBase;
import application.exception.NoDataFoundException;
import application.repository.JpaRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public abstract class JpaRepositoryImpl<T, ID> implements JpaRepository<T, ID> {
    private Class<T> clazz;

    private final DataBase dataBase;

    public JpaRepositoryImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    @Override
    public Optional<T> findById(ID id) {
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        Optional<T> entity = Optional.ofNullable(dataBase.getSession().get(clazz, id));
        transaction.commit();
        session.close();
        return entity;
    }

    @Override
    public List<T> findAll() {
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        List<T> listOfEntity = session.createQuery("from " + clazz.getName()).getResultList();
        transaction.commit();
        return listOfEntity;
    }

    @Override
    public void save(T entity) {
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(T entity) {
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(ID id) {
        Transaction transaction;
        Session session = dataBase.getSession();
        transaction = session.beginTransaction();
        Optional<T> entity = Optional.ofNullable(session.get(clazz, id));
        session.remove(entity.orElseThrow(() -> new NoDataFoundException("Entity not found")));
        transaction.commit();
        session.close();
    }
}
