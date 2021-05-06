package com.example.demo.service.customer.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.DiscountCard;
import com.example.demo.repository.customer.CustomerRepository;
import com.example.demo.repository.order.OrderRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.customer.interfaces.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : CustomerServiceImpl
 **/
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService, IGenericService<Customer> {
    private final CustomerRepository repository;
    private final OrderRepository orderRepository;
    private final FakeData fakeData;

    @Override
    public Customer getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("discound card with id: [" + id +"] no found"));

    }

    @Override
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @Override
    public Customer save(Customer type) {
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
        }

        return repository.save(type);
    }

    @Override
    public Customer deleteById(String id) {
        Customer customer = this.getById(id);
        orderRepository.saveAll(orderRepository.findAllByCustomerId(id).stream()
                .peek(item -> item.setCustomer(this.getById("60929d99301cee4c79df3d6f")))
                .collect(Collectors.toList()));
        repository.deleteById(id);
        return customer;
    }

}
