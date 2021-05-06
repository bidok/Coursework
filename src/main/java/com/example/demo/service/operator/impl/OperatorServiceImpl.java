package com.example.demo.service.operator.impl;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Operator;
import com.example.demo.repository.OperatorTimeTable.OperatorTimeTableRepository;
import com.example.demo.repository.operator.OperatorRepository;
import com.example.demo.repository.order.OrderRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.operator.interfces.IOperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : OperatorServiceImpl
 **/
@Service
@RequiredArgsConstructor
public class OperatorServiceImpl implements IOperatorService, IGenericService<Operator> {
    private final OperatorRepository repository;
    private final OperatorTimeTableRepository operatorTimeTableRepository;
    private final OrderRepository orderRepository;
    private final FakeData fakeData;

    @Override
    public Operator getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("operator whith id: [" + id + "] not fount"));
    }

    @Override
    public List<Operator> getAll() {
        return repository.findAll();
    }

    @Override
    public Operator save(Operator type) {
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
        }
        return repository.save(type);
    }

    @Override
    public Operator deleteById(String id) {
        Operator operator = this.getById(id);
        operatorTimeTableRepository.saveAll(operatorTimeTableRepository.findAllByWorkerId(id)
                .stream()
                .peek(item -> item.setWorker(this.getById("60929944301cee4c79df3d6c")))
                .collect(Collectors.toList()));
        orderRepository.saveAll(orderRepository.findAllByOperatorId(id)
                .stream()
                .peek(item -> item.setOperator(this.getById("60929944301cee4c79df3d6c")))
                .collect(Collectors.toList()));
        repository.deleteById(id);
        return operator;
    }


}
