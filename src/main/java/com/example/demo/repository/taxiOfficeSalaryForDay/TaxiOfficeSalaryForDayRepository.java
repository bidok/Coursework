package com.example.demo.repository.taxiOfficeSalaryForDay;

import com.example.demo.model.TaxiOfficeSalaryForDay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : TaxiOfficeSalaryForDayRepository
 **/
@Repository
public interface TaxiOfficeSalaryForDayRepository extends MongoRepository<TaxiOfficeSalaryForDay, String> {
    TaxiOfficeSalaryForDay getTaxiOfficeSalaryForDayByCreateTime(LocalDateTime createdTime);
    List<TaxiOfficeSalaryForDay> findAllByTaxiOfficeId(String id);
}
