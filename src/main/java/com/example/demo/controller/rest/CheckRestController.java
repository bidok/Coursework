package com.example.demo.controller.rest;

import com.example.demo.model.Check;
import com.example.demo.model.Customer;
import com.example.demo.service.check.impls.CheckServiceImpl;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : CheckRestController
 **/
@RestController
@RequestMapping("api/check")
@RequiredArgsConstructor
public class CheckRestController {
    private final CheckServiceImpl service;

    @ApiOperation("get all checks")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/get/all")
    public List<Check> getAll(){
        return service.getAll();
    }
    @ApiOperation(value = "get checks by id", notes = "id must be UUID")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/get/{id}")
    public Check getById (@PathVariable String id){
        return service.getById(id);
    }
    @ApiOperation(value = "save check", notes = "if you use id it`s UPDATE method, or if not is CREATE method")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="/save")
    public Check save(@RequestBody Check check){
        return service.save(check);
    }
    @ApiOperation(value = "delete check by id", notes = "id must be UUID")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public Check delete(@PathVariable String id){
        return service.deleteById(id);
    }
}
