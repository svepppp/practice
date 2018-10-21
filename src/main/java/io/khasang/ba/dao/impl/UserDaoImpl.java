package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.UserDao;
import io.khasang.ba.entity.User;

public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    public UserDaoImpl(Class<User> entityClass) {
        super(entityClass);
    }
}
