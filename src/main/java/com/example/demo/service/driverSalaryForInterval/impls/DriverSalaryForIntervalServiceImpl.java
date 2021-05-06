package com.example.demo.service.driverSalaryForInterval.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.DriverSalaryForInterval;
import com.example.demo.model.TaxiOfficeSalaryForDay;
import com.example.demo.repository.driverSalaryForDay.DriverSalaryForDayRepository;
import com.example.demo.repository.driverSalaryForInterval.DriverSalaryForIntervalRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverSalaryForInterval.interfaces.IDriverSalaryForIntervalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DispatchServiceSalaryForDayServiceImpl
 **/
@Service
@RequiredArgsConstructor
public class DriverSalaryForIntervalServiceImpl implements IDriverSalaryForIntervalService, IGenericService<DriverSalaryForInterval> {
    private final DriverSalaryForIntervalRepository repository;
    private final DriverSalaryForDayRepository driverSalaryForDayRepository;
    private final DriverServiceImpl driverService;
    private final FakeData fakeData;

    @Override
    public DriverSalaryForInterval getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("driver salary for day with id: [" + id + "] not found"));
    }

    @Override
    public List<DriverSalaryForInterval> getAll() {
        return repository.findAll();
    }

    @Override
    public DriverSalaryForInterval save(DriverSalaryForInterval modell) {
        if (this.getAll().stream().anyMatch(item -> item.getId().equals(modell.getId()))) {
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }
        modell.setSalary(driverSalaryForDayRepository.findAll().stream()
                .filter(item -> item.getCreateTime().isAfter(modell.getFrom()) &&
                        item.getCreateTime().isBefore(modell.getTo())).mapToInt(DriverSalaryForDay::getSalary).sum());
        return repository.save(modell);
    }

    @Override
    public DriverSalaryForInterval deleteById(String id) {
        DriverSalaryForInterval modell = this.getById(id);
        repository.deleteById(id);
        return modell;
    }

//
}
