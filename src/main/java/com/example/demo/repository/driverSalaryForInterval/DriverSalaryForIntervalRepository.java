package com.example.demo.repository.driverSalaryForInterval;

import com.example.demo.model.DriverSalaryForInterval;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DriverSalaryForIntervalRepository
 **/
@Repository
public interface DriverSalaryForIntervalRepository extends MongoRepository<DriverSalaryForInterval, String> {
    List<DriverSalaryForInterval> findAllByDriverId(String id);

}
