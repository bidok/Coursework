package com.example.demo.service.dispatchServiceSalaryForInterval.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DispatchServiceSalaryForDay;
import com.example.demo.model.DispatchServiceSalaryForInterval;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.repository.dispatchServiceSalaryForDay.DispatchServiceSalaryForDayRepository;
import com.example.demo.repository.dispatchSeviceSalaryForInterval.DispatchServiceSalaryForIntervalRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.dispatchServiceSalaryForInterval.interfaces.IDispatchServiceSalaryForIntervalService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DispatchServiceSalaryForDayServiceImpl
 **/
@Service
@RequiredArgsConstructor
public class DispatchServiceSalaryForIntervalServiceImpl implements IDispatchServiceSalaryForIntervalService, IGenericService<DispatchServiceSalaryForInterval> {
    private final DispatchServiceSalaryForIntervalRepository repository;
    private final DispatchServiceSalaryForDayRepository dispatchServiceSalaryForDayRepository;
    private final FakeData fakeData;

    private static  final Logger LOGGER = LoggerFactory.getLogger(DispatchServiceSalaryForIntervalServiceImpl.class);

    @Override
    public DispatchServiceSalaryForInterval getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("dispatch service salary for day with id: [" + id + "] not found"));
    }

    @Override
    public List<DispatchServiceSalaryForInterval> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll();
    }

    @Override
    public DispatchServiceSalaryForInterval save(DispatchServiceSalaryForInterval modell) {
        LOGGER.info("method save was called ");
        if (this.getAll().stream().anyMatch(item -> item.getId().equals(modell.getId()))) {
            LOGGER.info("object with id: [" + modell.getId() + "] was updated");
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }
        else LOGGER.info("object was created");
        modell.setSalary(dispatchServiceSalaryForDayRepository.findAll().stream()
                .filter(item -> item.getCreateTime().isAfter(modell.getFrom()) &&
                        item.getCreateTime().isBefore(modell.getTo())).mapToInt(DispatchServiceSalaryForDay::getSalary).sum());
        return repository.save(modell);
    }

    @Override
    public DispatchServiceSalaryForInterval deleteById(String id) {
        LOGGER.info("method delete was called");
        DispatchServiceSalaryForInterval modell = this.getById(id);
        repository.deleteById(id);
        LOGGER.info("object with id:[" + id +"] was deleted");
        return modell;
    }

}
