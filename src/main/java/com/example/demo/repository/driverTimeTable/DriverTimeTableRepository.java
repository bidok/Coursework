package com.example.demo.repository.driverTimeTable;

import com.example.demo.model.Driver;
import com.example.demo.model.DriverTimeTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : driverTimeTableRepository
 **/
@Repository
public interface DriverTimeTableRepository extends MongoRepository<DriverTimeTable, String> {
    List<DriverTimeTable> findAllByWorkerId(String id);
}
