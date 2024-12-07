package ru.clevertec.repository.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;
import ru.clevertec.exception.RepositoryException;
import ru.clevertec.repository.ReviewRepository;
import ru.clevertec.util.HibernateUtil;

import java.util.List;

public class ReviewRepositoryImpl implements ReviewRepository {
    private final Session session = HibernateUtil.getSession();

    @Override
    public List<Review> findAll() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<Review> reviews = session.createQuery("SELECT r FROM Review r", Review.class)
                    .getResultList();
            transaction.commit();
            return reviews;
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
    public Review findById(Long id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Review review = session.get(Review.class, id);
            transaction.commit();
            return review;
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
    public Review save(Review review) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(review);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        } finally {
            session.close();
        }
        return review;
    }

    @Override
    public boolean remove(Review review) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Review findReview = session.find(Review.class, review.getId());
            if (findReview == null) {
                transaction.rollback();
                return false;
            }
            session.remove(review);
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
    public Review update(Review review) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Review mergeReview = session.merge(review);
            transaction.commit();
            return mergeReview;
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
    public void addReview(Client client, Car car, String text, int rating) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Client foundClient = session.find(Client.class, client.getId());
            Car foundCar = session.find(Car.class, car.getId());
            Review review = Review.builder().text(text).rating(rating).build();
            if (foundClient != null && foundCar != null) {
                foundClient.addReviewToClient(review);
                foundCar.addReviewToCar(review);
            }
            session.persist(review);
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