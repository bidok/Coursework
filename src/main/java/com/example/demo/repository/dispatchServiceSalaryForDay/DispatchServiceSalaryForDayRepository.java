package com.example.demo.repository.dispatchServiceSalaryForDay;

import com.example.demo.model.DispatchServiceSalaryForDay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @author : bidok
 * @created : 02.05.2021, воскресенье
 * @className : DispatchServiceSalaryForDayRepository
 **/
@Repository
public interface DispatchServiceSalaryForDayRepository extends MongoRepository<DispatchServiceSalaryForDay, String> {
    DispatchServiceSalaryForDay getDispatchServiceSalaryForDayByCreateTime(LocalDateTime createdTime);
}
