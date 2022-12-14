package application.repository;

import java.util.List;
import java.util.Optional;

public interface JpaRepository<T, ID> {
    void setClazz(Class<T> clazzToSet);

    void save(T t);

    void update(T t);

    void delete(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();
}
