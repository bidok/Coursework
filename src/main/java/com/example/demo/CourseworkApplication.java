package com.example.demo;

import com.example.demo.data.FakeData;
import com.example.demo.model.*;
import com.example.demo.repository.dispatchServiceSalaryForDay.DispatchServiceSalaryForDayRepository;
import com.example.demo.repository.driver.DriverRepository;
import com.example.demo.repository.driverSalaryForDay.DriverSalaryForDayRepository;
import com.example.demo.repository.taxiOffice.TaxiOfficeRepository;
import com.example.demo.service.car.impl.CarServiceImpl;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class CourseworkApplication implements CommandLineRunner {

    public static void main(String[] args) {
       SpringApplication.run(CourseworkApplication.class, args);


    }

    @Autowired
    TaxiOfficeRepository repository;
    @Autowired
    DispatchServiceSalaryForDayRepository driverRepository;
private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void run(String... args) throws Exception {


    }
}
