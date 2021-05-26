package com.example.demo.controller.rest;

import com.example.demo.model.DispatchServiceSalaryForInterval;
import com.example.demo.model.TaxiOfficeSalaryForInterval;
import com.example.demo.service.dispatchServiceSalaryForInterval.impls.DispatchServiceSalaryForIntervalServiceImpl;
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
@RequestMapping("api/salary/forinterval/dispatchservice")
@RequiredArgsConstructor
public class DispatchServiceSalaryForIntervalRestController {
    private final DispatchServiceSalaryForIntervalServiceImpl service;

    @ApiOperation(value = "get all salary of dispatch service for interval, without undefined customer")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List< DispatchServiceSalaryForInterval> getAll(){
        return service.getAll();
    }

    @ApiOperation(value = "get salary of dispatch service for interval by id", notes = "id must be UUID")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public DispatchServiceSalaryForInterval getById (@PathVariable String id){
        return service.getById(id);
    }

    @ApiOperation(value = "save salary of dispatch service for interval", notes = "if id are exist is create method else update method")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public DispatchServiceSalaryForInterval save(@RequestBody  DispatchServiceSalaryForInterval modell){
        return service.save(modell);
    }

    @ApiOperation(value = "delete salary of dispatch service for interval by id", notes = "id must be UUID")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public  DispatchServiceSalaryForInterval delete(@PathVariable String id){
        return service.deleteById(id);
    }



}
