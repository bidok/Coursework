package com.example.demo.service.driverTimeTable.impl;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DispatchServiceSalaryForDay;
import com.example.demo.model.Driver;
import com.example.demo.model.DriverTimeTable;
import com.example.demo.model.OperatorTimeTable;
import com.example.demo.model.TimeTable;
import com.example.demo.repository.driverTimeTable.DriverTimeTableRepository;
import com.example.demo.repository.timeTable.TimeTableArchiveRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverTimeTable.interfces.IDriverTimeTableService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : DriverTimeTableImpl
 **/
@Service
@RequiredArgsConstructor
public class DriverTimeTableServiceImpl implements IDriverTimeTableService, IGenericService<DriverTimeTable> {
    private final DriverTimeTableRepository repository;
    private final TimeTableArchiveRepository timeTableArchiveRepository;

    private static  final Logger LOGGER = LoggerFactory.getLogger(DriverTimeTableServiceImpl.class);

    @Override
    public DriverTimeTable getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("driver timetable not found with id: [" + id +"]"));
    }

    @Override
    public List<DriverTimeTable> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getWorker().getName() != null && !item.getWorker().getName().equals("undefined"))
                .collect(Collectors.toList());
    }

    @Override
    public DriverTimeTable save(DriverTimeTable type) {
        LOGGER.info("method save was called ");
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            LOGGER.info("object with id: [" + type.getId() + "] was updated");
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
        }else LOGGER.info("object was created");
        if(Stream.of(type.getWorker(), type.getEndWork(), type.getStartWork()).anyMatch(Objects::isNull)){
            throw new InvalidDataException("field int this timetable are exist");
        }
        return repository.save(type);
    }

    @Override
    public DriverTimeTable deleteById(String id) {
        LOGGER.info("method delete was called");
        DriverTimeTable driverTimeTable = this.getById(id);
        repository.deleteById(id);
        LOGGER.info("object with id:[" + id +"] was deleted");
        return driverTimeTable;
    }

    public List<Driver> getDriverWhoWorksAtSomeDate(LocalDate date){
       return timeTableArchiveRepository.findAll().stream()
               .filter(item -> item.getClass().equals(DriverTimeTable.class))
               .filter(item -> item.getCreateTime().equals(date))
               .map(item -> (Driver)item.getWorker())
               .collect(Collectors.toList());
    }
}
