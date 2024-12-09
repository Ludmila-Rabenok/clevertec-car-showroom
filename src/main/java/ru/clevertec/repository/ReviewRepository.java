package ru.clevertec.repository;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;

import java.util.List;

public interface ReviewRepository {
    List<Review> findAll();

    Review findById(Long id);

    Review save(Review review);

    boolean remove(Review review);

    Review update(Review review);

    void addReview(Client client, Car car, String text, int rating);
}