package com.example.demo.controller.ui;

import com.example.demo.form.OperatorForm;
import com.example.demo.form.TaxiOfficeForm;
import com.example.demo.model.Operator;
import com.example.demo.model.TaxiOffice;
import com.example.demo.service.operator.impl.OperatorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : OperatorUIController
 **/
@Controller
@RequestMapping("ui/operator")
@RequiredArgsConstructor
public class OperatorUIController {
    private final OperatorServiceImpl service;
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("operators", service.getAll());
        return "operator/showAll";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        Operator operator = service.getById(id);
        model.addAttribute("operator", operator);
        return "operator/showById";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/operator/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("operatorForm", new OperatorForm());
        return "operator/create";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(@ModelAttribute("operatorForm") OperatorForm operatorForm) {
        Operator operator = new Operator();
        operator.setName(operatorForm.getName());
        operator.setPhoneNumber(operatorForm.getPhoneNumber());
        operator.setIdentificationCode(operatorForm.getIdentificationCode());
        operator.setPassportNumber(operatorForm.getPassportNumber());
        service.save(operator);
        return "redirect:/ui/operator/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        Operator operator = service.getById(id);
        OperatorForm operatorForm = new OperatorForm();
        operatorForm.setName(operator.getName());
        operatorForm.setPhoneNumber(operator.getPhoneNumber());
        operatorForm.setIdentificationCode(operator.getIdentificationCode());
        operatorForm.setPassportNumber(operator.getPassportNumber());
        model.addAttribute("operatorForm", operatorForm);
        return "operator/update";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("operatorForm") OperatorForm operatorForm){
        Operator operator = service.getById(id);
        operator.setName(operatorForm.getName());
        operator.setPhoneNumber(operatorForm.getPhoneNumber());
        operator.setIdentificationCode(operatorForm.getIdentificationCode());
        operator.setPassportNumber(operatorForm.getPassportNumber());
        service.save(operator);
        return "redirect:/ui/operator/get/all";
    }
}
