package com.example.demo.controller.rest;

import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.DriverSalaryForInterval;
import com.example.demo.service.driverSalaryForDay.impls.DriverSalaryForDayServiceImpl;
import com.example.demo.service.driverSalaryForInterval.impls.DriverSalaryForIntervalServiceImpl;
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

    @GetMapping("/get/all")
    public List<DriverSalaryForInterval> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public DriverSalaryForInterval getById (@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/save")
    public DriverSalaryForInterval save(@RequestBody DriverSalaryForInterval modell){
        return service.save(modell);
    }

    @RequestMapping("delete/{id}")
    public DriverSalaryForInterval delete(@PathVariable String id){
        return service.deleteById(id);
    }



}
