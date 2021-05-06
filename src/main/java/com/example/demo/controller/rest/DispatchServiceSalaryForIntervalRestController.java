package com.example.demo.controller.rest;

import com.example.demo.model.DispatchServiceSalaryForInterval;
import com.example.demo.model.TaxiOfficeSalaryForInterval;
import com.example.demo.service.dispatchServiceSalaryForInterval.impls.DispatchServiceSalaryForIntervalServiceImpl;
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
@RequestMapping("api/salary/forinterval/dispatchservice")
@RequiredArgsConstructor
public class DispatchServiceSalaryForIntervalRestController {
    private final DispatchServiceSalaryForIntervalServiceImpl service;

    @GetMapping("/get/all")
    public List< DispatchServiceSalaryForInterval> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public DispatchServiceSalaryForInterval getById (@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/save")
    public DispatchServiceSalaryForInterval save(@RequestBody  DispatchServiceSalaryForInterval modell){
        return service.save(modell);
    }

    @RequestMapping("delete/{id}")
    public  DispatchServiceSalaryForInterval delete(@PathVariable String id){
        return service.deleteById(id);
    }



}
