package com.example.demo.controller.ui;

import com.example.demo.form.SalaryForIntervalForm;
import com.example.demo.model.TaxiOffice;
import com.example.demo.model.TaxiOfficeSalaryForInterval;


import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForInterval.impls.TaxiOfficeSalaryForIntervalServiceImpl;
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
 * @className : TaxiOfficeSalaryForDayUIController
 **/
@Controller
@RequestMapping("ui/salary/forinterval/taxioffice")
@RequiredArgsConstructor
public class TaxiOfficeSalaryForIntervalUIController {
    private final TaxiOfficeSalaryForIntervalServiceImpl service;
    private final TaxiOfficeServiceImpl taxiOfficeService;
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("taxiOfficeSalaryForInterval", service.getAll());
        return "taxiOfficeSalaryForInterval/showAll";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("taxiOfficeSalaryForInterval", service.getById(id));
        return "taxiOfficeSalaryForInterval/showById";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/salary/forinterval/taxioffice/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        TaxiOfficeSalaryForInterval taxiOfficeSalaryForInterval = service.getById(id);
        SalaryForIntervalForm salaryForIntervalForm = new SalaryForIntervalForm(
                taxiOfficeSalaryForInterval.getTaxiOffice().getName(),
                taxiOfficeSalaryForInterval.getSalary().toString(),
                taxiOfficeSalaryForInterval.getFrom().toString(),
                taxiOfficeSalaryForInterval.getTo().toString()
        );
        model.addAttribute("salaryForIntervalForm", salaryForIntervalForm);
        Map<String, String> cards  = taxiOfficeService.getAll()
                .stream().collect(Collectors.toMap(TaxiOffice::getId, TaxiOffice::getName));
        model.addAttribute("taxiOffices", cards);
        return "taxiOfficeSalaryForInterval/update";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("salaryForIntervalForm") SalaryForIntervalForm salaryForIntervalForm){
        TaxiOfficeSalaryForInterval taxiOfficeSalaryForInterval = service.getById(id);
        taxiOfficeSalaryForInterval.setTaxiOffice(taxiOfficeService.getById(salaryForIntervalForm.getEntity()));
        taxiOfficeSalaryForInterval.setSalary(Integer.parseInt(salaryForIntervalForm.getSalary()));
        taxiOfficeSalaryForInterval.setFrom(LocalDate.parse(salaryForIntervalForm.getFrom()));
        taxiOfficeSalaryForInterval.setTo(LocalDate.parse(salaryForIntervalForm.getTo()));
        service.save(taxiOfficeSalaryForInterval);
        return "redirect:/ui/salary/forinterval/taxioffice/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("salaryForIntervalForm", new SalaryForIntervalForm());
        model.addAttribute("taxiOffices",
                taxiOfficeService.getAll()
                        .stream().collect(Collectors.toMap(TaxiOffice::getId, TaxiOffice::getName)));
        return "taxiOfficeSalaryForInterval/create";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(@ModelAttribute("salaryForIntervalForm") SalaryForIntervalForm salaryForIntervalForm) {
        TaxiOfficeSalaryForInterval TaxiOfficeSalaryForInterval = new TaxiOfficeSalaryForInterval();
        TaxiOfficeSalaryForInterval.setTaxiOffice(taxiOfficeService.getById(salaryForIntervalForm.getEntity()));
        TaxiOfficeSalaryForInterval.setSalary(Integer.parseInt(salaryForIntervalForm.getSalary()));
        TaxiOfficeSalaryForInterval.setFrom(LocalDate.parse(salaryForIntervalForm.getFrom()));
        TaxiOfficeSalaryForInterval.setTo(LocalDate.parse(salaryForIntervalForm.getTo()));
        service.save(TaxiOfficeSalaryForInterval);
        return "redirect:/ui/salary/forinterval/taxioffice/get/all";
    }
}
