package com.example.demo.controller.rest;

import com.example.demo.model.DispatchServiceSalaryForDay;
import com.example.demo.model.TaxiOfficeSalaryForDay;
import com.example.demo.service.dispatchServiceSalaryForDay.impls.DispatchServiceSalaryForDayServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForDay.impls.TaxiOfficeSalaryForDayServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DriverSalaryForDayRestController
 **/
@RestController
@RequestMapping("api/salary/forday/dispatchservice")
@RequiredArgsConstructor
public class DispatchServiceSalaryForDayRestController {
    private final DispatchServiceSalaryForDayServiceImpl service;

    @GetMapping("/get/all")
    public List<DispatchServiceSalaryForDay> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public DispatchServiceSalaryForDay getById (@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/save")
    public DispatchServiceSalaryForDay save(@RequestBody DispatchServiceSalaryForDay modell){
        return service.save(modell);
    }

    @RequestMapping("delete/{id}")
    public DispatchServiceSalaryForDay delete(@PathVariable String id){
        return service.deleteById(id);
    }



}
