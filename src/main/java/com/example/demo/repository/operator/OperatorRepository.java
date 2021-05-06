package com.example.demo.repository.operator;

import com.example.demo.model.Operator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : OperatorRepository
 **/
@Repository
public interface OperatorRepository extends MongoRepository<Operator, String> {
}
