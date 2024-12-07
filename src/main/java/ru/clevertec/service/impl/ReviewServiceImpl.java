package ru.clevertec.service.impl;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;
import ru.clevertec.repository.ReviewRepository;
import ru.clevertec.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public boolean remove(Review review) {
        return reviewRepository.remove(review);
    }

    @Override
    public Review update(Review review) {
        return reviewRepository.update(review);
    }

    @Override
    public void addReview(Client client, Car car, String text, int rating) {
        reviewRepository.addReview(client, car, text, rating);
    }
}
