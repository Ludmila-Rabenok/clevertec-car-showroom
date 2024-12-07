package ru.clevertec.repository;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;

import java.util.List;

public interface CarRepository {
    List<Car> findAll();

    Car findById(Long id);

    Car save(Car car);

    boolean remove(Car car);

    Car update(Car car);

    void assignCarToShowroom(Car car, CarShowroom carShowroom);

    List<Car> findCarsByFilters(String brand, String category, Integer year,
                                Double minPrice, Double maxPrice);

    List<Car> findCarsSortedByPrice(String typeSorting);

    List<Car> findCarsWithPagination(int page, int pageSize);
}
