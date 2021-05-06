package com.example.demo.repository.taxiOfficeSalaryForInterval;

import com.example.demo.model.TaxiOfficeSalaryForInterval;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : TaxiOfficeSalaryForIntervalRepository
 **/
@Repository
public interface TaxiOfficeSalaryForIntervalRepository extends MongoRepository<TaxiOfficeSalaryForInterval, String> {
    List<TaxiOfficeSalaryForInterval> findAllByTaxiOfficeId (String id);
}
