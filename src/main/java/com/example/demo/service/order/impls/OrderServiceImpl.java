package com.example.demo.service.order.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.Order;
import com.example.demo.repository.car.CarRepository;
import com.example.demo.repository.check.CheckRepository;
import com.example.demo.repository.operator.OperatorRepository;
import com.example.demo.repository.order.OrderRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.car.impl.CarServiceImpl;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.order.interfaces.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Order getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("order whoth this id: [" + id + "] not found"));
    }

    @Override
    public List<Order> getAll() {
            return repository.findAll();
    }

    @Override
    public Order save(Order type) {

        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());

        }
        else {
            if(this.getAll().stream().anyMatch(item -> item.getOrderNumber().equals(type.getOrderNumber()))){
                throw new InvalidDataException("order with this number are exist");
            }
        }
        return repository.save(type);
    }

    @Override
    public Order deleteById(String id) {
        Order order = this.getById(id);
        checkRepository.saveAll(checkRepository.findAllByOrderId(id).stream()
                .peek(item -> item.setOrder(this.getById("60929ebff730832e4f299f8c")))
                .collect(Collectors.toList()));
        repository.deleteById(id);
        return order;
    }



}
