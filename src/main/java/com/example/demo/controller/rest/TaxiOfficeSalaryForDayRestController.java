package com.example.demo.controller.rest;

import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.TaxiOfficeSalaryForDay;
import com.example.demo.service.taxiOfficeSalaryForDay.impls.TaxiOfficeSalaryForDayServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DriverSalaryForDayRestController
 **/
@RestController
@RequestMapping("api/salary/forday/taxioffice")

@RequiredArgsConstructor
public class TaxiOfficeSalaryForDayRestController {
    private final TaxiOfficeSalaryForDayServiceImpl service;


    @ApiOperation(value = "get all salary of taxi office for day, without undefined customer")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<TaxiOfficeSalaryForDay> getAll(){
        return service.getAll();
    }

    @ApiOperation(value = "get salary of taxi office for day by id", notes = "id must be UUID")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public TaxiOfficeSalaryForDay getById (@PathVariable String id){
        return service.getById(id);
    }

    @ApiOperation(value = "save salary of taxi office for day", notes = "if id are exist is create method else update method")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public TaxiOfficeSalaryForDay save(@RequestBody TaxiOfficeSalaryForDay modell){
        return service.save(modell);
    }

    @ApiOperation(value = "delete salary of taxi office for day by id", notes = "id must be UUID")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public TaxiOfficeSalaryForDay delete(@PathVariable String id){
        return service.deleteById(id);
    }



}
