package com.example.demo.controller.rest;

import com.example.demo.model.Driver;
import com.example.demo.model.TaxiOffice;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driver.interfaces.IDriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 07.04.2021, среда
 * @className : DriverRestController
 **/

//@Tag(name = "Driver Controller", description = "CRUD for driver")
@RestController
@RequestMapping("api/driver")
public class DriverRestController {

    @Autowired
    DriverServiceImpl driverService;

   // @Operation(summary = "Get All", description = "Get All drivers")
    @RequestMapping("/get/all")
    public List<Driver> getServices(){
        return driverService.getAll();
    }

   // @Operation(summary = "Get By ID", description = "Get Driver by ID")
    @RequestMapping("/get/{id}")
    public Driver getById(@PathVariable(value = "id" ) String id ){
        return driverService.getById(id);
    }

 //   @Operation(summary = "Delete By ID", description = "Delete Driver by ID")
    @GetMapping("/delete/{id}")
    public Driver delete(@PathVariable(value = "id") String id){
        return driverService.deleteById(id);
    }

   // @Operation(summary = "Create", description = "Create Driver")
    @PostMapping("/create/")
    public Driver create(@RequestBody Driver driver){
        return driverService.save(driver);
    }

   // @Operation(summary = "Update", description = "Update Taxi Office")
    @PostMapping("/update")
    public Driver update(@RequestBody Driver driver){
        return driverService.save(driver);
    }




}
