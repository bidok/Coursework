package com.example.demo.controller.rest;

import com.example.demo.model.DiscountCard;
import com.example.demo.model.Operator;
import com.example.demo.service.discountCard.impls.DiscountCardServiceImpl;
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

    @GetMapping("/get/all")
    public List<DiscountCard> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public DiscountCard getById (@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/save")
    public DiscountCard save(@RequestBody DiscountCard discountCard){
        return service.save(discountCard);
    }

    @RequestMapping("delete/{id}")
    public DiscountCard delete(@PathVariable String id){
        return service.deleteById(id);
    }
}
