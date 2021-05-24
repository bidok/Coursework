package com.example.demo.controller.rest;

import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.DriverSalaryForInterval;
import com.example.demo.service.driverSalaryForDay.impls.DriverSalaryForDayServiceImpl;
import com.example.demo.service.driverSalaryForInterval.impls.DriverSalaryForIntervalServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DriverSalaryForDayRestController
 **/
@RestController
@RequestMapping("api/salary/forinterval/driver")
@RequiredArgsConstructor
public class DriverSalaryForIntervalRestController {
    private final DriverSalaryForIntervalServiceImpl service;

    @ApiOperation(value = "get all driver salary for interval, without undefined customer")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<DriverSalaryForInterval> getAll(){
        return service.getAll();
    }

    @ApiOperation(value = "get driver salary for interval by id", notes = "id must be UUID")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public DriverSalaryForInterval getById (@PathVariable String id){
        return service.getById(id);
    }

    @ApiOperation(value = "save driver salary for interval", notes = "if id are exist is create method else update method")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public DriverSalaryForInterval save(@RequestBody DriverSalaryForInterval modell){
        return service.save(modell);
    }

    @ApiOperation(value = "delete driver salary for interval by id", notes = "id must be UUID")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public DriverSalaryForInterval delete(@PathVariable String id){
        return service.deleteById(id);
    }



}
