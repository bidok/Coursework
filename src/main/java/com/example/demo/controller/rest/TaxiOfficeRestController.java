package com.example.demo.controller.rest;


import com.example.demo.exceptions.ApplicationExceptionHandler;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.TaxiOffice;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import com.example.demo.service.taxiOffice.interfaces.ITaxiOfficeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author : bidok
 * @created : 10.02.2021, среда
 * @className : MarkaRestController
 **/


@RestController
@RequestMapping("api/taxioffice")
public class TaxiOfficeRestController {

    @Autowired
    TaxiOfficeServiceImpl taxiOfficeService;

    final static Logger LOGGER = LoggerFactory.getLogger(TaxiOfficeRestController.class);

    @ApiOperation(value = "get all taxi services")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<TaxiOffice> getServices(){
        LOGGER.info("method get all from rest controller for taxi office was called");
        return taxiOfficeService.getAll();
    }
    @ApiOperation(value = "get by id taxi services", notes = "id must be UUID")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public TaxiOffice getById(@PathVariable(value = "id" ) String id ){
        LOGGER.info("method get by id:[" + id + "] from rest controller for taxi office was called");
        return taxiOfficeService.getById(id);
    }
    @ApiOperation(value = "delete by id taxi services", notes = "id must be UUID")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public TaxiOffice delete(@PathVariable(value = "id") String id){
        LOGGER.info("method delete by id:[" + id + "] from rest controller for taxi office was called");
        return taxiOfficeService.deleteById(id);
    }
    @ApiOperation(value = "create taxi services", notes = "create without id")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TaxiOffice create(@RequestBody TaxiOffice taxiOffice){
        LOGGER.info("method create from rest controller for taxi office was called");
        return taxiOfficeService.save(taxiOffice);
    }
    @ApiOperation(value = "update taxi services", notes = "id must be exist")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public TaxiOffice update(@RequestBody TaxiOffice taxiOffice){
        LOGGER.info("method update from rest controller for taxi office was called");
        return taxiOfficeService.save(taxiOffice);
    }
}
