package com.scream.testtask.service;

import com.scream.testtask.models.Client;
import com.scream.testtask.repositories.ClientRepository;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientService{
    private final ClientRepository clientRepository;
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client getClient(@NotNull String id) {
        return clientRepository.findById(id).orElseThrow();
    }

    @Transactional
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public void saveClient(@NotNull Client client) {
            clientRepository.save(client);
    }

    @Transactional
    public void deleteClient(@NotNull String id) {
        clientRepository.deleteById(id);
    }
}
