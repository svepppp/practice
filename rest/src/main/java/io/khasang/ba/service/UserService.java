package io.khasang.ba.service;

import io.khasang.ba.entity.User;

import java.util.List;

public interface UserService {
    /**
     * method for add user
     *
     * @param user = user for adding
     * @return created user
     */
    User addUser(User user);

    /**
     * method for getting user by specific id
     *
     * @param id - user's id
     * @return user by id
     */
    User getUserById(long id);

    /**
     * method for getting all users
     *
     * @return all users
     */
    List<User> getAllUsers();

    /**
     * method for update user
     *
     * @param user - user's with updated params
     * @return updated user
     */
    User updateUser(User user);

    /**
     * method for delete user by id
     *
     * @param id - user's id for delete
     * @return deleted user
     */
    User deleteUser(long id);
}
