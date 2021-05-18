package com.example.demo.repository.car;

import com.example.demo.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : bidok
 * @created : 30.04.2021, пятница
 * @className : CarRepository
 **/
@Repository
public interface CarRepository extends MongoRepository<Car, String> {
    List<Car> findAllByTaxiOfficeId(String id);
    List<Car> findAllByModellId(String id);
    List<Car> findAllByDriverId(String id);
    List<Car> findAllByOrderByModell();

}
