package io.khasang.ba.service;

import io.khasang.ba.entity.Ticket;

import java.util.List;

public interface TicketService {

    /**
     * method for add new ticket
     *
     * @param ticket - ticket for add
     * @return created ticket
     */
    Ticket addTicket(Ticket ticket);

    /**
     * method for getting ticket by id
     *
     * @param id - ticket's id
     * @return - ticket by id
     */
    Ticket getTicketById(long id);

    /**
     * method for getting all tickets
     *
     * @return all tickets
     */
    List<Ticket> getAllTickets();

    /**
     * method for update ticket
     *
     * @param ticket - ticket with updated params
     * @return updated ticket
     */
    Ticket updateTicket(Ticket ticket);

    /**
     * method for delete ticket by id
     *
     * @param id - ticket's id for delete
     * @return deleted ticket
     */
    Ticket deleteTicket(long id);
}