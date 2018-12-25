package io.khasang.ba.dao;

import java.util.List;

public interface BasicDao<T> {

    /**
     * method for add entity
     *
     * @param entity = entity for adding
     * @return created entity
     */
    T add(T entity);

    /**
     * method for getting entity by specific id
     *
     * @param id - entity's id
     * @return entity by id
     */
    T getById(long id);

    /**
     * method gor getting all entity
     *
     * @return all entity
     */
    List<T> getAll();

    /**
     * method for update entity
     *
     * @param entity - entity's with updated params
     * @return updated entity
     */
    T update(T entity);

    /**
     * method for delete entity by id
     *
     * @param entity - entity's for delete
     * @return deleted entity
     */
    T delete(T entity);
}
