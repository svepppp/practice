package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.RequestDao;
import io.khasang.ba.entity.Request;

public class RequestDaoImpl extends BasicDaoImpl<Request> implements RequestDao {
    public RequestDaoImpl(Class<Request> entityClass) {
        super(entityClass);
    }
}