package com.example.demo.controller.rest;

import com.example.demo.model.Order;
import com.example.demo.service.order.impls.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : OrderRestController
 **/
@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderServiceImpl service;

    @GetMapping("/get/all")
    public List<Order> getAll(){
        return service.getAll();
    }
    @GetMapping("/get/{id}")
    public Order getById(@PathVariable String id){
       return service.getById(id);
    }
    @GetMapping("/delete/{id}")
    public Order delete(@PathVariable String id){
        return service.deleteById(id);
    }
    @PostMapping("/save")
    public Order save(@RequestBody Order order){
        return service.save(order);
    }

}
