package com.example.demo.controller.ui;

import com.example.demo.form.CustomerForm;
import com.example.demo.form.DriverForm;
import com.example.demo.model.Customer;
import com.example.demo.model.DiscountCard;
import com.example.demo.model.Driver;
import com.example.demo.model.TaxiOffice;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.discountCard.impls.DiscountCardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : CustomerUIController
 **/
@Controller
@RequestMapping("ui/customer")
@RequiredArgsConstructor
public class CustomerUIController {
    private final CustomerServiceImpl service;
    private final DiscountCardServiceImpl discountCardService;

    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("customer", service.getAll());
        return "customer/showAll";
    }

    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("customer", service.getById(id));
        return "customer/showById";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/customer/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        Customer customer = service.getById(id);
        CustomerForm customerForm = new CustomerForm(
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getDiscountCard().getCardNumber()
        );
        model.addAttribute("customerForm", customerForm);
        Map<String, String> cards  = discountCardService.getAll()
                .stream().collect(Collectors.toMap(DiscountCard::getId, DiscountCard::getCardNumber));
        model.addAttribute("cards", cards);
        return "customer/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("customerForm") CustomerForm customerForm){
        Customer customer = new Customer();
        customer.setName(customerForm.getName());
        customer.setPhoneNumber(customerForm.getPhoneNumber());
        customer.setDiscountCard(discountCardService.getById(customerForm.getDiscountCard()));
        return "redirect:/ui/customer/get/all";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("customerForm", new CustomerForm());
        model.addAttribute("cards",
                discountCardService.getAll().stream()
                        .collect(Collectors.toMap(DiscountCard::getId, DiscountCard::getCardNumber)));
        return "customer/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("customerForm") CustomerForm customerForm) {
        Customer customer = new Customer();
        customer.setName(customerForm.getName());
        customer.setPhoneNumber(customerForm.getPhoneNumber());
        customer.setDiscountCard(discountCardService.getById(customerForm.getDiscountCard()));
        service.save(customer);
        return "redirect:/ui/customer/get/all";
    }
}
