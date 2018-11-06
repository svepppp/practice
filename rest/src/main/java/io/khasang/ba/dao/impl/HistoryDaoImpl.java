package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.HistoryDao;
import io.khasang.ba.entity.History;

public class HistoryDaoImpl extends BasicDaoImpl<History> implements HistoryDao {
    public HistoryDaoImpl(Class<History> entityClass) {
        super(entityClass);
    }
}
