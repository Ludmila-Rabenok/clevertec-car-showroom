package ru.clevertec.repository.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.exception.RepositoryException;
import ru.clevertec.repository.CarShowroomRepository;
import ru.clevertec.util.HibernateUtil;

import java.util.List;

public class CarShowroomRepositoryImpl implements CarShowroomRepository {
    private final Session session = HibernateUtil.getSession();

    @Override
    public List<CarShowroom> findAll() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<CarShowroom> carShowrooms = session.createQuery("SELECT c FROM CarShowroom c", CarShowroom.class)
                    .getResultList();
            transaction.commit();
            return carShowrooms;
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
    public CarShowroom findById(Long id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CarShowroom carShowroom = session.get(CarShowroom.class, id);
            transaction.commit();
            return carShowroom;
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
    public CarShowroom save(CarShowroom carShowroom) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(carShowroom);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
        return carShowroom;
    }

    @Override
    public boolean remove(CarShowroom carShowroom) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CarShowroom findCarShowroom = session.find(CarShowroom.class, carShowroom.getId());
            if (findCarShowroom == null) {
                transaction.rollback();
                return false;
            }
            session.remove(carShowroom);
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
    public CarShowroom update(CarShowroom carShowroom) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CarShowroom mergeCarShowroom = session.merge(carShowroom);
            transaction.commit();
            return mergeCarShowroom;
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
    public List<Car> findCarsFromShowroomByIdWithEntityGraph(Long showroomId) {
        EntityGraph<?> entityGraph;
        EntityManager entityManager = null;
        try {
            entityManager = session.getEntityManagerFactory().createEntityManager();
            entityGraph = entityManager.getEntityGraph("CarShowroom.withCarAndCategory");
            return session.createQuery("SELECT c.cars FROM CarShowroom c where c.id= :id", Car.class)
                    .setParameter("id", showroomId)
                    .setHint("javax.persistence.fetchgraph", entityGraph)
                    .getResultList();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        } finally {
            session.close();
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}