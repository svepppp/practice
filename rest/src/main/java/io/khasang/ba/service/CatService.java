package io.khasang.ba.service;

import io.khasang.ba.entity.Cat;

import java.util.List;

public interface CatService {

    /**
     * method for add cat
     *
     * @param cat = cat for adding
     * @return created cat
     */
    Cat addCat(Cat cat);

    /**
     * method for getting cat by specific id
     *
     * @param id - cat's id
     * @return cat by id
     */
    Cat getCatById(long id);

    /**
     * method gor getting all cats
     *
     * @return all cats
     */
    List<Cat> getAllCats();

    /**
     * method for update cat
     *
     * @param cat - cat's with updated params
     * @return updated cat
     */
    Cat updateCat(Cat cat);

    /**
     * method for delete cat by id
     *
     * @param id - cat's id for delete
     * @return deleted cat
     */
    Cat deleteCat(long id);
}
