package nl.adacademie.drankbuddy.persistence.interfaces;

import java.util.List;
import java.util.Optional;

/**
 * Een basisinterface voor alle DAO's
 * <p>
 * @param <T> De entiteit die door deze DAO wordt beheerd/gebruikt.
 */
public interface IGenericDao<T> {

    Optional<T> findById(int id);

    List<T> findAll();

    T save(T entity);

    void update(T entity);

    void deleteById(int id);

    void delete(T entity);

}
