package com.example.demo.controller.rest;

import com.example.demo.model.Car;
import com.example.demo.model.Modell;
import com.example.demo.service.car.impl.CarServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : CarRestController
 **/
@RestController
@RequestMapping("api/car")
@RequiredArgsConstructor
public class CarRestController {
    private final CarServiceImpl service;

    @ApiOperation("get all cars")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Car> getAll(){
        return service.getAll();
    }
    @ApiOperation(value = "get cars by id", notes = "id must be UUID")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public Car getById (@PathVariable String id){
        return service.getById(id);
    }
    @ApiOperation(value = "save cars", notes = "if you use id it`s UPDATE method, or if not is CREATE method")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public Car save(@RequestBody Car car){
        return service.save(car);
    }
    @ApiOperation(value = "delete cars by id", notes = "id must be UUID")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public Car delete(@PathVariable String id){
        return service.deleteById(id);
    }

}
