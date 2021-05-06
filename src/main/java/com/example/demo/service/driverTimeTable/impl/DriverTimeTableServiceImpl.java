package com.example.demo.service.driverTimeTable.impl;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DriverTimeTable;
import com.example.demo.model.OperatorTimeTable;
import com.example.demo.repository.driverTimeTable.DriverTimeTableRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverTimeTable.interfces.IDriverTimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : DriverTimeTableImpl
 **/
@Service
@RequiredArgsConstructor
public class DriverTimeTableServiceImpl implements IDriverTimeTableService, IGenericService<DriverTimeTable> {
    private final DriverTimeTableRepository repository;
    private final DriverServiceImpl driverService;
    private final FakeData fakeData;

    @Override
    public DriverTimeTable getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("driver timetable not found with id: [" + id +"]"));
    }

    @Override
    public List<DriverTimeTable> getAll() {
        return repository.findAll();
    }

    @Override
    public DriverTimeTable save(DriverTimeTable type) {
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
        }
        return repository.save(type);
    }

    @Override
    public DriverTimeTable deleteById(String id) {
        DriverTimeTable driverTimeTable = this.getById(id);
        repository.deleteById(id);
        return driverTimeTable;
    }

//    List<DriverTimeTable> list = new ArrayList<>();
//
//    @PostConstruct
//    void init() {
//        list = fakeData.getOperatorTimeTables();
//        repository.saveAll(list);
//    }
}
