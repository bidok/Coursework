package com.example.demo.controller.rest;

import com.example.demo.model.DriverTimeTable;
import com.example.demo.model.OperatorTimeTable;
import com.example.demo.service.driverTimeTable.impl.DriverTimeTableServiceImpl;
import com.example.demo.service.operatorTimeTable.impl.OperatorTimeTableServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : OperatorTimeTableRestController
 **/
@RestController
@RequestMapping("api/timetable/driver")
@RequiredArgsConstructor
public class DriverTimeTableRestController {
    private final DriverTimeTableServiceImpl service;

    @GetMapping("/get/all")
    public List<DriverTimeTable> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public DriverTimeTable getById (@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/save")
    public DriverTimeTable save(@RequestBody DriverTimeTable operatorTimeTable){
        return service.save(operatorTimeTable);
    }

    @RequestMapping("delete/{id}")
    public DriverTimeTable delete(@PathVariable String id){
        return service.deleteById(id);
    }
}
