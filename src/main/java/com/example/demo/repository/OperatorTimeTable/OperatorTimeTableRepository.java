package com.example.demo.repository.OperatorTimeTable;

import com.example.demo.model.Operator;
import com.example.demo.model.OperatorTimeTable;
import com.example.demo.model.TimeTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : TimeTableRepository
 **/
@Repository
public interface OperatorTimeTableRepository extends MongoRepository<OperatorTimeTable, String> {
    List<OperatorTimeTable> findAllByWorkerId(String id);
}
