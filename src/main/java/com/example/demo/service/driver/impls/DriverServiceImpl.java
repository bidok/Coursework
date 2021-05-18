package com.example.demo.service.driver.impls;

import com.example.demo.dao.driver.impls.DriverDAOImpl;
import com.example.demo.dao.driver.interfaces.IDriverDAO;
import com.example.demo.data.FakeData;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Driver;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.DriverStatisticBySalaryAndOrder;
import com.example.demo.model.TaxiOffice;
import com.example.demo.repository.car.CarRepository;
import com.example.demo.repository.driver.DriverRepository;
import com.example.demo.repository.driverSalaryForDay.DriverSalaryForDayRepository;
import com.example.demo.repository.driverSalaryForInterval.DriverSalaryForIntervalRepository;
import com.example.demo.repository.driverTimeTable.DriverTimeTableRepository;
import com.example.demo.repository.taxiOffice.TaxiOfficeRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.driver.interfaces.IDriverService;
import com.example.demo.service.order.impls.OrderServiceImpl;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 07.04.2021, среда
 * @className : DriverServiceImpl
 **/
@Service
public class DriverServiceImpl implements IDriverService, IGenericService<Driver> {
    @Autowired
    DriverDAOImpl driverDAO;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    DriverTimeTableRepository driverTimeTableRepository;
    @Autowired
    TaxiOfficeServiceImpl taxiOfficeRepository;
    @Autowired
    DriverSalaryForDayRepository driverSalaryForDayRepository;
    @Autowired
    DriverSalaryForIntervalRepository driverSalaryForIntervalRepository;
    @Autowired
    CarRepository carRepository;


    @Autowired
    FakeData data;

    @Override
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver save(Driver o) {
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(o.getId()))){
            o.setUpdateTime(LocalDateTime.now());
            o.setCreateTime(getById(o.getId()).getCreateTime());
        }
        return driverRepository.save((Driver) o);
    }

    @Override
    public Driver getById(String id) {
        return driverRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Driver with id: " + id + " not found"));
    }

    @Override
    public Driver deleteById(String id) {
        Driver driver = this.getById(id);
        driverTimeTableRepository.saveAll(driverTimeTableRepository.findAllByWorkerId(id)
                .stream()
                .peek(item -> {
                    item.setWorker(this.getById("60929746301cee4c79df3d6b"));
                }).collect(Collectors.toList()));
        driverSalaryForDayRepository.saveAll(driverSalaryForDayRepository.findAllByDriverId(id)
                .stream()
                .peek(item -> {
                    item.setDriver(this.getById("60929746301cee4c79df3d6b"));
                }).collect(Collectors.toList()));
        driverSalaryForIntervalRepository.saveAll(driverSalaryForIntervalRepository.findAllByDriverId(id)
                .stream()
                .peek(item -> {
                    item.setDriver(this.getById("60929746301cee4c79df3d6b"));
                }).collect(Collectors.toList()));
        carRepository.saveAll(carRepository.findAllByDriverId(id).stream()
                .peek(item -> {
                    item.setDriver(this.getById("60929746301cee4c79df3d6b"));
                }).collect(Collectors.toList()));
        driverRepository.deleteById(id);
        return driver;
    }

    public List<Driver> getFirst10DriversWithBestMarkForSomeTaxiOffice(String taxiOfficeId){
        return driverRepository.findAllByTaxiOfficeId(taxiOfficeId).stream()
                .sorted(Comparator.comparing(Driver::getMark).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<DriverStatisticBySalaryAndOrder> getDriverStatisticBySalaryAndOrderForSomeTaxiOffice(String taxiOfficeId){
        List<DriverStatisticBySalaryAndOrder> statistic = new ArrayList<>();
        driverRepository.findAllByTaxiOfficeId(taxiOfficeId).forEach(item ->
                new DriverStatisticBySalaryAndOrder(
                        item,
                        driverSalaryForDayRepository.findAllByDriverId(item.getId()).stream().mapToInt(DriverSalaryForDay::getSalary).sum(),
                        orderService.getAll().stream().filter(order -> order.getCar().getDriver().equals(item)).count()
                ));
        return statistic;
    }

    public List<Driver> get10DriverWithBiggestNumOfCompletedOrders(){
        Map<Driver, Long> drivers =  new HashMap<>();
        this.getAll().forEach(driver ->
                drivers.put(driver, orderService.getAll().stream().filter(order -> order.getCar().getDriver().equals(driver) &&
                        order.getCompleted()).count())
        );
        return drivers.entrySet().stream().sorted((Comparator.comparing(Map.Entry::getValue))).map(Map.Entry::getKey).collect(Collectors.toList());
    }

}
