package com.example.demo.controller.rest;

import com.example.demo.model.Customer;
import com.example.demo.model.DiscountCard;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : CustomerRestController
 **/
@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerRestController {
    private final CustomerServiceImpl service;

    @ApiOperation(value = "get all customers, without undefined customer")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Customer> getAll(){
        return service.getAll();
    }
    @ApiOperation(value = "get customer by id", notes = "id must be UUID")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public Customer getById (@PathVariable String id){
        return service.getById(id);
    }
    @ApiOperation(value = "save customer", notes = "if id are exist is create method else update method")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public Customer save(@RequestBody Customer customer){
        return service.save(customer);
    }
    @ApiOperation(value = "delete customer by id", notes = "id must be UUID")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public Customer delete(@PathVariable String id){
        return service.deleteById(id);
    }
}
