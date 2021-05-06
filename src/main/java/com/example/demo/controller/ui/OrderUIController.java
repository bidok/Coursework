package com.example.demo.controller.ui;

import com.example.demo.form.CarForm;
import com.example.demo.form.OperatorForm;
import com.example.demo.form.OrderForm;
import com.example.demo.model.*;
import com.example.demo.service.car.impl.CarServiceImpl;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.operator.impl.OperatorServiceImpl;
import com.example.demo.service.order.impls.OrderServiceImpl;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : OrderUIController
 **/
@Controller
@RequestMapping("ui/order")
@RequiredArgsConstructor
public class OrderUIController {
    private final OrderServiceImpl service;
    private final DriverServiceImpl driverService;
    private final CarServiceImpl carService;
    private final OperatorServiceImpl operatorService;
    private final CustomerServiceImpl customerService;


    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("orders", service.getAll());
        return "order/showAll";
    }

    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("order", service.getById(id));
        return "order/showById";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/order/get/all";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("orderForm", new OrderForm());
        model.addAttribute("customers", customerService.getAll().stream().collect(Collectors.toMap(Customer::getId, Customer::getName)));
        //model.addAttribute("drivers", driverService.getAll().stream().collect(Collectors.toMap(Driver::getId, Driver::getName)));
        model.addAttribute("cars", carService.getAll().stream().collect(Collectors.toMap(Car::getId, Car::getCarNumber)));
        model.addAttribute("operators", operatorService.getAll().stream().collect(Collectors.toMap(Operator::getId, Operator::getName)));
        model.addAttribute("state", new ArrayList<String>(Arrays.asList("true", "false")));
        return "order/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("orderForm") OrderForm orderForm) {
        Order order = new Order();
        order.setOrderNumber(orderForm.getOrderNumber());
        order.setStartAddress(orderForm.getStartAddress());
        order.setEndAddress(orderForm.getEndAddress());
        order.setIsOutOfCity(Boolean.valueOf(orderForm.getIsOutOfCity()));
        order.setCustomer(customerService.getById(orderForm.getCustomer()));
        order.setOperator(operatorService.getById(orderForm.getOperator()));
        //order.setDriver(driverService.getById(orderForm.getDriver()));
        order.setCar(carService.getById(orderForm.getCar()));
        order.setCompleted(Boolean.valueOf(orderForm.getCompleted()));
        service.save(order);
        return "redirect:/ui/order/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        Order order = service.getById(id);
        OrderForm orderForm = new OrderForm();
        orderForm.setOrderNumber(order.getOrderNumber());
        orderForm.setStartAddress(order.getStartAddress());
        orderForm.setEndAddress(order.getEndAddress());
        orderForm.setIsOutOfCity(order.getIsOutOfCity().toString());
        orderForm.setCustomer(order.getCustomer().getName());
        orderForm.setOperator(order.getOperator().getName());
        //orderForm.setDriver(order.getDriver().getName());
        orderForm.setCar(order.getCar().getCarNumber());
        orderForm.setCompleted(order.getCompleted().toString());
        model.addAttribute("orderForm", orderForm);
        model.addAttribute("customers", customerService.getAll().stream().collect(Collectors.toMap(Customer::getId, Customer::getName)));
        //model.addAttribute("drivers", driverService.getAll().stream().collect(Collectors.toMap(Driver::getId, Driver::getName)));
        model.addAttribute("cars", carService.getAll().stream().collect(Collectors.toMap(Car::getId, Car::getCarNumber)));
        model.addAttribute("operators", operatorService.getAll().stream().collect(Collectors.toMap(Operator::getId, Operator::getName)));
        model.addAttribute("state", new ArrayList<String>(Arrays.asList("true", "false")));
        return "order/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("orderForm") OrderForm orderForm){
        Order order = service.getById(id);
        order.setOrderNumber(orderForm.getOrderNumber());
        order.setStartAddress(orderForm.getStartAddress());
        order.setEndAddress(orderForm.getEndAddress());
        order.setIsOutOfCity(Boolean.valueOf(orderForm.getIsOutOfCity()));
        order.setCustomer(customerService.getById(orderForm.getCustomer()));
        order.setOperator(operatorService.getById(orderForm.getOperator()));
       // order.setDriver(driverService.getById(orderForm.getDriver()));
        order.setCar(carService.getById(orderForm.getCar()));
        order.setCompleted(Boolean.valueOf(orderForm.getCompleted()));
        service.save(order);
        return "redirect:/ui/order/get/all";
    }

}
