package com.example.demo.service.taxiOfficeSalaryForInterval.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.form.SalaryForDayForm;
import com.example.demo.form.SalaryForIntervalForm;
import com.example.demo.model.TaxiOffice;
import com.example.demo.model.TaxiOfficeSalaryForDay;
import com.example.demo.model.TaxiOfficeSalaryForInterval;
import com.example.demo.repository.taxiOfficeSalaryForDay.TaxiOfficeSalaryForDayRepository;
import com.example.demo.repository.taxiOfficeSalaryForInterval.TaxiOfficeSalaryForIntervalRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForDay.interfaces.ITaxiOfficeServiceSalaryForDayService;
import com.example.demo.service.taxiOfficeSalaryForInterval.interfaces.ITaxiOfficeServiceSalaryForIntervalService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private static  final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public TaxiOfficeSalaryForInterval getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("taxi office salary for day  with id: [" + id + "] not found"));
    }

    @Override
    public List<TaxiOfficeSalaryForInterval> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll();
    }

    @Override
    public TaxiOfficeSalaryForInterval save(TaxiOfficeSalaryForInterval modell) {
        LOGGER.info("method save was called ");
        List<TaxiOfficeSalaryForInterval> salary = this.getAll();
        if (salary.stream().anyMatch(item -> item.getId().equals(modell.getId()))) {
            LOGGER.info("object with id: [" + modell.getId() + "] was updated");
            modell.setUpdateTime(LocalDateTime.now());
            modell.setCreateTime(getById(modell.getId()).getCreateTime());
        }
        else LOGGER.info("object was created");
        modell.setSalary(taxiOfficeSalaryForDayRepository.findAll().stream()
                .filter(item -> item.getCreateTime().isAfter(modell.getFrom()) &&
                        item.getCreateTime().isBefore(modell.getTo())).mapToInt(TaxiOfficeSalaryForDay::getSalary).sum());

        return repository.save(modell);
    }

    @Override
    public TaxiOfficeSalaryForInterval deleteById(String id) {
        LOGGER.info("method delete was called");
        TaxiOfficeSalaryForInterval modell = this.getById(id);
        repository.deleteById(id);
        LOGGER.info("object with id:[" + id +"] was deleted");
        return modell;
    }

    public List<TaxiOfficeSalaryForInterval> getSortedSalaryForSomeInterval(LocalDate from, LocalDate to) {
        List<TaxiOfficeSalaryForInterval> list = new ArrayList<>();
        taxiOfficeService.getAll().forEach(item -> list.add(getSalaryForInterval(item, from, to)));
    return list;

    }
    public TaxiOfficeSalaryForInterval getSalaryForInterval(TaxiOffice taxiOffice,LocalDate from, LocalDate to){
       Integer salary = taxiOfficeSalaryForDayRepository.findAll().stream()
               .filter(item -> item.getTaxiOffice().getId().equals(taxiOffice.getId()))
                .filter(item -> item.getCreateTime().isAfter(from) &&
                        item.getCreateTime().isBefore(to)).mapToInt(TaxiOfficeSalaryForDay::getSalary).sum();
    return new TaxiOfficeSalaryForInterval(taxiOffice, salary, from, to);
    }

}
