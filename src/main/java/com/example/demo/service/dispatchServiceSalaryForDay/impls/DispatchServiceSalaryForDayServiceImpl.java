package com.example.demo.service.dispatchServiceSalaryForDay.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DispatchServiceSalaryForDay;
import com.example.demo.repository.dispatchServiceSalaryForDay.DispatchServiceSalaryForDayRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.dispatchServiceSalaryForDay.interfaces.IDispatchServiceSalaryForDayService;
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
public class DispatchServiceSalaryForDayServiceImpl implements IDispatchServiceSalaryForDayService, IGenericService<DispatchServiceSalaryForDay> {
    private final DispatchServiceSalaryForDayRepository repository;
    private final FakeData fakeData;

    @Override
    public DispatchServiceSalaryForDay getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("dispatch service salary for day with id: [" + id + "] not found"));
    }

    @Override
    public List<DispatchServiceSalaryForDay> getAll() {
        return repository.findAll();
    }

    @Override
    public DispatchServiceSalaryForDay save(DispatchServiceSalaryForDay modell) {
        if (this.getAll().stream().anyMatch(item -> item.getId().equals(modell.getId()))) {
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }
        return repository.save(modell);
    }

    @Override
    public DispatchServiceSalaryForDay deleteById(String id) {
        DispatchServiceSalaryForDay modell = this.getById(id);
        repository.deleteById(id);
        return modell;
    }

}
