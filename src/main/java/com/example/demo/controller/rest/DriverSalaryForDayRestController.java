package com.example.demo.controller.rest;

import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.Modell;
import com.example.demo.service.driverSalaryForDay.impls.DriverSalaryForDayServiceImpl;
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
@RequestMapping("api/salary/forday/driver")
@RequiredArgsConstructor
public class DriverSalaryForDayRestController {
    private final DriverSalaryForDayServiceImpl service;

    @ApiOperation(value = "get all driver salary for day, without undefined customer")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<DriverSalaryForDay> getAll(){
        return service.getAll();
    }

    @ApiOperation(value = "get driver salary for day by id", notes = "id must be UUID")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public DriverSalaryForDay getById (@PathVariable String id){
        return service.getById(id);
    }

    @ApiOperation(value = "save sdriver salary  for day", notes = "if id are exist is create method else update method")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public DriverSalaryForDay save(@RequestBody DriverSalaryForDay modell){
        return service.save(modell);
    }

    @ApiOperation(value = "delete driver salary for day by id", notes = "id must be UUID")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public DriverSalaryForDay delete(@PathVariable String id){
        return service.deleteById(id);
    }



}
