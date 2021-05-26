package com.example.demo.controller.ui;

import com.example.demo.form.SalaryForIntervalForm;

import com.example.demo.model.DispatchServiceSalaryForInterval;

import com.example.demo.service.dispatchServiceSalaryForInterval.impls.DispatchServiceSalaryForIntervalServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DispatchServiceSalaryForDayUIController
 **/
@Controller
@RequestMapping("ui/salary/forinterval/dispatchservice")
@RequiredArgsConstructor
public class DispatchServiceSalaryForIntervalUIController {
    private final DispatchServiceSalaryForIntervalServiceImpl service;
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("dispatchServiceSalaryForInterval", service.getAll());
        return "dispatchServiceSalaryForInterval/showAll";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("dispatchServiceSalaryForInterval", service.getById(id));
        return "dispatchServiceSalaryForInterval/showById";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/salary/forinterval/dispatchservice/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        DispatchServiceSalaryForInterval DispatchServiceSalaryForInterval = service.getById(id);
        SalaryForIntervalForm salaryForIntervalForm = new SalaryForIntervalForm(
                "",
                DispatchServiceSalaryForInterval.getSalary().toString(),
                DispatchServiceSalaryForInterval.getFrom().toString(),
                DispatchServiceSalaryForInterval.getTo().toString()
        );
        model.addAttribute("salaryForIntervalForm", salaryForIntervalForm);
        return "dispatchServiceSalaryForInterval/update";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("salaryForDayForm") SalaryForIntervalForm salaryForIntervalForm){
        DispatchServiceSalaryForInterval dispatchServiceSalaryForInterval = service.getById(id);
        dispatchServiceSalaryForInterval.setSalary(Integer.parseInt(salaryForIntervalForm.getSalary()));
        dispatchServiceSalaryForInterval.setFrom(LocalDate.parse(salaryForIntervalForm.getFrom()));
        dispatchServiceSalaryForInterval.setTo(LocalDate.parse(salaryForIntervalForm.getTo()));
        service.save(dispatchServiceSalaryForInterval);
        return "redirect:/ui/salary/forinterval/dispatchservice/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("salaryForIntervalForm", new SalaryForIntervalForm());
        return "dispatchServiceSalaryForInterval/create";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(@ModelAttribute("salaryForIntervalForm") SalaryForIntervalForm salaryForIntervalForm) {
        DispatchServiceSalaryForInterval dispatchServiceSalaryForInterval = new DispatchServiceSalaryForInterval();
        dispatchServiceSalaryForInterval.setSalary(Integer.parseInt(salaryForIntervalForm.getSalary()));
        dispatchServiceSalaryForInterval.setFrom(LocalDate.parse(salaryForIntervalForm.getFrom()));
        dispatchServiceSalaryForInterval.setTo(LocalDate.parse(salaryForIntervalForm.getTo()));
        service.save(dispatchServiceSalaryForInterval);
        return "redirect:/ui/salary/forinterval/dispatchservice/get/all";
    }
}
