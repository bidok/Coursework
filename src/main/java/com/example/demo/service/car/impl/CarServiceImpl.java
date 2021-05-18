package com.example.demo.service.car.impl;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.CarClass;
import com.example.demo.model.TaxiOffice;
import com.example.demo.repository.car.CarRepository;
import com.example.demo.repository.order.OrderRepository;
import com.example.demo.repository.taxiOffice.TaxiOfficeRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.car.interfces.ICarService;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.model.impls.ModelServiceImpl;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : CarServiceImpl
 **/
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements ICarService, IGenericService<Car> {
    private final CarRepository carRepository;
    private final OrderRepository orderRepository;
    private final FakeData fakeData;

    @Override
    public Car getById(String id) {
        return carRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("car not fount whit id: [" + id + "]"));
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Car save(Car car) {

        car.setTaxiOffice(car.getDriver().getTaxiOffice());
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(car.getId()))){
            car.setUpdateTime(LocalDateTime.now());
            car.setCreateTime(getById(car.getId()).getCreateTime());

        }else {
            if(this.getAll().stream().anyMatch(item -> item.getCarNumber().equals(car.getCarNumber()))){
                throw new InvalidDataException("car whit this car number are exist");
            }
        }
       return carRepository.save(car);
    }

    @Override
    public Car deleteById(String id) {
        Car car = this.getById(id);
        orderRepository.saveAll(orderRepository.findAllByCarId(id).stream()
                .peek(item -> item.setCar(this.getById("60929ab5301cee4c79df3d6d")))
                .collect(Collectors.toList()));
        carRepository.deleteById(id);
        return car;
    }

    public List<Car> getAllAndOrderByModel(){
        return carRepository.findAllByOrderByModell();
    }

    public List<Car> getCarsFromSomeTaxiOfficeAndWithSomeCarClass(String taxiOfficeId, String carClass){
        return carRepository.findAllByTaxiOfficeId(taxiOfficeId).stream()
                .filter(item -> item.getModell().getCarClass().equals(CarClass.valueOf(carClass)))
                .collect(Collectors.toList());
    }
}
