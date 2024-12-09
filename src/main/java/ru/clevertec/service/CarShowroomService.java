package ru.clevertec.service;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;

import java.util.List;

public interface CarShowroomService {
    List<CarShowroom> findAll();

    CarShowroom findById(Long id);

    CarShowroom save(CarShowroom carShowroom);

    boolean remove(CarShowroom carShowroom);

    CarShowroom update(CarShowroom carShowroom);

    List<Car> findCarsFromShowroomByIdWithEntityGraph(Long showroomId);
}
