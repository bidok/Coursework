package com.example.demo.service.order.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.Check;
import com.example.demo.model.Order;
import com.example.demo.repository.car.CarRepository;
import com.example.demo.repository.check.CheckRepository;
import com.example.demo.repository.operator.OperatorRepository;
import com.example.demo.repository.order.OrderRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.car.impl.CarServiceImpl;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.order.interfaces.IOrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : OrderServiceImpl
 **/
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService, IGenericService<Order> {
    private final OrderRepository repository;
    private final CheckRepository checkRepository;
    private final FakeData fakeData;
    private final CarServiceImpl carService;

    private static  final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public Order getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("order whoth this id: [" + id + "] not found"));
    }

    @Override
    public List<Order> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll().stream()
                    .filter(Objects::nonNull)
                    .filter(item -> item.getOrderNumber() != null && !item.getOrderNumber().equals("undefined"))
                    .collect(Collectors.toList());
    }

    @Override
    public Order save(Order type) {
        LOGGER.info("method save was called ");
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            LOGGER.info("object with id: [" + type.getId() + "] was updated");
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
        }
        else {
            if(this.getAll().stream().anyMatch(item -> item.getOrderNumber().equals(type.getOrderNumber()))){
                throw new InvalidDataException("order with this number are exist");
            }
            if(type.getCar().getState().equals(false)) {
                throw new InvalidDataException("car with number " + type.getCar().getCarNumber() + " are exist");
            }
            Car car = type.getCar();
            car.setState(false);
            carService.save(car);
            LOGGER.info("object was created");
        }
        return repository.save(type);
    }

    @Override
    public Order deleteById(String id) {
        LOGGER.info("method delete was called");
        Order order = this.getById(id);
        checkRepository.saveAll(checkRepository.findAllByOrderId(id).stream()
                .peek(item -> item.setOrder(this.getById("60929ebff730832e4f299f8c")))
                .collect(Collectors.toList()));
        repository.deleteById(id);
        LOGGER.info("object with id:[" + id +"] was deleted");
        return order;
    }



}
