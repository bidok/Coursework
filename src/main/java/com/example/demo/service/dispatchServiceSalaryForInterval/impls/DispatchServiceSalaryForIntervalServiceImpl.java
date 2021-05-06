package com.example.demo.service.dispatchServiceSalaryForInterval.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DispatchServiceSalaryForDay;
import com.example.demo.model.DispatchServiceSalaryForInterval;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.repository.dispatchServiceSalaryForDay.DispatchServiceSalaryForDayRepository;
import com.example.demo.repository.dispatchSeviceSalaryForInterval.DispatchServiceSalaryForIntervalRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.dispatchServiceSalaryForInterval.interfaces.IDispatchServiceSalaryForIntervalService;
import lombok.RequiredArgsConstructor;
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

    @Override
    public DispatchServiceSalaryForInterval getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("dispatch service salary for day with id: [" + id + "] not found"));
    }

    @Override
    public List<DispatchServiceSalaryForInterval> getAll() {
        return repository.findAll();
    }

    @Override
    public DispatchServiceSalaryForInterval save(DispatchServiceSalaryForInterval modell) {
        if (this.getAll().stream().anyMatch(item -> item.getId().equals(modell.getId()))) {
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }
        modell.setSalary(dispatchServiceSalaryForDayRepository.findAll().stream()
                .filter(item -> item.getCreateTime().isAfter(modell.getFrom()) &&
                        item.getCreateTime().isBefore(modell.getTo())).mapToInt(DispatchServiceSalaryForDay::getSalary).sum());
        return repository.save(modell);
    }

    @Override
    public DispatchServiceSalaryForInterval deleteById(String id) {
        DispatchServiceSalaryForInterval modell = this.getById(id);
        repository.deleteById(id);
        return modell;
    }

}
