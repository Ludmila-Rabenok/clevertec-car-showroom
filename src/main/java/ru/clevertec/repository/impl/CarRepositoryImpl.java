package ru.clevertec.repository.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.exception.RepositoryException;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.util.HibernateUtil;

import java.util.List;

public class CarRepositoryImpl implements CarRepository {

    private final Session session = HibernateUtil.getSession();

    @Override
    public List<Car> findAll() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            final List<Car> cars = session.createQuery
                            ("SELECT c FROM Car c", Car.class)
                    .getResultList();
            transaction.commit();
            return cars;
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
    public Car findById(Long id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Car car = session.get(Car.class, id);
            transaction.commit();
            return car;
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
    public Car save(Car car) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(car);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
        return car;
    }

    @Override
    public boolean remove(Car car) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Car findCar = session.find(Car.class, car.getId());
            if (findCar == null) {
                transaction.rollback();
                return false;
            }
            session.remove(car);
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
    public Car update(Car car) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Car mergeCar = session.merge(car);
            transaction.commit();
            return mergeCar;
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
    public void assignCarToShowroom(Car car, CarShowroom carShowroom) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CarShowroom foundCarShowroom = session.find(CarShowroom.class, carShowroom.getId());
            Car foundCar = session.find(Car.class, car.getId());
            if (foundCarShowroom != null && foundCar != null) {
                foundCarShowroom.addCarToShowroom(foundCar);
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

    @Override
    public List<Car> findCarsByFilters(String brand, String category, Integer year,
                                       Double minPrice, Double maxPrice) {
        Transaction transaction = null;
        List<Car> cars;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> root = carCriteriaQuery.from(Car.class);

            Predicate predicate = criteriaBuilder.conjunction();

            if (brand != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("brand"), brand));
            }
            if (year != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("year"), year));
            }
            if (category != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category").get("name"), category));
            }
            if (minPrice != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.ge(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.le(root.get("price"), maxPrice));
            }
            carCriteriaQuery.where(predicate);
            cars = session.createQuery(carCriteriaQuery).getResultList();
            transaction.commit();
            return cars;
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
    public List<Car> findCarsSortedByPrice(String typeSorting) {
        Transaction transaction = null;
        List<Car> cars;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> root = carCriteriaQuery.from(Car.class);
            if (typeSorting != null) {
                if (typeSorting.equalsIgnoreCase("ASC")) {
                    carCriteriaQuery.orderBy(criteriaBuilder.asc(root.get("price")));
                }
                if (typeSorting.equalsIgnoreCase("DESC")) {
                    carCriteriaQuery.orderBy(criteriaBuilder.desc(root.get("price")));
                }
            }
            cars = session.createQuery(carCriteriaQuery).getResultList();
            transaction.commit();
            return cars;
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
    public List<Car> findCarsWithPagination(int page, int pageSize) {
        Transaction transaction = null;
        List<Car> cars;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> root = carCriteriaQuery.from(Car.class);

            cars = session.createQuery(carCriteriaQuery)
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
            transaction.commit();
            return cars;
        } catch (
                HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
    }
}