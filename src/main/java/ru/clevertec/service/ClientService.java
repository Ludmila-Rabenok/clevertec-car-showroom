package ru.clevertec.service;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Client findById(Long id);

    Client save(Client client);

    boolean remove(Client client);

    Client update(Client client);

    void buyCar(Car car, Client client);
}
