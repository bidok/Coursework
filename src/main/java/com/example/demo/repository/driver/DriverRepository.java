package com.example.demo.repository.driver;

import com.example.demo.model.Driver;
import com.example.demo.model.TaxiOffice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : bidok
 * @created : 29.04.2021, четверг
 * @className : DriverRepository
 **/
@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {
    void deleteAllByTaxiOfficeId(String id);
    List<Driver> findAllByTaxiOfficeId(String id);
}
