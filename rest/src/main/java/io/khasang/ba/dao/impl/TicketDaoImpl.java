package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.TicketDao;
import io.khasang.ba.entity.Ticket;

public class TicketDaoImpl extends BasicDaoImpl<Ticket> implements TicketDao {
    public TicketDaoImpl(Class<Ticket> entityClass) {
        super(entityClass);
    }
}