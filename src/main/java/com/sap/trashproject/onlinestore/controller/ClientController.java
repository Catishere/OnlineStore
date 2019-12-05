package com.sap.trashproject.onlinestore.controller;

import com.sap.trashproject.onlinestore.entity.Client;
import com.sap.trashproject.onlinestore.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> getClients() {
        return clientService.findAll();
    }

    @PostMapping("/clients/register")
    public Client registerClient(@RequestBody Client client) {
        System.out.println(client.getFirstName());
        clientService.save(client);
        return client;
    }

    @PostMapping("/clients/authenticate")
    public Client login(@RequestBody Client client) {
        return client;
    }
}