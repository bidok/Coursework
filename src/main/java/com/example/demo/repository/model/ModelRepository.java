package com.example.demo.repository.model;

import com.example.demo.model.Modell;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : bidok
 * @created : 29.04.2021, четверг
 * @className : ModelRepository
 **/
@Repository
public interface ModelRepository extends MongoRepository<Modell, String> {
}
