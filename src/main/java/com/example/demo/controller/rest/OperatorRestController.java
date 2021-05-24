package com.example.demo.controller.rest;

import com.example.demo.model.Car;
import com.example.demo.model.Operator;
import com.example.demo.repository.operator.OperatorRepository;
import com.example.demo.service.operator.impl.OperatorServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : OperatorRestController
 **/
@RestController
@RequestMapping("api/operator")@RequiredArgsConstructor
public class OperatorRestController {
    private final OperatorServiceImpl service;

    @ApiOperation(value = "get all operator, without undefined operator")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Operator> getAll(){
        return service.getAll();
    }

    @ApiOperation(value = "get operator by id", notes = "id must be UUID")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public Operator getById (@PathVariable String id){
        return service.getById(id);
    }

    @ApiOperation(value = "save operator", notes = "if id are exist is create method else update method")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public Operator save(@RequestBody Operator operator){
        return service.save(operator);
    }

    @ApiOperation(value = "delete operator by id", notes = "id must be UUID")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public Operator delete(@PathVariable String id){
        return service.deleteById(id);
    }

}
