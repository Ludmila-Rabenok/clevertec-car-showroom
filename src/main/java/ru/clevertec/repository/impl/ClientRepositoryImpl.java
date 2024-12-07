package ru.clevertec.repository.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.exception.RepositoryException;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.util.HibernateUtil;

import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {
    private final Session session = HibernateUtil.getSession();

    @Override
    public List<Client> findAll() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<Client> clients = session.createQuery("SELECT c FROM Client c", Client.class)
                    .getResultList();
            transaction.commit();
            return clients;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Client findById(Long id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            transaction.commit();
            return client;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Client save(Client client) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
        return client;
    }

    @Override
    public boolean remove(Client client) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Client findClient = session.find(Client.class, client.getId());
            if (findClient == null) {
                transaction.rollback();
                return false;
            }
            session.remove(client);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Client update(Client client) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Client mergeClient = session.merge(client);
            transaction.commit();
            return mergeClient;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void buyCar(Car car, Client client) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Client foundClient = session.find(Client.class, client.getId());
            Car foundCar = session.find(Car.class, car.getId());
            if (foundClient != null && foundCar != null) {
                foundClient.addCarToClient(foundCar);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
    }
}