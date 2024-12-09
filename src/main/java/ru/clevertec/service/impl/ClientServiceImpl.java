package ru.clevertec.service.impl;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public boolean remove(Client client) {
        return clientRepository.remove(client);
    }

    @Override
    public Client update(Client client) {
        return clientRepository.update(client);
    }

    @Override
    public void buyCar(Car car, Client client) {
        clientRepository.buyCar(car, client);
    }
}
