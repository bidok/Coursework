package com.example.demo.repository.dispatchSeviceSalaryForInterval;

import com.example.demo.model.DispatchServiceSalaryForInterval;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DispatchServiceSalaryForIntervalRepository
 **/
@Repository
public interface DispatchServiceSalaryForIntervalRepository extends MongoRepository<DispatchServiceSalaryForInterval, String> {
}
