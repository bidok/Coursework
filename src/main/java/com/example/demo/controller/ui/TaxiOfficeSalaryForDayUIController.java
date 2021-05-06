package com.example.demo.controller.ui;

import com.example.demo.form.SalaryForDayForm;
import com.example.demo.model.Driver;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.TaxiOffice;
import com.example.demo.model.TaxiOfficeSalaryForDay;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverSalaryForDay.impls.DriverSalaryForDayServiceImpl;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForDay.impls.TaxiOfficeSalaryForDayServiceImpl;
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
@RequestMapping("ui/salary/forday/taxioffice")
@RequiredArgsConstructor
public class TaxiOfficeSalaryForDayUIController {
    private final TaxiOfficeSalaryForDayServiceImpl service;
    private final TaxiOfficeServiceImpl taxiOfficeService;

    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("taxiOfficeSalaryForDay", service.getAll());
        return "taxiOfficeSalaryForDay/showAll";
    }

    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("taxiOfficeSalaryForDay", service.getById(id));
        return "taxiOfficeSalaryForDay/showById";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/salary/forday/taxioffice/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        TaxiOfficeSalaryForDay taxiOfficeSalaryForDay = service.getById(id);
        SalaryForDayForm salaryForDayForm = new SalaryForDayForm(
                taxiOfficeSalaryForDay.getTaxiOffice().getName(),
                taxiOfficeSalaryForDay.getSalary().toString()
        );
        model.addAttribute("salaryForDayForm", salaryForDayForm);
        Map<String, String> cards  = taxiOfficeService.getAll()
                .stream().collect(Collectors.toMap(TaxiOffice::getId, TaxiOffice::getName));
        model.addAttribute("taxioffices", cards);
        return "taxiOfficeSalaryForDay/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("salaryForDayForm") SalaryForDayForm salaryForDayForm){
        TaxiOfficeSalaryForDay taxiOfficeSalaryForDay = service.getById(id);
        taxiOfficeSalaryForDay.setTaxiOffice(taxiOfficeService.getById(salaryForDayForm.getEntity()));
        taxiOfficeSalaryForDay.setSalary(Integer.parseInt(salaryForDayForm.getSalary()));
        service.save(taxiOfficeSalaryForDay);
        return "redirect:/ui/salary/forday/taxioffice/get/all";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("salaryForDayForm", new SalaryForDayForm());
        model.addAttribute("taxioffices",
                taxiOfficeService.getAll()
                        .stream().collect(Collectors.toMap(TaxiOffice::getId, TaxiOffice::getName)));
        return "taxiOfficeSalaryForDay/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("salaryForDayForm") SalaryForDayForm salaryForDayForm) {
        TaxiOfficeSalaryForDay taxiOfficeSalaryForDay = new TaxiOfficeSalaryForDay();
        taxiOfficeSalaryForDay.setTaxiOffice(taxiOfficeService.getById(salaryForDayForm.getEntity()));
        taxiOfficeSalaryForDay.setSalary(Integer.parseInt(salaryForDayForm.getSalary()));
        service.save(taxiOfficeSalaryForDay);
        return "redirect:/ui/salary/forday/taxioffice/get/all";
    }
}
