package com.example.demo.controller.ui;

import com.example.demo.form.OperatorTimeTableForm;
import com.example.demo.model.Driver;
import com.example.demo.model.DriverTimeTable;
import com.example.demo.model.Operator;
import com.example.demo.model.OperatorTimeTable;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverTimeTable.impl.DriverTimeTableServiceImpl;
import com.example.demo.service.operator.impl.OperatorServiceImpl;
import com.example.demo.service.operatorTimeTable.impl.OperatorTimeTableServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : OperatorTimeTableUIController
 **/
@Controller
@RequestMapping("ui/timetable/driver")
@RequiredArgsConstructor
public class DriverTimeTableUIController {
    private final DriverTimeTableServiceImpl service;
    private final OperatorServiceImpl operatorService;
    private final DriverServiceImpl driverService;

    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("timeTable", service.getAll());
        return "driverTimeTable/showAll";
    }

    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("timeTable", service.getById(id));
        return "driverTimeTable/showById";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/timetable/driver/get/all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        DriverTimeTable driverTimeTable = service.getById(id);
        OperatorTimeTableForm operatorTimeTableForm = new OperatorTimeTableForm();
        operatorTimeTableForm.setStartWork(driverTimeTable.getStartWork().toString());
        operatorTimeTableForm.setEndWork(driverTimeTable.getEndWork().toString());
        operatorTimeTableForm.setOperator(driverTimeTable.getWorker().getName());
        model.addAttribute("timeTableForm", operatorTimeTableForm);
        model.addAttribute("operators", driverService.getAll().stream().collect(Collectors.toMap(Driver::getId, Driver::getName)));
        return "driverTimeTable/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("timeTableForm") OperatorTimeTableForm operatorTimeTableForm){
        DriverTimeTable timeTable = service.getById(id);
        timeTable.setStartWork(LocalTime.parse(operatorTimeTableForm.getStartWork()));
        timeTable.setEndWork(LocalTime.parse(operatorTimeTableForm.getEndWork()));
        timeTable.setWorker(driverService.getById(operatorTimeTableForm.getOperator()));
        service.save(timeTable);
        return "redirect:/ui/timetable/driver/get/all";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("operatorTimeTableForm", new OperatorTimeTableForm());
        model.addAttribute("operators",
                driverService.getAll().stream()
                        .collect(Collectors.toMap(Driver::getId, Driver::getName)));
        return "driverTimeTable/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("operatorTimeTableForm") OperatorTimeTableForm operatorTimeTableForm) {
        DriverTimeTable operatorTimeTable = new DriverTimeTable();
        operatorTimeTable.setStartWork(LocalTime.parse(operatorTimeTableForm.getStartWork()));
        operatorTimeTable.setEndWork(LocalTime.parse(operatorTimeTableForm.getEndWork()));
        operatorTimeTable.setWorker(driverService.getById(operatorTimeTableForm.getOperator()));
        service.save(operatorTimeTable);
        return "redirect:/ui/timetable/driver/get/all";
    }
}
