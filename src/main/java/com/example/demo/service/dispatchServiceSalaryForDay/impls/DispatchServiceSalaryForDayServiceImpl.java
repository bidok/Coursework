package com.example.demo.service.dispatchServiceSalaryForDay.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DispatchServiceSalaryForDay;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.repository.dispatchServiceSalaryForDay.DispatchServiceSalaryForDayRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.dispatchServiceSalaryForDay.interfaces.IDispatchServiceSalaryForDayService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private static  final Logger LOGGER = LoggerFactory.getLogger(DispatchServiceSalaryForDayServiceImpl.class);

    @Override
    public DispatchServiceSalaryForDay getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("dispatch service salary for day with id: [" + id + "] not found"));
    }

    @Override
    public List<DispatchServiceSalaryForDay> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll();
    }

    @Override
    public DispatchServiceSalaryForDay save(DispatchServiceSalaryForDay modell) {
        LOGGER.info("method save was called ");
        if (this.getAll().stream().anyMatch(item -> item.getId().equals(modell.getId()))) {
            LOGGER.info("object with id: [" + modell.getId() + "] was updated");
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        } else LOGGER.info("object was created");
        return repository.save(modell);
    }

    @Override
    public DispatchServiceSalaryForDay deleteById(String id) {
        LOGGER.info("method delete was called");
        DispatchServiceSalaryForDay modell = this.getById(id);
        repository.deleteById(id);
        LOGGER.info("object with id:[" + id +"] was deleted");
        return modell;
    }



    @PostConstruct
    void addNewSalary(){
        if(LocalTime.now().isAfter(LocalTime.of(0,0)) && LocalTime.now().isAfter(LocalTime.of(0,5))){
            if(!(this.getAll().stream().anyMatch(item -> item.getCreateTime().equals(LocalDate.now())))){
                this.save(new DispatchServiceSalaryForDay(0));
            }
        }
    }

}
