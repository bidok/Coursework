package com.example.demo.controller.rest;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.model.Car;
import com.example.demo.model.Modell;
import com.example.demo.service.model.impls.ModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 29.04.2021, четверг
 * @className : ModelRestController
 **/
@RestController
@RequestMapping("api/model")
public class ModelRestController {

    @Autowired
    private ModelServiceImpl service;

    @GetMapping("/get/all")
    public List<Modell> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Modell getById (@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/save")
    public Modell save(@RequestBody Modell modell){
        return service.save(modell);
    }

    @RequestMapping("delete/{id}")
    public Modell delete(@PathVariable String id){
        return service.deleteById(id);
    }

}
