package com.example.demo.controller.rest;

import com.example.demo.model.Order;
import com.example.demo.service.order.impls.OrderServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @ApiOperation(value = "get all order")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Order> getAll(){
        return service.getAll();
    }

    @ApiOperation(value = "get order by id", notes = "id must be UUID")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public Order getById(@PathVariable String id){
       return service.getById(id);
    }

    @ApiOperation(value = "delete order by id", notes = "id must be UUID")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public Order delete(@PathVariable String id){
        return service.deleteById(id);
    }

    @ApiOperation(value = "save order", notes = "if id are exist is create method else update method and you cant use car whist busy")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public Order save(@RequestBody Order order){
        return service.save(order);
    }

}
