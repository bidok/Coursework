package com.example.demo.controller.rest;

import com.example.demo.model.Customer;
import com.example.demo.model.DiscountCard;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
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


    @GetMapping("/get/all")
    public List<Customer> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Customer getById (@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/save")
    public Customer save(@RequestBody Customer customer){
        return service.save(customer);
    }

    @RequestMapping("delete/{id}")
    public Customer delete(@PathVariable String id){
        return service.deleteById(id);
    }
}
