package com.example.demo.repository.driverSalaryForDay;

import com.example.demo.model.DriverSalaryForDay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DriverSalaryForDayRepository
 **/
@Repository
public interface DriverSalaryForDayRepository extends MongoRepository<DriverSalaryForDay, String> {
    List<DriverSalaryForDay> findAllByCreateTime(Date date);
    List<DriverSalaryForDay> findAllByDriverId(String id);
}
