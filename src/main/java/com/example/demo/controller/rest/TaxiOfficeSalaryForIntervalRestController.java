package com.example.demo.controller.rest;

import com.example.demo.model.DriverSalaryForInterval;
import com.example.demo.model.TaxiOfficeSalaryForInterval;
import com.example.demo.service.driverSalaryForInterval.impls.DriverSalaryForIntervalServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForInterval.impls.TaxiOfficeSalaryForIntervalServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DriverSalaryForDayRestController
 **/
@RestController
@RequestMapping("api/salary/forinterval/taxioffice")
@RequiredArgsConstructor
public class TaxiOfficeSalaryForIntervalRestController {
    private final TaxiOfficeSalaryForIntervalServiceImpl service;

    @ApiOperation(value = "get all salary of taxi office for interval, without undefined customer")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List< TaxiOfficeSalaryForInterval> getAll(){
        return service.getAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "get salary of taxi office for interval by id", notes = "id must be UUID")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public  TaxiOfficeSalaryForInterval getById (@PathVariable String id){
        return service.getById(id);
    }

    @ApiOperation(value = "save salary of taxi office for interval", notes = "if id are exist is create method else update method")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public TaxiOfficeSalaryForInterval save(@RequestBody  TaxiOfficeSalaryForInterval modell){
        return service.save(modell);
    }

    @ApiOperation(value = "delete salary of taxi office for interval by id", notes = "id must be UUID")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public  TaxiOfficeSalaryForInterval delete(@PathVariable String id){
        return service.deleteById(id);
    }



}
