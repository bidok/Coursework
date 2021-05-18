package com.example.demo.service.taxiOfficeSalaryForInterval.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.form.SalaryForDayForm;
import com.example.demo.model.TaxiOfficeSalaryForDay;
import com.example.demo.model.TaxiOfficeSalaryForInterval;
import com.example.demo.repository.taxiOfficeSalaryForDay.TaxiOfficeSalaryForDayRepository;
import com.example.demo.repository.taxiOfficeSalaryForInterval.TaxiOfficeSalaryForIntervalRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForDay.interfaces.ITaxiOfficeServiceSalaryForDayService;
import com.example.demo.service.taxiOfficeSalaryForInterval.interfaces.ITaxiOfficeServiceSalaryForIntervalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DispatchServiceSalaryForIntervalServiceImpl
 **/
@Service
@RequiredArgsConstructor
public class TaxiOfficeSalaryForIntervalServiceImpl implements ITaxiOfficeServiceSalaryForIntervalService, IGenericService<TaxiOfficeSalaryForInterval> {
    private final TaxiOfficeSalaryForIntervalRepository repository;
    private final TaxiOfficeSalaryForDayRepository taxiOfficeSalaryForDayRepository;
    private final TaxiOfficeServiceImpl taxiOfficeService;
    private final FakeData fakeData;

    @Override
    public TaxiOfficeSalaryForInterval getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("taxi office salary for day  with id: [" + id + "] not found"));
    }

    @Override
    public List<TaxiOfficeSalaryForInterval> getAll() {
        return repository.findAll();
    }

    @Override
    public TaxiOfficeSalaryForInterval save(TaxiOfficeSalaryForInterval modell) {
        List<TaxiOfficeSalaryForInterval> salary = this.getAll();
        if (salary.stream().anyMatch(item -> item.getId().equals(modell.getId()))) {
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }
        modell.setSalary(taxiOfficeSalaryForDayRepository.findAll().stream()
                .filter(item -> item.getCreateTime().isAfter(modell.getFrom()) &&
                        item.getCreateTime().isBefore(modell.getTo())).mapToInt(TaxiOfficeSalaryForDay::getSalary).sum());

        return repository.save(modell);
    }

    @Override
    public TaxiOfficeSalaryForInterval deleteById(String id) {
        TaxiOfficeSalaryForInterval modell = this.getById(id);
        repository.deleteById(id);
        return modell;
    }

    public List<TaxiOfficeSalaryForInterval> getSortedSalaryForSomeInterval(LocalDate from, LocalDate to) {
        return this.getAll().stream()
                .filter(item -> (item.getFrom().equals(from) ||
                                item.getFrom().isAfter(from)) &&
                        (item.getTo().equals(to) ||
                                item.getTo().isBefore(to)))
                .sorted(Comparator.comparing(TaxiOfficeSalaryForInterval::getSalary))
                .collect(Collectors.toList());
    }

}
