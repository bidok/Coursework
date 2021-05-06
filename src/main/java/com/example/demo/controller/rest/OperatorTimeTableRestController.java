package com.example.demo.controller.rest;

import com.example.demo.model.Operator;
import com.example.demo.model.OperatorTimeTable;
import com.example.demo.model.TimeTable;
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
@RequestMapping("api/timetable/operator")
@RequiredArgsConstructor
public class OperatorTimeTableRestController {
    private final OperatorTimeTableServiceImpl service;

    @GetMapping("/get/all")
    public List<OperatorTimeTable> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public OperatorTimeTable getById (@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/save")
    public OperatorTimeTable save(@RequestBody OperatorTimeTable operatorTimeTable){
        return service.save(operatorTimeTable);
    }

    @RequestMapping("delete/{id}")
    public OperatorTimeTable delete(@PathVariable String id){
        return service.deleteById(id);
    }
}
