package com.example.demo.service.operatorTimeTable.impl;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Driver;
import com.example.demo.model.Operator;
import com.example.demo.model.OperatorTimeTable;
import com.example.demo.model.TimeTable;
import com.example.demo.repository.OperatorTimeTable.OperatorTimeTableRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.operatorTimeTable.interfces.IOperatorTimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public OperatorTimeTable getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("operator timetable not found with id: [" + id +"]"));
    }

    @Override
    public List<OperatorTimeTable> getAll() {
        return repository.findAll();
    }

    @Override
    public OperatorTimeTable save(OperatorTimeTable type) {
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
        }
        return repository.save(type);
    }

    @Override
    public OperatorTimeTable deleteById(String id) {
        OperatorTimeTable operatorTimeTable = this.getById(id);
        repository.deleteById(id);
        return operatorTimeTable;
    }

}



