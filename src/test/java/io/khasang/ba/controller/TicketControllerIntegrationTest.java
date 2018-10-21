package io.khasang.ba.controller;

import io.khasang.ba.entity.Ticket;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class TicketControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/ticket";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get";
    private static final String ALL = "/all";
    private static final String UPDATE = "/update";
    private static final String DELETE = "/delete";

    @Test
    public void addTicket() {
        Ticket ticket = createTicket();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Ticket> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Ticket.class,
                ticket.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void checkGetAllTickets() {
        createTicket();
        createTicket();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Ticket>> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ticket>>() {
                }
        );

        List<Ticket> tickets = responseEntity.getBody();
        assertNotNull(tickets.get(0));
        assertNotNull(tickets.get(1));
    }

    @Test
    public void checkUpdateTicket() {
        Ticket ticket = createTicket();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Ticket> entity = new HttpEntity<>(ticket, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Ticket> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                Ticket.class,
                ticket.getId()
        );
        Ticket updatedTicket = updateTicket(ticket);
        assertNotNull(ticket);
        assertNotNull(updatedTicket);
        assertEquals("OK", ticket, updatedTicket);
    }

    @Test
    public void checkDeleteTicket() {
        Ticket ticket = createTicket();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Ticket> entity = new HttpEntity<>(ticket, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Ticket> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                entity,
                Ticket.class,
                ticket.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    private Ticket updateTicket(Ticket ticket) {
        ticket.setSeries("updated");
        ticket.setDateOfIssue(LocalDate.of(2018, 12, 31));
        return ticket;
    }

    private Ticket createTicket() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Ticket ticket = prefillTicket();
        HttpEntity<Ticket> entity = new HttpEntity<>(ticket, headers);
        RestTemplate restTemplate = new RestTemplate();
        Ticket createdTicket = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Ticket.class
        ).getBody();

        assertNotNull(createdTicket);
        assertNotNull(createdTicket.getSeries());
        return createdTicket;
    }

    private Ticket prefillTicket() {
        Ticket ticket = new Ticket();
        ticket.setDateOfIssue(LocalDate.of(2018, 10, 21));
        ticket.setSeries("notarius");
        return ticket;
    }
}