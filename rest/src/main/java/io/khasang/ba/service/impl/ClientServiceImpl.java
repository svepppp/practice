package io.khasang.ba.service.impl;

import io.khasang.ba.dao.ClientDao;
import io.khasang.ba.entity.Client;
import io.khasang.ba.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public Client addClient(Client client) {
        return clientDao.add(client);
    }

    @Override
    public Client getClientById(long id) {
        return clientDao.getById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAll();
    }

    @Override
    public Client updateClient(Client client) {
        return clientDao.update(client);
    }

    @Override
    public Client deleteClient(long id) {
        return clientDao.delete(getClientById(id));
    }
}
