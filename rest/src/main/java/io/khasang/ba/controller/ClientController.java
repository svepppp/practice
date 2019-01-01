package io.khasang.ba.controller;

import io.khasang.ba.entity.Client;
import io.khasang.ba.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Client addClient(@RequestBody Client client) {
        clientService.addClient(client);
        return client;
    }

    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public Client getClientById(@PathVariable(value = "id") long id) {
        return clientService.getClientById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public Client updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public Client deleteClient(@PathVariable(value = "id") long id) {
        return clientService.deleteClient(id);
    }
}
