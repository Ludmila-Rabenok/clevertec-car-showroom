package ru.clevertec.service.impl;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.repository.CarShowroomRepository;
import ru.clevertec.service.CarShowroomService;

import java.util.List;

public class CarShowroomServiceImpl implements CarShowroomService {
    private final CarShowroomRepository carShowroomRepository;

    public CarShowroomServiceImpl(CarShowroomRepository carShowroomRepository) {
        this.carShowroomRepository = carShowroomRepository;
    }

    @Override
    public List<CarShowroom> findAll() {
        return carShowroomRepository.findAll();
    }

    @Override
    public CarShowroom findById(Long id) {
        return carShowroomRepository.findById(id);
    }

    @Override
    public CarShowroom save(CarShowroom carShowroom) {
        return carShowroomRepository.save(carShowroom);
    }

    @Override
    public boolean remove(CarShowroom carShowroom) {
        return carShowroomRepository.remove(carShowroom);
    }

    @Override
    public CarShowroom update(CarShowroom carShowroom) {
        return carShowroomRepository.update(carShowroom);
    }

    @Override
    public List<Car> findCarsFromShowroomByIdWithEntityGraph(Long showroomId) {
        return carShowroomRepository.findCarsFromShowroomByIdWithEntityGraph(showroomId);
    }
}
