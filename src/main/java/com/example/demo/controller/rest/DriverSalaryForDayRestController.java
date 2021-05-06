package com.example.demo.controller.rest;

import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.Modell;
import com.example.demo.service.driverSalaryForDay.impls.DriverSalaryForDayServiceImpl;
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

    @GetMapping("/get/all")
    public List<DriverSalaryForDay> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public DriverSalaryForDay getById (@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/save")
    public DriverSalaryForDay save(@RequestBody DriverSalaryForDay modell){
        return service.save(modell);
    }

    @RequestMapping("delete/{id}")
    public DriverSalaryForDay delete(@PathVariable String id){
        return service.deleteById(id);
    }



}
