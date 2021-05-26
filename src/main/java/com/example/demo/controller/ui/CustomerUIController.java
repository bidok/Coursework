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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("customer", service.getAll());
        return "customer/showAll";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("customer", service.getById(id));
        return "customer/showById";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/customer/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        Customer customer = service.getById(id);
        CustomerForm customerForm = new CustomerForm();
                customerForm.setName(customer.getName());
                customerForm.setPhoneNumber(customer.getPhoneNumber());
                customerForm.setMail(customer.getMail());
        model.addAttribute("customerForm", customerForm);
        return "customer/update";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("customerForm") CustomerForm customerForm){
        Customer customer = new Customer();
        customer.setName(customerForm.getName());
        customer.setPhoneNumber(customerForm.getPhoneNumber());
        customer.setMail(customerForm.getMail());
        service.save(customer);
        return "redirect:/ui/customer/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("customerForm", new CustomerForm());
        return "customer/create";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(@ModelAttribute("customerForm") CustomerForm customerForm) {
        Customer customer = new Customer();
        customer.setName(customerForm.getName());
        customer.setPhoneNumber(customerForm.getPhoneNumber());
        customer.setMail(customerForm.getMail());
        service.save(customer);
        return "redirect:/ui/customer/get/all";
    }
}
