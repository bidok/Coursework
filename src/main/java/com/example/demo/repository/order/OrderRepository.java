package com.example.demo.repository.order;

import com.example.demo.model.Order;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : OrderRepository
**/
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findAllByCarId(String id);
    List<Order> findAllByOperatorId(String id);
    List<Order> findAllByCustomerId(String id);
    List<Order> findAllByCompletedIsFalse();
}
