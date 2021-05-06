package com.example.demo.repository.check;

import com.example.demo.model.Check;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : CheckRepository
 **/
@Repository
public interface CheckRepository extends MongoRepository<Check, String> {
    List<Check> findAllByOrderId(String id);
}
