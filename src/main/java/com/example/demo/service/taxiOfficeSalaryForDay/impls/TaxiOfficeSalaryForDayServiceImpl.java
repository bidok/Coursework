package com.example.demo.service.taxiOfficeSalaryForDay.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.TaxiOfficeSalaryForDay;
import com.example.demo.repository.taxiOfficeSalaryForDay.TaxiOfficeSalaryForDayRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForDay.interfaces.ITaxiOfficeServiceSalaryForDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public TaxiOfficeSalaryForDay getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("taxi office salary for day  with id: [" + id + "] not found"));
    }

    @Override
    public List<TaxiOfficeSalaryForDay> getAll() {
        return repository.findAll();
    }

    @Override
    public TaxiOfficeSalaryForDay save(TaxiOfficeSalaryForDay modell) {
        if (this.getAll().stream().anyMatch(item -> item.getId().equals(modell.getId()))) {
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }
        return repository.save(modell);
    }

    @Override
    public TaxiOfficeSalaryForDay deleteById(String id) {
        TaxiOfficeSalaryForDay modell = this.getById(id);
        repository.deleteById(id);
        return modell;
    }

    @PostConstruct
    void addNewSalary(){
        if(LocalTime.now().isAfter(LocalTime.of(0,0)) && LocalTime.now().isAfter(LocalTime.of(0,5))){
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
