package ru.clevertec.service.impl;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.service.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public boolean remove(Car car) {
        return carRepository.remove(car);
    }

    @Override
    public Car update(Car car) {
        return carRepository.update(car);
    }

    @Override
    public void assignCarToShowroom(Car car, CarShowroom carShowroom) {
        carRepository.assignCarToShowroom(car, carShowroom);
    }

    @Override
    public List<Car> findCarsByFilters(String brand, String category, Integer year, Double minPrice, Double maxPrice) {
        return carRepository.findCarsByFilters(brand, category, year, minPrice, maxPrice);
    }

    @Override
    public List<Car> findCarsSortedByPrice(String typeSorting) {
        return carRepository.findCarsSortedByPrice(typeSorting);
    }

    @Override
    public List<Car> findCarsWithPagination(int page, int pageSize) {
        return carRepository.findCarsWithPagination(page, pageSize);
    }
}
