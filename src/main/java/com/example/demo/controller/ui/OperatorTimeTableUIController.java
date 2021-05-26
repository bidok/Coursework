package com.example.demo.controller.ui;

import com.example.demo.form.OperatorTimeTableForm;
import com.example.demo.model.Driver;
import com.example.demo.model.Operator;
import com.example.demo.model.OperatorTimeTable;
import com.example.demo.model.TimeTable;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.operator.impl.OperatorServiceImpl;
import com.example.demo.service.operatorTimeTable.impl.OperatorTimeTableServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : OperatorTimeTableUIController
 **/
@Controller
@RequestMapping("ui/timetable/operator")
@RequiredArgsConstructor
public class OperatorTimeTableUIController {
    private final OperatorTimeTableServiceImpl service;
    private final OperatorServiceImpl operatorService;
    private final DriverServiceImpl driverService;
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/all")
    public String showAll(Model model){
        model.addAttribute("timeTable", service.getAll());
        return "operatorTimeTable/showAll";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/get/{id}")
    public  String getById(@PathVariable String id, Model model){
        model.addAttribute("timeTable", service.getById(id));
        return "operatorTimeTable/showById";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        service.deleteById(id);
        return "redirect:/ui/timetable/operator/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        OperatorTimeTable operatorTimeTable = service.getById(id);
        OperatorTimeTableForm operatorTimeTableForm = new OperatorTimeTableForm();
        operatorTimeTableForm.setStartWork(operatorTimeTable.getStartWork().toString());
        operatorTimeTableForm.setEndWork(operatorTimeTable.getEndWork().toString());
        operatorTimeTableForm.setOperator(operatorTimeTable.getWorker().getName());
        model.addAttribute("timeTableForm", operatorTimeTableForm);
        model.addAttribute("operators", operatorService.getAll().stream().collect(Collectors.toMap(Operator::getId, Operator::getName)));
        return "operatorTimeTable/update";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("timeTableForm") OperatorTimeTableForm operatorTimeTableForm){
        OperatorTimeTable timeTable = service.getById(id);
        timeTable.setStartWork(LocalTime.parse(operatorTimeTableForm.getStartWork()));
        timeTable.setEndWork(LocalTime.parse(operatorTimeTableForm.getEndWork()));
        timeTable.setWorker(operatorService.getById(operatorTimeTableForm.getOperator()));
        service.save(timeTable);
        return "redirect:/ui/timetable/operator/get/all";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("operatorTimeTableForm", new OperatorTimeTableForm());
        model.addAttribute("operators",
                operatorService.getAll().stream()
                        .collect(Collectors.toMap(Operator::getId, Operator::getName)));
        return "operatorTimeTable/create";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(@ModelAttribute("operatorTimeTableForm") OperatorTimeTableForm operatorTimeTableForm) {
        OperatorTimeTable operatorTimeTable = new OperatorTimeTable();
        operatorTimeTable.setStartWork(LocalTime.parse(operatorTimeTableForm.getStartWork()));
        operatorTimeTable.setEndWork(LocalTime.parse(operatorTimeTableForm.getEndWork()));
        operatorTimeTable.setWorker(operatorService.getById(operatorTimeTableForm.getOperator()));
        service.save(operatorTimeTable);
        return "redirect:/ui/timetable/operator/get/all";
    }
}
