package com.example.demo.service.driverSalaryForDay.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DispatchServiceSalaryForDay;
import com.example.demo.model.Driver;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.repository.driverSalaryForDay.DriverSalaryForDayRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverSalaryForDay.interfaces.IDriverSalaryForDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DispatchServiceSalaryForDayServiceImpl
 **/
@Service
@RequiredArgsConstructor
public class DriverSalaryForDayServiceImpl implements IDriverSalaryForDayService, IGenericService<DriverSalaryForDay> {
    private final DriverSalaryForDayRepository repository;
    private final DriverServiceImpl driverService;
    private final FakeData fakeData;

    @Override
    public DriverSalaryForDay getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("driver salary for day with id: [" + id + "] not found"));
    }

    @Override
    public List<DriverSalaryForDay> getAll() {
        return repository.findAll();
    }

    @Override
    public DriverSalaryForDay save(DriverSalaryForDay modell) {
        if (this.getAll().stream().anyMatch(item -> item.getId().equals(modell.getId()))) {
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }
        return repository.save(modell);
    }

    @Override
    public DriverSalaryForDay deleteById(String id) {
        DriverSalaryForDay modell = this.getById(id);
        repository.deleteById(id);
        return modell;
    }

    @PostConstruct
    void addNewSalary(){
        if(LocalTime.now().isAfter(LocalTime.of(0,0)) && LocalTime.now().isAfter(LocalTime.of(0,5))){
            if(!(this.getAll().stream().anyMatch(item -> item.getCreateTime().equals(LocalDate.now())))){
                List<DriverSalaryForDay> driverSalaryForDays = new ArrayList<>();
                driverService.getAll().forEach(item ->
                        driverSalaryForDays.add(new DriverSalaryForDay(item, 0)));
                repository.saveAll(driverSalaryForDays);
                driverSalaryForDays.clear();
            }
        }
    }
}
