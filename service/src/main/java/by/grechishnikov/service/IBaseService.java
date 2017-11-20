package by.grechishnikov.service;

import by.grechishnikov.entity.Bean;

import java.util.List;

/**
 * Base service
 *
 * @param <T> - entity
 */
public interface IBaseService<T extends Bean> {
    /**
     * Save or update Bean in DB
     *
     * @param t - bean
     */
    void saveOrUpdate(T t);

    /**
     * Get Bean from DB by id
     *
     * @param id - id in DB
     * @return - Bean
     */
    T get(int id);

    /**
     * Delete Bean in DB
     *
     * @param t - Bean
     */
    void delete(T t);

    /**
     * Get all beans from DB
     *
     * @return - list
     */
    List<T> getAll();
}
