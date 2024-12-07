package ru.clevertec;

import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.entity.Category;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.impl.CarRepositoryImpl;
import ru.clevertec.service.CarService;
import ru.clevertec.service.impl.CarServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarRepository carRepository = new CarRepositoryImpl();
        CarService carService = new CarServiceImpl(carRepository);
        /////////////////////////////////////////
        List<Car> carList = carService.findAll();
//        Category category=carList.get(1).getCategory();
//        System.out.println(category);
//        CarShowroom carShowroom = carList.get(1).getCarShowroom();
//        System.out.println(carShowroom);
        ////////////////////////////////////////////
//        Car car = carService.findById(1L);
//        System.out.println(car);
        //////////////////////////////////////
//        carService.save(buildCar());
        /////////////////////////////////
//        List<Car> carsByFilters = carService.findCarsByFilters("BMV",
//                "truck", 2010, 50000.0, 50000.9);
//        carsByFilters.forEach(System.out::println);
        //////////////////////////////////////////
//        carService.findCarsSortedByPrice("DESC").forEach(System.out::println);
        //////////////////////////////////////////////
//        carService.findCarsWithPagination(1, 2).forEach(System.out::println);
        ///////////////////////////////////////////
//        CarShowroomRepository carShowroomRepository = new CarShowroomRepositoryImpl();
//        carShowroomRepository.findCarsFromShowroomByIdWithEntityGraph(1L).forEach(System.out::println);
/////////////////////////////////////////////////////
//        ClientRepository clientRepository=new ClientRepositoryImpl();
//        Client client = clientRepository.findById(1L);
//        System.out.println(client);
        /////////////////////////////////////
    }

    static Car buildCar() {
        return Car.builder()
                .model("Model1")
                .brand("Brand1")
                .year(2023)
                .price(257777.8)
                .build();
    }

    static Category buildCategory() {
        return Category.builder()
                .name("Sedan")
                .build();
    }

    static CarShowroom buildCarShowroom() {
        return CarShowroom.builder()
                .name("Showroom4")
                .address("Address4")
                .build();
    }
}