package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.OnlineQueueDao;
import io.khasang.ba.entity.OnlineQueue;

public class OnlineQueueDaoImpl extends BasicDaoImpl<OnlineQueue> implements OnlineQueueDao {

    public OnlineQueueDaoImpl(Class<OnlineQueue> entityClass) {
        super(entityClass);
    }
}
