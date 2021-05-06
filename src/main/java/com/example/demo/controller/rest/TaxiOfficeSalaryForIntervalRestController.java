package com.example.demo.controller.rest;

import com.example.demo.model.DriverSalaryForInterval;
import com.example.demo.model.TaxiOfficeSalaryForInterval;
import com.example.demo.service.driverSalaryForInterval.impls.DriverSalaryForIntervalServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForInterval.impls.TaxiOfficeSalaryForIntervalServiceImpl;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/get/all")
    public List< TaxiOfficeSalaryForInterval> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public  TaxiOfficeSalaryForInterval getById (@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/save")
    public TaxiOfficeSalaryForInterval save(@RequestBody  TaxiOfficeSalaryForInterval modell){
        return service.save(modell);
    }

    @RequestMapping("delete/{id}")
    public  TaxiOfficeSalaryForInterval delete(@PathVariable String id){
        return service.deleteById(id);
    }



}
