package com.example.demo.repository.timeTable;

import com.example.demo.model.TimeTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Vasyl Bidiak
 * @created : 26.05.2021, среда
 * @className : TimeTableArchiveRepository
 **/
@Repository
public interface TimeTableArchiveRepository extends MongoRepository<TimeTable, String> {
}
