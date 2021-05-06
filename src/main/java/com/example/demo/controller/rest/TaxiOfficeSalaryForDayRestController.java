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


    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<TaxiOfficeSalaryForDay> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public TaxiOfficeSalaryForDay getById (@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/save")
    public TaxiOfficeSalaryForDay save(@RequestBody TaxiOfficeSalaryForDay modell){
        return service.save(modell);
    }

    @RequestMapping("delete/{id}")
    public TaxiOfficeSalaryForDay delete(@PathVariable String id){
        return service.deleteById(id);
    }



}
