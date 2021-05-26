package com.example.demo.service.taxiOfficeSalaryForDay.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.TaxiOffice;
import com.example.demo.model.TaxiOfficeSalaryForDay;
import com.example.demo.model.TaxiOfficeSalaryForInterval;
import com.example.demo.repository.taxiOfficeSalaryForDay.TaxiOfficeSalaryForDayRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForDay.interfaces.ITaxiOfficeServiceSalaryForDayService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DispatchServiceSalaryForDayServiceImpl
 **/
@Service
@RequiredArgsConstructor
public class TaxiOfficeSalaryForDayServiceImpl implements ITaxiOfficeServiceSalaryForDayService, IGenericService<TaxiOfficeSalaryForDay> {
    private final TaxiOfficeSalaryForDayRepository repository;
    private final TaxiOfficeServiceImpl taxiOfficeService;
    private final FakeData fakeData;

    private static  final Logger LOGGER = LoggerFactory.getLogger(TaxiOfficeSalaryForDayServiceImpl.class);


    @Override
    public TaxiOfficeSalaryForDay getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("taxi office salary for day  with id: [" + id + "] not found"));
    }

    @Override
    public List<TaxiOfficeSalaryForDay> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(item -> !item.getTaxiOffice().getName().equals("undefined"))
                .collect(Collectors.toList());
    }

    @Override
    public TaxiOfficeSalaryForDay save(TaxiOfficeSalaryForDay modell) {
        LOGGER.info("method save was called ");
        List<TaxiOfficeSalaryForDay> salary = this.getAll();
        if (salary.stream().anyMatch(item -> item.getId().equals(modell.getId()))) {
            LOGGER.info("object with id: [" + modell.getId() + "] was updated");
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }else LOGGER.info("object was created");
        if (salary.stream().anyMatch(item -> item.getCreateTime().equals(modell.getCreateTime()))){
            throw new InvalidDataException("salary are exist");
        }
        return repository.save(modell);
    }

    @Override
    public TaxiOfficeSalaryForDay deleteById(String id) {
        LOGGER.info("method delete was called");
        TaxiOfficeSalaryForDay modell = this.getById(id);
        repository.deleteById(id);
        LOGGER.info("object with id:[" + id +"] was deleted");
        return modell;
    }

    @PostConstruct
    void addNewSalary(){
        if(LocalTime.now().isAfter(LocalTime.of(0,0)) && LocalTime.now().isBefore(LocalTime.of(0,5))){
            if(!(this.getAll().stream().anyMatch(item -> item.getCreateTime().equals(LocalDate.now())))){
                List<TaxiOfficeSalaryForDay> taxiOfficeSalaryForDays = new ArrayList<>();
                taxiOfficeService.getAll().forEach(item ->
                        taxiOfficeSalaryForDays.add(new TaxiOfficeSalaryForDay(item ,0)));
                repository.saveAll(taxiOfficeSalaryForDays);
                taxiOfficeSalaryForDays.clear();
            }
        }
    }


}
