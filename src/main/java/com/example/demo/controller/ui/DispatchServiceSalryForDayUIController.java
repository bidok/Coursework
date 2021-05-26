package com.example.demo.controller.ui;

import com.example.demo.form.SalaryForDayForm;
import com.example.demo.model.DispatchServiceSalaryForDay;
import com.example.demo.model.Driver;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.service.dispatchServiceSalaryForDay.impls.DispatchServiceSalaryForDayServiceImpl;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverSalaryForDay.impls.DriverSalaryForDayServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("ui/salary/forday/dispatchservice")
@RequiredArgsConstructor
public class DispatchServiceSalryForDayUIController {
    private final DispatchServiceSalaryForDayServiceImpl service;
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("dispatchServiceSalaryForDay", service.getAll());
        return "dispatchServiceSalaryForDay/showAll";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("dispatchServiceSalaryForDay", service.getById(id));
        return "dispatchServiceSalaryForDay/showById";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/salary/forday/dispatchservice/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        DispatchServiceSalaryForDay dispatchServiceSalaryForDay = service.getById(id);
        SalaryForDayForm salaryForDayForm = new SalaryForDayForm();
               salaryForDayForm.setSalary(dispatchServiceSalaryForDay.getSalary().toString());
        model.addAttribute("salaryForDayForm", salaryForDayForm);

        return "dispatchServiceSalaryForDay/update";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("salaryForDayForm") SalaryForDayForm salaryForDayForm){
        DispatchServiceSalaryForDay driverSalaryForDay = new DispatchServiceSalaryForDay();;
        driverSalaryForDay.setSalary(Integer.parseInt(salaryForDayForm.getSalary()));
        service.save(driverSalaryForDay);
        return "redirect:/ui/salary/forday/dispatchservice/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("salaryForDayForm", new SalaryForDayForm());
        return "dispatchServiceSalaryForDay/create";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(@ModelAttribute("salaryForDayForm") SalaryForDayForm salaryForDayForm) {
        DispatchServiceSalaryForDay driverSalaryForDay = new DispatchServiceSalaryForDay();
        driverSalaryForDay.setSalary(Integer.parseInt(salaryForDayForm.getSalary()));
        service.save(driverSalaryForDay);
        return "redirect:/ui/salary/forday/dispatchservice/get/all";
    }
}
