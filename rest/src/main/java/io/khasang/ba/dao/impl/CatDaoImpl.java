package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.CatDao;
import io.khasang.ba.entity.Cat;

public class CatDaoImpl extends BasicDaoImpl<Cat> implements CatDao {
    public CatDaoImpl(Class<Cat> entityClass) {
        super(entityClass);
    }
}
