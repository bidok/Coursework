package com.example.demo.controller.ui;

import com.example.demo.form.DriverForm;
import com.example.demo.form.ModelForm;
import com.example.demo.model.*;
import com.example.demo.repository.model.ModelRepository;
import com.example.demo.service.model.impls.ModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : ModelUIController
 **/
@Controller
@RequestMapping("/ui/model")
public class ModelUIController {

    @Autowired
    ModelServiceImpl modelService;
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/get/all")
    public String getAll(Model model){
        model.addAttribute("modells", modelService.getAll());
        return "model/showAll";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("modell", modelService.getById(id));
        return "model/showById";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        modelService.deleteById(id);
        return "redirect:/ui/model/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        Modell modell = modelService.getById(id);
         ModelForm modelForm = new ModelForm(
                modell.getName(),
                 modell.getMarka().toString(),
                 modell.getCarClass().toString(),
                 modell.getDate().toString()
        );
        model.addAttribute("modelForm", modelForm);
        List<String> markas = Arrays.stream(Marka.values()).map(Marka::toString)
                .collect(Collectors.toList());
        model.addAttribute("markas", markas);
        List<String> carClasses = Arrays.stream(CarClass.values()).map(CarClass::toString)
                .collect(Collectors.toList());
        model.addAttribute("carClasses", carClasses);

        return "model/update";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("modelForm") ModelForm modelForm){
        Modell modell = new Modell();
        modell.setId(id);
        modell.setName(modelForm.getName());
        modell.setMarka(Marka.valueOf(modelForm.getMarka()));
        modell.setCarClass(CarClass.valueOf(modelForm.getCarClass()));
        modell.setDate(LocalDate.parse(modelForm.getDate()));
        modelService.save(modell);
        return "redirect:/ui/model/get/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("modelForm", new ModelForm());

        List<String> markas = Arrays.stream(Marka.values())
                .map(Marka::toString)
                .collect(Collectors.toList());
        model.addAttribute("markas", markas);

        List<String> carClasses = Arrays.stream(CarClass.values())
                .map(CarClass::toString)
                .collect(Collectors.toList());
        model.addAttribute("carClasses", carClasses);

        return "model/create";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(@ModelAttribute("driverForm") ModelForm modelForm) {
        Modell modell = new Modell();
        modell.setName(modelForm.getName());
        modell.setMarka(Marka.valueOf(modelForm.getMarka()));
        modell.setCarClass(CarClass.valueOf(modelForm.getCarClass()));
        modell.setDate(LocalDate.parse(modelForm.getDate()));
        modelService.save(modell);
        return "redirect:/ui/model/get/all";
    }
}
