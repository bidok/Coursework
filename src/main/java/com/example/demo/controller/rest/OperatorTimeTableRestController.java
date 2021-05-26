package com.example.demo.controller.rest;

import com.example.demo.model.Operator;
import com.example.demo.model.OperatorTimeTable;
import com.example.demo.model.TimeTable;
import com.example.demo.service.operatorTimeTable.impl.OperatorTimeTableServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @ApiOperation(value = "get all operator time table")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<OperatorTimeTable> getAll(){
        return service.getAll();
    }

    @ApiOperation(value = "get operator time table by id", notes = "id must be UUID")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public OperatorTimeTable getById (@PathVariable String id){
        return service.getById(id);
    }

    @ApiOperation(value = "save operator time table", notes = "if id are exist is create method else update method")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public OperatorTimeTable save(@RequestBody OperatorTimeTable operatorTimeTable){
        return service.save(operatorTimeTable);
    }

    @ApiOperation(value = "delete operator time table by id", notes = "id must be UUID")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public OperatorTimeTable delete(@PathVariable String id){
        return service.deleteById(id);
    }
}
