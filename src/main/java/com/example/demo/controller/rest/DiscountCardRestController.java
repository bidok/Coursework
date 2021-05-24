package com.example.demo.controller.rest;

import com.example.demo.model.DiscountCard;
import com.example.demo.model.Operator;
import com.example.demo.service.discountCard.impls.DiscountCardServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : DiscountCardRestController
 **/
@RestController
@RequestMapping("api/discountcard")
@RequiredArgsConstructor
public class DiscountCardRestController {
    private final DiscountCardServiceImpl service;
    @ApiOperation(value = "get all discount cards, without undefined customer")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<DiscountCard> getAll(){
        return service.getAll();
    }
    @ApiOperation(value = "get discount cards by id", notes = "id must be UUID")
    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
    public DiscountCard getById (@PathVariable String id){
        return service.getById(id);
    }
    @ApiOperation(value = "save discount card", notes = "if id are exist is create method else update method")
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public DiscountCard save(@RequestBody DiscountCard discountCard){
        return service.save(discountCard);
    }
    @ApiOperation(value = "delete discount card by id", notes = "id must be UUID")
    @RequestMapping(value ="delete/{id}", method = RequestMethod.GET)
    public DiscountCard delete(@PathVariable String id){
        return service.deleteById(id);
    }
}
