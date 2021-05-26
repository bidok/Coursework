package com.example.demo;

import com.example.demo.data.FakeData;
import com.example.demo.model.*;
import com.example.demo.repository.car.CarRepository;
import com.example.demo.repository.dispatchServiceSalaryForDay.DispatchServiceSalaryForDayRepository;
import com.example.demo.repository.driver.DriverRepository;
import com.example.demo.repository.driverSalaryForDay.DriverSalaryForDayRepository;
import com.example.demo.repository.driverSalaryForInterval.DriverSalaryForIntervalRepository;
import com.example.demo.repository.order.OrderRepository;
import com.example.demo.repository.taxiOffice.TaxiOfficeRepository;
import com.example.demo.repository.taxiOfficeSalaryForDay.TaxiOfficeSalaryForDayRepository;
import com.example.demo.repository.timeTable.TimeTableArchiveRepository;
import com.example.demo.service.car.impl.CarServiceImpl;
import com.example.demo.service.check.impls.CheckServiceImpl;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverSalaryForDay.impls.DriverSalaryForDayServiceImpl;
import com.example.demo.service.driverSalaryForInterval.impls.DriverSalaryForIntervalServiceImpl;
import com.example.demo.service.driverTimeTable.impl.DriverTimeTableServiceImpl;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForDay.impls.TaxiOfficeSalaryForDayServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForInterval.impls.TaxiOfficeSalaryForIntervalServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableScheduling
public class CourseworkApplication implements CommandLineRunner {

    public static void main(String[] args) {
       SpringApplication.run(CourseworkApplication.class, args);


    }


    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    TimeTableArchiveRepository timeTableArchiveRepository;

    @Override
    public void run(String... args) throws Exception {
        {



        }

    }
}

