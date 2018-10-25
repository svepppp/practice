package io.khasang.ba.service.impl;

import io.khasang.ba.dao.TicketDao;
import io.khasang.ba.entity.Ticket;
import io.khasang.ba.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketDao ticketDao;

    @Override
    public Ticket addTicket(Ticket ticket) {
        return ticketDao.add(ticket);
    }

    @Override
    public Ticket getTicketById(long id) {
        return ticketDao.getById(id);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketDao.getAll();
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketDao.update(ticket);
    }

    @Override
    public Ticket deleteTicket(long id) {
        return ticketDao.delete(getTicketById(id));
    }
}