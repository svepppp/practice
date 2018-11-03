package io.khasang.ba.service.impl;

import io.khasang.ba.dao.UserDao;
import io.khasang.ba.entity.User;
import io.khasang.ba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User addUser(User user) {
        return userDao.add(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public User deleteUser(long id) {
        return userDao.delete(getUserById(id));
    }
}
