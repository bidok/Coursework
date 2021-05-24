package com.example.demo.controller.rest;

import com.example.demo.model.DriverTimeTable;
import com.example.demo.model.OperatorTimeTable;
import com.example.demo.service.driverTimeTable.impl.DriverTimeTableServiceImpl;
import com.example.demo.service.operatorTimeTable.impl.OperatorTimeTableServiceImpl;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "get all driver time table")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<DriverTimeTable> getAll(){
        return service.getAll();
    }

    @ApiOperation(value = "get driver time table by id", notes = "id must be UUID")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public DriverTimeTable getById (@PathVariable String id){
        return service.getById(id);
    }

    @ApiOperation(value = "save driver time table", notes = "if id are exist is create method else update method")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public DriverTimeTable save(@RequestBody DriverTimeTable operatorTimeTable){
        return service.save(operatorTimeTable);
    }

    @ApiOperation(value = "delete driver time table by id", notes = "id must be UUID")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public DriverTimeTable delete(@PathVariable String id){
        return service.deleteById(id);
    }
}
