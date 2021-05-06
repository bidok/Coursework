package com.example.demo.controller.ui;

import com.example.demo.form.SalaryForDayForm;
import com.example.demo.model.Driver;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverSalaryForDay.impls.DriverSalaryForDayServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DriverSalaryForDayUIController
 **/
@Controller
@RequestMapping("ui/salary/forday/driver")
@RequiredArgsConstructor
public class DriverSalaryForDayUIController {
    private final DriverSalaryForDayServiceImpl service;
    private final DriverServiceImpl driverService;

    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("driverSalaryForDay", service.getAll());
        return "driverSalaryForDay/showAll";
    }

    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("driverSalaryForDay", service.getById(id));
        return "driverSalaryForDay/showById";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/salary/forday/driver/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        DriverSalaryForDay driverSalaryForDay = service.getById(id);
        SalaryForDayForm salaryForDayForm = new SalaryForDayForm(
                driverSalaryForDay.getDriver().getName(),
                driverSalaryForDay.getSalary().toString()
        );
        model.addAttribute("salaryForDayForm", salaryForDayForm);
        Map<String, String> cards  = driverService.getAll()
                .stream().collect(Collectors.toMap(Driver::getId, Driver::getName));
        model.addAttribute("drivers", cards);
        return "driverSalaryForDay/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("salaryForDayForm") SalaryForDayForm salaryForDayForm){
        DriverSalaryForDay driverSalaryForDay = service.getById(id);
        driverSalaryForDay.setDriver(driverService.getById(salaryForDayForm.getEntity()));
        driverSalaryForDay.setSalary(Integer.parseInt(salaryForDayForm.getSalary()));
        service.save(driverSalaryForDay);
        return "redirect:/ui/salary/forday/driver/get/all";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("salaryForDayForm", new SalaryForDayForm());
        model.addAttribute("drivers",
                driverService.getAll()
                        .stream().collect(Collectors.toMap(Driver::getId, Driver::getName)));
        return "driverSalaryForDay/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("salaryForDayForm") SalaryForDayForm salaryForDayForm) {
        DriverSalaryForDay driverSalaryForDay = new DriverSalaryForDay();
        driverSalaryForDay.setDriver(driverService.getById(salaryForDayForm.getEntity()));
        driverSalaryForDay.setSalary(Integer.parseInt(salaryForDayForm.getSalary()));
        service.save(driverSalaryForDay);
        return "redirect:/ui/salary/forday/driver/get/all";
    }
}
