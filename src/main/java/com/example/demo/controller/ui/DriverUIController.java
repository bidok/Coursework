package com.example.demo.controller.ui;

import com.example.demo.dao.taxiOffice.impls.TaxiOfficeDAOImpl;
import com.example.demo.data.FakeData;
import com.example.demo.form.DriverForm;
import com.example.demo.form.TaxiOfficeForm;
import com.example.demo.model.Driver;
import com.example.demo.model.TaxiOffice;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driver.interfaces.IDriverService;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import freemarker.cache.FileTemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 07.04.2021, среда
 * @className : DriverUIController
 **/

@Controller
@RequestMapping("/ui/driver")
public class DriverUIController {
    @Autowired
    DriverServiceImpl service;

    @Autowired
    TaxiOfficeServiceImpl taxiOfficeService;

    @Autowired
    FakeData fakeData;

    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("driver", service.getAll());
        return "driver/showAll";
    }

    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("driver", service.getById(id));
        return "driver/showById";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.delete(id);
        return "redirect:/ui/driver/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        Driver driver = new Driver();
        driver = service.getById(id);
        DriverForm driverForm = new DriverForm(
                driver.getName(),
                driver.getPhone(),
                driver.getMark().toString(),
                driver.getLicenseNumber(),
                driver.getTaxiOffice().getName()
        );
        model.addAttribute("driverForm", driverForm);
        Map<String, String> taxiOffices = taxiOfficeService.getAll()
                .stream().collect(Collectors.toMap(TaxiOffice::getId, TaxiOffice::getName));
        model.addAttribute("taxiOffices", taxiOffices);
        return "driver/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("driverForm") DriverForm driverForm){
        Driver driver = new Driver();
        driver.setId(id);
        driver.setName(driverForm.getName());
        driver.setPhone(driverForm.getPhone());
        driver.setMark(Integer.parseInt(driverForm.getMark()));
        driver.setLicenseNumber(driverForm.getLicenseNumber());
        driver.setTaxiOffice(taxiOfficeService.getById(driverForm.getTaxiOffice()));
        service.update(driver);
        return "redirect:/ui/driver/get/all";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("driverForm", new DriverForm());
        model.addAttribute("taxiOffices",
                taxiOfficeService.getAll().stream()
                        .collect(Collectors.toMap(TaxiOffice::getId, TaxiOffice::getName)));
        return "driver/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("driverForm") DriverForm driverForm) {
        Driver driver = new Driver();
        driver.setName(driverForm.getName());
        driver.setPhone(driverForm.getPhone());
        driver.setMark(Integer.parseInt(driverForm.getMark()));
        driver.setLicenseNumber(driverForm.getLicenseNumber());
        driver.setTaxiOffice(taxiOfficeService.getById(driverForm.getTaxiOffice()));
        service.create(driver);
        return "redirect:/ui/driver/get/all";
    }

}
