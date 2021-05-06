package com.example.demo.controller.ui;

import com.example.demo.form.CheckForm;
import com.example.demo.form.CustomerForm;
import com.example.demo.model.Check;
import com.example.demo.model.Customer;
import com.example.demo.model.DiscountCard;
import com.example.demo.model.Order;
import com.example.demo.service.check.impls.CheckServiceImpl;
import com.example.demo.service.order.impls.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : CheckUIController
 **/
@Controller
@RequestMapping("ui/check")
@RequiredArgsConstructor
public class CheckUIController {
    private final CheckServiceImpl service;
    private final OrderServiceImpl orderService;

    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("check", service.getAll());
        return "check/showAll";
    }

    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("check", service.getById(id));
        return "check/showById";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/check/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        Check check = service.getById(id);
        CheckForm checkForm = new CheckForm(
                check.getCheckNumber(),
                check.getOrder().getOrderNumber(),
                check.getDistance().toString(),
                check.getPrice().toString()
        );
        model.addAttribute("checkForm", checkForm);
        Map<String, String> cards  = orderService.getAll()
                .stream().collect(Collectors.toMap(Order::getId, Order::getOrderNumber));
        model.addAttribute("orders", cards);
        return "check/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("checkForm") CheckForm checkForm){
        Check check = service.getById(id);
        check.setCheckNumber(checkForm.getCheckNumber());
        check.setOrder(orderService.getById(checkForm.getOrder()));
        check.setDistance(Integer.parseInt(checkForm.getDistance()));
        check.setPrice(Double.parseDouble(checkForm.getPrice()));
        service.save(check);
        return "redirect:/ui/check/get/all";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("checkForm", new CheckForm());
        model.addAttribute("orders",
                orderService.getAll()
                        .stream().collect(Collectors.toMap(Order::getId, Order::getOrderNumber)));
        return "check/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("checkForm") CheckForm checkForm) {
        Check check = new Check();
        check.setCheckNumber(checkForm.getCheckNumber());
        check.setOrder(orderService.getById(checkForm.getOrder()));
        check.setDistance(Integer.parseInt(checkForm.getDistance()));
        check.setPrice(Double.parseDouble(checkForm.getPrice()));
        service.save(check);
        return "redirect:/ui/check/get/all";
    }
}
