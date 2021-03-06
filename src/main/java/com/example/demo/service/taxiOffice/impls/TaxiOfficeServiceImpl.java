package com.example.demo.service.taxiOffice.impls;

import com.example.demo.dao.taxiOffice.impls.TaxiOfficeDAOImpl;
import com.example.demo.dao.taxiOffice.interfaces.ITaxiOfficeDAO;
import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.car.CarRepository;
import com.example.demo.repository.driver.DriverRepository;
import com.example.demo.repository.order.OrderRepository;
import com.example.demo.repository.taxiOffice.TaxiOfficeRepository;
import com.example.demo.repository.taxiOfficeSalaryForDay.TaxiOfficeSalaryForDayRepository;
import com.example.demo.repository.taxiOfficeSalaryForInterval.TaxiOfficeSalaryForIntervalRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.order.impls.OrderServiceImpl;
import com.example.demo.service.taxiOffice.interfaces.ITaxiOfficeService;
import com.example.demo.service.taxiOfficeSalaryForDay.impls.TaxiOfficeSalaryForDayServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForInterval.impls.TaxiOfficeSalaryForIntervalServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : bidok
 * @created : 17.02.2021, среда
 * @className : TaxiOfficeServiceImpl
 **/
@Service
public class TaxiOfficeServiceImpl implements ITaxiOfficeService, IGenericService<TaxiOffice> {

    @Autowired
    TaxiOfficeDAOImpl taxiOfficeDAO;
    @Autowired
    TaxiOfficeRepository taxiOfficeRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    CarRepository carRepository;
    @Autowired
    TaxiOfficeSalaryForDayRepository taxiOfficeSalaryForDayRepository;
    @Autowired
    TaxiOfficeSalaryForIntervalRepository taxiOfficeSalaryForIntervalRepository;

    private static  final Logger LOGGER = LoggerFactory.getLogger(TaxiOfficeServiceImpl.class);


    @Override
    public TaxiOffice getById(String id) {
        LOGGER.info("method get by id [" + id + "] for taxi office was called");
        return taxiOfficeRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("taxi office whit id: [" + id + "] not found");
           return new ObjectNotFoundException("taxi office whit id: [" + id + "] not found");
        });
    }

    @Override
    public List<TaxiOffice> getAll() {
        LOGGER.info("method get all for taxi office was called");
        return  taxiOfficeRepository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getName() != null && !item.getName().equals("undefined"))
                .collect(Collectors.toList());
    }

    @Override
    public TaxiOffice save(TaxiOffice type) {
        LOGGER.info("method save was called for taxi office");
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
            LOGGER.info("tax office with id: [" + type.getId() + "] was updated");
        } else LOGGER.info("taxi office was created");
        if(Stream.of(type.getName(), type.getOwnerName(), type.getLicenseNumber(), type.getPhoneNumber()).anyMatch(Objects::isNull)){
            throw new InvalidDataException("some field in object are null");
        }
        return taxiOfficeRepository.save(type);
    }

    @Override
    public TaxiOffice deleteById(String id) {
        LOGGER.info("method delete for taxi office was called");
        TaxiOffice taxiOffice = this.getById(id);
        driverRepository.saveAll(
                driverRepository.findAllByTaxiOfficeId(id).stream()
                .peek(item -> {
                    item.setTaxiOffice(this.getById("6092554a5a6ed14a1e444ed6"));
                    LOGGER.info("taxi office of driver with id[" +item.getId() +"] was deleted");
                }).collect(Collectors.toList())
        );
        carRepository.saveAll(
                carRepository.findAllByTaxiOfficeId(id).stream()
                        .peek(item -> {
                                    item.setTaxiOffice(this.getById("6092554a5a6ed14a1e444ed6"));
                                    LOGGER.info("taxi office of car with id[" +item.getId() +"] was deleted");
                                }).collect(Collectors.toList()));
        taxiOfficeSalaryForDayRepository.saveAll(
                taxiOfficeSalaryForDayRepository.findAllByTaxiOfficeId(id).stream()
                        .peek(item -> {
                                item.setTaxiOffice(this.getById("6092554a5a6ed14a1e444ed6"));
                        }).collect(Collectors.toList()));
        taxiOfficeSalaryForIntervalRepository.saveAll(
                taxiOfficeSalaryForIntervalRepository.findAllByTaxiOfficeId(id).stream()
                .peek(item -> {
                    item.setTaxiOffice(this.getById("6092554a5a6ed14a1e444ed6"));
                }).collect(Collectors.toList()));
        LOGGER.info("taxi office with id:[" + id +"] was deleted");
        taxiOfficeRepository.deleteById(id);
        return taxiOffice;
    }

    public IntSummaryStatistics getStatisticSameTaxiOfficeByDriverMarks(String taxiOfficeId){
       return driverRepository.findAllByTaxiOfficeId(taxiOfficeId).stream()
                .filter(item -> !item.getName().equals("undefined"))
                .collect(Collectors.summarizingInt(Driver::getMark));
    }


    public List<Long> getStatisticForSomeTaxiOfficeByOrders(String taxiOfficeId){
        List<Long> list = new ArrayList<>();
        List<Order> orders = orderService.getAll().stream().filter(item -> item.getCar().getTaxiOffice().getId().equals(taxiOfficeId)).collect(Collectors.toList());
        list.add(orders.stream().filter(Order::getCompleted).count());
        list.add(orders.stream().filter(item -> !item.getCompleted()).count());
        return  list;
    }
    public List<TaxiOfficeStatisticBySalaryAndOrders> getStatistic(){
        List<TaxiOfficeStatisticBySalaryAndOrders> statistic = new ArrayList<>();
        this.getAll().forEach(item ->
            statistic.add(new TaxiOfficeStatisticBySalaryAndOrders(
                    item,
                    taxiOfficeSalaryForDayRepository.findAllByTaxiOfficeId(item.getId()).stream().mapToInt(TaxiOfficeSalaryForDay::getSalary).sum(),
                    orderService.getAll().stream().filter(order -> order.getCar().getTaxiOffice().getId().equals(item.getId()) && order.getCompleted()).count(),
                    orderService.getAll().stream().filter(order -> order.getCar().getTaxiOffice().getId().equals(item.getId()) && !order.getCompleted()).count()
            ))
        );
        return statistic;
    }
}
