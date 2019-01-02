package io.khasang.ba.service;

import io.khasang.ba.entity.Client;

import java.util.List;

public interface ClientService {
    /**
     * method for add client
     *
     * @param client = client for adding
     * @return created client
     */
    Client addClient(Client client);

    /**
     * method for getting client by specific id
     *
     * @param id - client's id
     * @return client by id
     */
    Client getClientById(long id);

    /**
     * method for getting all clients
     *
     * @return all clients
     */
    List<Client> getAllClients();

    /**
     * method for update client
     *
     * @param client - client's with updated params
     * @return updated client
     */
    Client updateClient(Client client);

    /**
     * method for delete client by id
     *
     * @param id - client's id for delete
     * @return deleted client
     */
    Client deleteClient(long id);
}
