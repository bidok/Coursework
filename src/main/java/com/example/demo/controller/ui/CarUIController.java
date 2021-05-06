package com.example.demo.controller.ui;

import com.example.demo.form.CarForm;
import com.example.demo.form.TaxiOfficeForm;
import com.example.demo.model.Car;
import com.example.demo.model.Driver;
import com.example.demo.model.Modell;
import com.example.demo.model.TaxiOffice;
import com.example.demo.service.car.impl.CarServiceImpl;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.model.impls.ModelServiceImpl;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : CarUIController
 **/
@Controller
@RequestMapping("ui/car")
@RequiredArgsConstructor
public class CarUIController {
    private final CarServiceImpl service;
    private final DriverServiceImpl driverService;
    private final TaxiOfficeServiceImpl taxiOfficeService;
    private final ModelServiceImpl modelService;


    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("cars", service.getAll());
        return "car/showAll";
    }

    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("car", service.getById(id));
        return "car/showById";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/car/get/all";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("carForm", new CarForm());
        model.addAttribute("models", modelService.getAll().stream().collect(Collectors.toMap(Modell::getId, Modell::getName)));
        model.addAttribute("drivers", driverService.getAll().stream().collect(Collectors.toMap(Driver::getId, Driver::getName)));
        model.addAttribute("taxiOffice", taxiOfficeService.getAll().stream().collect(Collectors.toMap(TaxiOffice::getId, TaxiOffice::getName)));
        model.addAttribute("state", new ArrayList<String>(Arrays.asList("true", "false")));
        return "car/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("carForm") CarForm carForm) {
        Car car  = new Car();
        car.setCarNumber(carForm.getCarNumber());
        car.setState(Boolean.valueOf(carForm.getState()));
        car.setLocation(carForm.getLocation());
        car.setInsuranceNumber(carForm.getInsuranceNumber());
        car.setDriver(driverService.getById(carForm.getDriver()));
        car.setTaxiOffice(taxiOfficeService.getById(carForm.getTaxiOffice()));
        car.setModell(modelService.getById(carForm.getModell()));
        service.save(car);
        return "redirect:/ui/car/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        Car car = service.getById(id);
        CarForm carForm = new CarForm();
        carForm.setCarNumber(car.getCarNumber());
        carForm.setState(car.getState().toString());
        carForm.setLocation(car.getLocation());
        carForm.setInsuranceNumber(car.getInsuranceNumber());
        carForm.setDriver(car.getDriver().getName());
        carForm.setTaxiOffice(car.getTaxiOffice().getName());
        carForm.setModell(car.getModell().getName());

        model.addAttribute("carForm", carForm);
        model.addAttribute("models", modelService.getAll().stream().collect(Collectors.toMap(Modell::getId, Modell::getName)));
        model.addAttribute("drivers", driverService.getAll().stream().collect(Collectors.toMap(Driver::getId, Driver::getName)));
        model.addAttribute("taxiOffice", taxiOfficeService.getAll().stream().collect(Collectors.toMap(TaxiOffice::getId, TaxiOffice::getName)));
        model.addAttribute("state", new ArrayList<String>(Arrays.asList("true", "false")));
        return "car/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("carForm") CarForm carForm){
        Car car = service.getById(id);
        car.setCarNumber(carForm.getCarNumber());
        car.setState(Boolean.valueOf(carForm.getState()));
        car.setLocation(carForm.getLocation());
        car.setInsuranceNumber(carForm.getInsuranceNumber());
        car.setDriver(driverService.getById(carForm.getDriver()));
        car.setTaxiOffice(taxiOfficeService.getById(carForm.getTaxiOffice()));
        car.setModell(modelService.getById(carForm.getModell()));
        service.save(car);
        return "redirect:/ui/car/get/all";
    }
}
