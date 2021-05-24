package com.example.demo.service.driverSalaryForInterval.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.DriverSalaryForInterval;
import com.example.demo.model.TaxiOfficeSalaryForDay;
import com.example.demo.repository.driverSalaryForDay.DriverSalaryForDayRepository;
import com.example.demo.repository.driverSalaryForInterval.DriverSalaryForIntervalRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverSalaryForInterval.interfaces.IDriverSalaryForIntervalService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    private static  final Logger LOGGER = LoggerFactory.getLogger(DriverSalaryForIntervalServiceImpl.class);

    @Override
    public DriverSalaryForInterval getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("driver salary for day with id: [" + id + "] not found"));
    }

    @Override
    public List<DriverSalaryForInterval> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll();
    }

    @Override
    public DriverSalaryForInterval save(DriverSalaryForInterval modell) {
        LOGGER.info("method save was called ");
        if (this.getAll().stream().anyMatch(item -> item.getId().equals(modell.getId()))) {
            LOGGER.info("object with id: [" + modell.getId() + "] was updated");
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }
        else LOGGER.info("object was created");

        modell.setSalary(driverSalaryForDayRepository.findAll().stream()
                .filter(item -> item.getCreateTime().isAfter(modell.getFrom()) &&
                        item.getCreateTime().isBefore(modell.getTo())).mapToInt(DriverSalaryForDay::getSalary).sum());
        return repository.save(modell);
    }

    @Override
    public DriverSalaryForInterval deleteById(String id) {
        LOGGER.info("method delete was called");
        DriverSalaryForInterval modell = this.getById(id);
        repository.deleteById(id);
        LOGGER.info("object with id:[" + id +"] was deleted");
        return modell;
    }

    public List<DriverSalaryForInterval> getSalaryForSomeIntervalAndSomeTaxiOffice(String taxiOfficeId, LocalDate from, LocalDate to){
        return this.getAll().stream()
                .filter(item -> item.getDriver().getTaxiOffice().getId().equals(taxiOfficeId) &&
                                (item.getFrom().equals(from) ||
                                    item.getFrom().isAfter(from)) &&
                                (item.getTo().equals(to) ||
                                    item.getTo().isBefore(to)))
                .collect(Collectors.toList());
    }

}
