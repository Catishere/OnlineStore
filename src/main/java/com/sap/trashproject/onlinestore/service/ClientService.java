package com.sap.trashproject.onlinestore.service;

import com.sap.trashproject.onlinestore.repository.ClientRepository;
import com.sap.trashproject.onlinestore.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRep;

    public ClientService() {
        this.clientRep = new ClientRepository();
    }

    public List<Client> findAll() {
        clientRep.openCurrentSessionWithTransaction();
        List<Client> clients =  clientRep.findAll();
        clientRep.closeCurrentSessionWithTransaction();
        return clients;
    }

    public Long count() {
        clientRep.openCurrentSessionWithTransaction();
        Long count = clientRep.count();
        clientRep.closeCurrentSessionWithTransaction();
        return count;
    }

    public void deleteById(Long clientId) {
        clientRep.openCurrentSessionWithTransaction();
        clientRep.deleteById(clientId);
        clientRep.closeCurrentSessionWithTransaction();
    }
    public void save(Client client) {
        clientRep.openCurrentSessionWithTransaction();
        clientRep.save(client);
        clientRep.closeCurrentSessionWithTransaction();
    }
}
