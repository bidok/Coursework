package com.example.demo.controller.rest;

import com.example.demo.model.Car;
import com.example.demo.model.Operator;
import com.example.demo.repository.operator.OperatorRepository;
import com.example.demo.service.operator.impl.OperatorServiceImpl;
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

    @GetMapping("/get/all")
    public List<Operator> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Operator getById (@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/save")
    public Operator save(@RequestBody Operator operator){
        return service.save(operator);
    }

    @RequestMapping("delete/{id}")
    public Operator delete(@PathVariable String id){
        return service.deleteById(id);
    }

}
