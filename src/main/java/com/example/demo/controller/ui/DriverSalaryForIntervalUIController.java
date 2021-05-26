package com.example.demo.controller.ui;

import com.example.demo.form.SalaryForDayForm;
import com.example.demo.form.SalaryForIntervalForm;
import com.example.demo.model.Driver;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.DriverSalaryForInterval;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverSalaryForDay.impls.DriverSalaryForDayServiceImpl;
import com.example.demo.service.driverSalaryForInterval.impls.DriverSalaryForIntervalServiceImpl;
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
 * @className : DriverSalaryForDayUIController
 **/
@Controller
@RequestMapping("ui/salary/forinterval/driver")
@RequiredArgsConstructor
public class DriverSalaryForIntervalUIController {
    private final DriverSalaryForIntervalServiceImpl service;
    private final DriverServiceImpl driverService;
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("driverSalaryForInterval", service.getAll());
        return "driverSalaryForInterval/showAll";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("driverSalaryForInterval", service.getById(id));
        return "driverSalaryForInterval/showById";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/salary/forinterval/driver/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        DriverSalaryForInterval driverSalaryForInterval = service.getById(id);
        SalaryForIntervalForm salaryForIntervalForm = new SalaryForIntervalForm(
                driverSalaryForInterval.getDriver().getName(),
                driverSalaryForInterval.getSalary().toString(),
                driverSalaryForInterval.getFrom().toString(),
                driverSalaryForInterval.getTo().toString()
        );
        model.addAttribute("salaryForIntervalForm", salaryForIntervalForm);
        Map<String, String> cards  = driverService.getAll()
                .stream().collect(Collectors.toMap(Driver::getId, Driver::getName));
        model.addAttribute("drivers", cards);
        return "driverSalaryForInterval/update";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("salaryForDayForm") SalaryForIntervalForm salaryForIntervalForm){
        DriverSalaryForInterval driverSalaryForInterval = service.getById(id);
        driverSalaryForInterval.setDriver(driverService.getById(salaryForIntervalForm.getEntity()));
        driverSalaryForInterval.setSalary(Integer.parseInt(salaryForIntervalForm.getSalary()));
        driverSalaryForInterval.setFrom(LocalDate.parse(salaryForIntervalForm.getFrom()));
        driverSalaryForInterval.setTo(LocalDate.parse(salaryForIntervalForm.getTo()));
        service.save(driverSalaryForInterval);
        return "redirect:/ui/salary/forinterval/driver/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("salaryForIntervalForm", new SalaryForIntervalForm());
        model.addAttribute("drivers",
                driverService.getAll()
                        .stream().collect(Collectors.toMap(Driver::getId, Driver::getName)));
        return "driverSalaryForInterval/create";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(@ModelAttribute("salaryForIntervalForm") SalaryForIntervalForm salaryForIntervalForm) {
        DriverSalaryForInterval driverSalaryForInterval = new DriverSalaryForInterval();
        driverSalaryForInterval.setDriver(driverService.getById(salaryForIntervalForm.getEntity()));
        driverSalaryForInterval.setSalary(Integer.parseInt(salaryForIntervalForm.getSalary()));
        driverSalaryForInterval.setFrom(LocalDate.parse(salaryForIntervalForm.getFrom()));
        driverSalaryForInterval.setTo(LocalDate.parse(salaryForIntervalForm.getTo()));
        service.save(driverSalaryForInterval);
        return "redirect:/ui/salary/forinterval/driver/get/all";
    }
}
