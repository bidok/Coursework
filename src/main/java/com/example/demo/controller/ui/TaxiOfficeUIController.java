package com.example.demo.controller.ui;

import com.example.demo.data.FakeData;
import com.example.demo.form.TaxiOfficeForm;
import com.example.demo.model.TaxiOffice;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import com.example.demo.service.taxiOffice.interfaces.ITaxiOfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : bidok
 * @created : 03.03.2021, среда
 * @className : TaxiOfficeUIController
 **/
@Controller
@RequestMapping("/ui/taxioffice")
public class TaxiOfficeUIController {

    @Autowired
    TaxiOfficeServiceImpl service;

    @Autowired
    FakeData data;

    static final Logger LOGGER = LoggerFactory.getLogger(TaxiOfficeUIController.class);

    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("taxiOffices", service.getAll());
        LOGGER.info("method get all from ui controller for taxi office was called");
        return "taxiOffice/showAll";
    }

    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("taxiOffice", service.getById(id));
        LOGGER.info("method get by id:[" + id + "] from ui controller for taxi office was called");
        return "taxiOffice/showById";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        LOGGER.info("method delete by id:[" + id + "] from ui controller for taxi office was called");
        return "redirect:/ui/taxioffice/get/all";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("taxiOfficeForm", new TaxiOfficeForm());
        LOGGER.info("method create from ui controller for taxi office was called");
        return "taxiOffice/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("taxiOfficeForm") TaxiOfficeForm taxiOfficeForm) {
        TaxiOffice taxiOffice = new TaxiOffice();
        taxiOffice.setName(taxiOfficeForm.getName());
        taxiOffice.setPhoneNumber(taxiOfficeForm.getPhoneNumber());
        taxiOffice.setOwnerName(taxiOfficeForm.getOwnerName());
        taxiOffice.setLicenseNumber(taxiOfficeForm.getLicenseNumber());
        service.save(taxiOffice);
        return "redirect:/ui/taxioffice/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        LOGGER.info("method update from ui controller for taxi office was called");
        TaxiOffice taxiOffice = new TaxiOffice();
        taxiOffice = service.getById(id);
        TaxiOfficeForm taxiOfficeForm = new TaxiOfficeForm(
                taxiOffice.getName(),
                taxiOffice.getPhoneNumber(),
                taxiOffice.getOwnerName(),
                taxiOffice.getLicenseNumber()
                );
        model.addAttribute("taxiOfficeForm", taxiOfficeForm);
        return "taxiOffice/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("taxiOfficeForm") TaxiOfficeForm taxiOfficeForm){
        TaxiOffice taxiOffice = service.getById(id);
        taxiOffice.setId(id);
        taxiOffice.setName(taxiOfficeForm.getName());
        taxiOffice.setPhoneNumber(taxiOfficeForm.getPhoneNumber());
        taxiOffice.setOwnerName(taxiOfficeForm.getOwnerName());
        taxiOffice.setLicenseNumber(taxiOfficeForm.getLicenseNumber());
        service.save(taxiOffice);
        return "redirect:/ui/taxioffice/get/all";
    }
}