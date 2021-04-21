package com.example.demo.controller.rest;


import com.example.demo.model.TaxiOffice;
import com.example.demo.service.taxiOffice.interfaces.ITaxiOfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 10.02.2021, среда
 * @className : MarkaRestController
 **/

@Tag(name = "Taxi Office Controller", description = "CRUD for taxi office")
@RestController
@RequestMapping("api/taxioffice")
public class TaxiOfficeRestController {

    @Autowired
    ITaxiOfficeService taxiOfficeService;

    @Operation(summary = "Get All", description = "Get All taxi offices")
    @RequestMapping("/get/all")
    public List<TaxiOffice> getServices(){
        return taxiOfficeService.getAll();
    }

    @Operation(summary = "Get By ID", description = "Get Taxi Office by ID")
    @RequestMapping("/get/{id}")
    public TaxiOffice getById(@PathVariable(value = "id" ) String id ){
        return taxiOfficeService.getById(id);
    }

    @Operation(summary = "Delete By ID", description = "Delete Taxi Office by ID")
    @GetMapping("/delete/{id}")
    public TaxiOffice delete(@PathVariable(value = "id") String id){
        return taxiOfficeService.delete(id);
    }

    @Operation(summary = "Create", description = "Create Taxi Office")
    @PostMapping("/create/")
    public TaxiOffice create(@RequestBody TaxiOffice taxiOffice){
        return taxiOfficeService.create(taxiOffice);
    }

    @Operation(summary = "Update", description = "Update Taxi Office")
    @PostMapping("/update")
    public TaxiOffice update(@RequestBody TaxiOffice taxiOffice){
        return taxiOfficeService.update(taxiOffice);
    }
}
