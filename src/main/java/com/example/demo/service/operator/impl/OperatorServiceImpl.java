package com.example.demo.service.operator.impl;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Operator;
import com.example.demo.repository.OperatorTimeTable.OperatorTimeTableRepository;
import com.example.demo.repository.operator.OperatorRepository;
import com.example.demo.repository.order.OrderRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.operator.interfces.IOperatorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    private static  final Logger LOGGER = LoggerFactory.getLogger(OperatorServiceImpl.class);

    @Override
    public Operator getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("operator whith id: [" + id + "] not fount"));
    }

    @Override
    public List<Operator> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getName() != null && !item.getName().equals("undefined"))
                .collect(Collectors.toList());
    }

    @Override
    public Operator save(Operator type) {
        LOGGER.info("method save was called ");
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            LOGGER.info("object with id: [" + type.getId() + "] was updated");
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
        }else LOGGER.info("object was created");

        return repository.save(type);
    }

    @Override
    public Operator deleteById(String id) {
        LOGGER.info("method delete was called");
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
        LOGGER.info("object with id:[" + id +"] was deleted");
        return operator;
    }


}
