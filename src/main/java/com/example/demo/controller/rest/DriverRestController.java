package com.example.demo.controller.rest;

import com.example.demo.model.Driver;
import com.example.demo.model.TaxiOffice;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driver.interfaces.IDriverService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 07.04.2021, среда
 * @className : DriverRestController
 **/


@RestController
@RequestMapping("api/driver")
public class DriverRestController {

    @Autowired
    DriverServiceImpl driverService;

    @ApiOperation(value = "get all drivers")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Driver> getServices(){
        return driverService.getAll();
    }

    @ApiOperation(value = "get driver by id", notes = "id must be UUID")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public Driver getById(@PathVariable(value = "id" ) String id ){
        return driverService.getById(id);
    }

    @ApiOperation(value = "delete driver by id", notes = "id must be UUID")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public Driver delete(@PathVariable(value = "id") String id){
        return driverService.deleteById(id);
    }

    @ApiOperation(value = "create model")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value ="/create", method = RequestMethod.POST)
    public Driver create(@RequestBody Driver driver){
        return driverService.save(driver);
    }

    @ApiOperation(value = "update model")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public Driver update(@RequestBody Driver driver){
        return driverService.save(driver);
    }




}
