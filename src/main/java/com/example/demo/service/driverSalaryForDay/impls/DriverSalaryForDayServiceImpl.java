package com.example.demo.service.driverSalaryForDay.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DispatchServiceSalaryForDay;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.DriverSalaryForInterval;
import com.example.demo.repository.driverSalaryForDay.DriverSalaryForDayRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverSalaryForDay.interfaces.IDriverSalaryForDayService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Driver;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    private static  final Logger LOGGER = LoggerFactory.getLogger(DriverSalaryForDayServiceImpl.class);

    @Override
    public DriverSalaryForDay getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("driver salary for day with id: [" + id + "] not found"));
    }

    @Override
    public List<DriverSalaryForDay> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(item -> !item.getDriver().getName().equals("undefined"))
                .collect(Collectors.toList());
    }

    @Override
    public DriverSalaryForDay save(DriverSalaryForDay modell) {
        LOGGER.info("method save was called ");
        List<DriverSalaryForDay> salary = this.getAll();
        if (salary.stream().anyMatch(item -> item.getId().equals(modell.getId()))) {
            LOGGER.info("object with id: [" + modell.getId() + "] was updated");
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }
        if (salary.stream().anyMatch(item -> item.getCreateTime().equals(modell.getCreateTime()))){
            throw new InvalidDataException("salary are exist");
        }
        else LOGGER.info("object was created");
        return repository.save(modell);
    }

    @Override
    public DriverSalaryForDay deleteById(String id) {
        LOGGER.info("method delete was called");
        DriverSalaryForDay modell = this.getById(id);
        repository.deleteById(id);
        LOGGER.info("object with id:[" + id +"] was deleted");
        return modell;
    }



    public List<DriverSalaryForDay> getSalaryForSomeIntervalAndSomeTaxiOffice(String taxiOfficeId, LocalDate from, LocalDate to){
        return this.getAll().stream()
                .filter(item -> taxiOfficeId.equals(item.getDriver().getTaxiOffice().getId()))
                .filter(item ->
                        (from.isBefore(item.getCreateTime()) || from.isEqual(item.getCreateTime())) &&
                                (to.isAfter(item.getCreateTime()) || to.isEqual(item.getCreateTime())))
                .collect(Collectors.toList());
    }
}
