package com.example.demo.repository.customer;

import com.example.demo.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : CustomerRepository
 **/
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findAllByDiscountCardId(String id);

}
