package com.sap.trashproject.onlinestore.repository;

import com.sap.trashproject.onlinestore.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository {
    List<Client> findAll();
    Long count();
    void deleteById(Long clientId);
    void save(Client client);
}
