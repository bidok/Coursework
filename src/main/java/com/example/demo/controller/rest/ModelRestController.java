package com.example.demo.controller.rest;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.model.Car;
import com.example.demo.model.Modell;
import com.example.demo.service.model.impls.ModelServiceImpl;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "get all model")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Modell> getAll(){
        return service.getAll();
    }

    @ApiOperation(value = "get model by id", notes = "id must be UUID")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public Modell getById (@PathVariable String id){
        return service.getById(id);
    }

    @ApiOperation(value = "save model", notes = "if id are exist is create method else update method")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public Modell save(@RequestBody Modell modell){
        return service.save(modell);
    }

    @ApiOperation(value = "delete model by id", notes = "id must be UUID")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public Modell delete(@PathVariable String id){
        return service.deleteById(id);
    }

}
