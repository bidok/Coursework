package com.example.demo.service.check.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Check;
import com.example.demo.model.DispatchServiceSalaryForDay;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.TaxiOfficeSalaryForDay;
import com.example.demo.repository.check.CheckRepository;
import com.example.demo.repository.dispatchServiceSalaryForDay.DispatchServiceSalaryForDayRepository;
import com.example.demo.repository.driverSalaryForDay.DriverSalaryForDayRepository;
import com.example.demo.repository.taxiOfficeSalaryForDay.TaxiOfficeSalaryForDayRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.check.interfaces.ICheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : CheckServiceImpl
 **/
@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements ICheckService, IGenericService<Check> {
    private final CheckRepository repository;
    private final DriverSalaryForDayRepository driverSalaryForDayRepository;
    private final TaxiOfficeSalaryForDayRepository taxiOfficeSalaryForDayRepository;
    private final DispatchServiceSalaryForDayRepository dispatchServiceSalaryForDayRepository;
    private final FakeData fakeData;

    @Override
    public Check getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("check with id: [" + id + "] not found"));
    }

    @Override
    public List<Check> getAll() {
        return repository.findAll();
    }

    @Override
    public Check save(Check type) {
        List<Check> list = this.getAll();
        if(list.stream().anyMatch(item -> item.getId().equals(type.getId()))){
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
        }
        else {
            type.getOrder().getCustomer().getDiscountCard().setDistance(type.getOrder().getCustomer().getDiscountCard().getDistance() + type.getDistance());
            if(type.getOrder().getCustomer().getDiscountCard().getDistance() >= 100){
                type.getOrder().getCustomer().getDiscountCard().setDistance(0);
                type.getOrder().getCustomer().getDiscountCard().setDiscount(type.getOrder().getCustomer().getDiscountCard().getDiscount() + 3);
            }

            if(list.stream().anyMatch(item -> item.getCheckNumber().equals(type.getCheckNumber()))){
                throw new InvalidDataException("order with this number are exist");
            }
        }
        if(type.getOrder().getIsOutOfCity()){
            type.setPrice(type.getDistance() * 10.0);
        }
        //salary calculation, formula = (oldSalary + priceForNewCheck * part(0,25-drv; 0.6-taxOff; 0.150dispServ) - formula * percent / 100)
        else type.setPrice(type.getDistance() * 5.0);
        double discount = type.getOrder().getCustomer().getDiscountCard().getDiscount() / 100;
        //for driver
        DriverSalaryForDay driverSalaryForDay  =  driverSalaryForDayRepository.findAll().stream()
                .filter(item -> item.getDriver().equals(type.getOrder().getCar().getDriver()) &&
                        item.getCreateTime().equals(type.getCreateTime())).findFirst().orElse(null);
        driverSalaryForDay.setSalary((int)((driverSalaryForDay.getSalary() + type.getPrice() * 0.25) - ((driverSalaryForDay.getSalary() + type.getPrice() * 0.25) * discount)));
        driverSalaryForDayRepository.save(driverSalaryForDay);
        //for taxiOffice
        TaxiOfficeSalaryForDay taxiOfficeSalaryForDay =  taxiOfficeSalaryForDayRepository.findAllByTaxiOfficeId(type.getOrder().getCar().getDriver().getTaxiOffice().getId())
                .stream().filter(item -> item.getCreateTime().equals(type.getCreateTime())).findFirst().orElse(null);
        taxiOfficeSalaryForDay.setSalary((int)((taxiOfficeSalaryForDay.getSalary() + type.getPrice() * 0.60) - (taxiOfficeSalaryForDay.getSalary() + type.getPrice() * 0.60) * discount));
        taxiOfficeSalaryForDayRepository.save(taxiOfficeSalaryForDay);
        //for dispatchservice
        DispatchServiceSalaryForDay dispatchServiceSalaryForDay =  dispatchServiceSalaryForDayRepository.findAll().stream()
                .filter(item -> item.getCreateTime().equals(type.getCreateTime())).findFirst().orElse(null);
        dispatchServiceSalaryForDay.setSalary((int)((dispatchServiceSalaryForDay.getSalary() + type.getPrice() * 0.15) - (dispatchServiceSalaryForDay.getSalary() + type.getPrice() * 0.15) * discount));
        dispatchServiceSalaryForDayRepository.save(dispatchServiceSalaryForDay);
        return repository.save(type);
    }

    @Override
    public Check deleteById(String id) {
        Check check = this.getById(id);
        repository.deleteById(id);
        return check;
    }


}
