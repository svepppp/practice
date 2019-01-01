package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.ClientDao;
import io.khasang.ba.entity.Client;

public class ClientDaoImpl extends BasicDaoImpl<Client> implements ClientDao {
    public ClientDaoImpl(Class<Client> entityClass) {
        super(entityClass);
    }
}