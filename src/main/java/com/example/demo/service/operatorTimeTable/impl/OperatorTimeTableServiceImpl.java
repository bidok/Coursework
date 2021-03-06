package com.example.demo.service.operatorTimeTable.impl;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Driver;
import com.example.demo.model.Operator;
import com.example.demo.model.OperatorTimeTable;
import com.example.demo.model.TimeTable;
import com.example.demo.repository.OperatorTimeTable.OperatorTimeTableRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.operatorTimeTable.interfces.IOperatorTimeTableService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : OperatorTimeTableImpl
 **/
@Service
@RequiredArgsConstructor
public class OperatorTimeTableServiceImpl implements IOperatorTimeTableService, IGenericService<OperatorTimeTable> {
    private final OperatorTimeTableRepository repository;
    private final FakeData fakeData;

    private static  final Logger LOGGER = LoggerFactory.getLogger(OperatorTimeTableServiceImpl.class);

    @Override
    public OperatorTimeTable getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("operator timetable not found with id: [" + id +"]"));
    }

    @Override
    public List<OperatorTimeTable> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getWorker() != null && !item.getWorker().getName().equals("undefined"))
                .collect(Collectors.toList());
    }

    @Override
    public OperatorTimeTable save(OperatorTimeTable type) {
        LOGGER.info("method save was called ");
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
        }
        else LOGGER.info("object was created");
        if(Stream.of(type.getWorker(), type.getEndWork(), type.getStartWork()).anyMatch(Objects::isNull)){
            throw new InvalidDataException("field int this timetable are exist");
        }
        type.setStartWork(ZonedDateTime.of(LocalDate.now().atTime(type.getStartWork()), ZoneId.of("Europe/Kiev")).toLocalTime());
        type.setEndWork(ZonedDateTime.of(LocalDate.now().atTime(type.getEndWork()),ZoneId.of("Europe/Kiev")).toLocalTime());
        return repository.save(type);
    }

    @Override
    public OperatorTimeTable deleteById(String id) {
        LOGGER.info("method delete was called");
        OperatorTimeTable operatorTimeTable = this.getById(id);
        repository.deleteById(id);
        LOGGER.info("object with id:[" + id +"] was deleted");
        return operatorTimeTable;
    }

}



