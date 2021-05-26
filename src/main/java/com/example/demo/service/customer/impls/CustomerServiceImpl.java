package com.example.demo.service.customer.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.Customer;
import com.example.demo.model.DiscountCard;
import com.example.demo.model.Order;
import com.example.demo.repository.customer.CustomerRepository;
import com.example.demo.repository.discountCard.DiscountCardRepository;
import com.example.demo.repository.order.OrderRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.customer.interfaces.ICustomerService;
import com.example.demo.service.discountCard.impls.DiscountCardServiceImpl;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private final DiscountCardServiceImpl discountCardService;
    private final DiscountCardRepository discountCardRepository;
    private final FakeData fakeData;

    private static  final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public Customer getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("discound card with id: [" + id +"] no found"));

    }

    @Override
    public List<Customer> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(item -> !item.getName().equals("undefined"))
                .filter(item -> item.getDiscountCard() != null && !item.getDiscountCard().getId().equals("60929d99301cee4c79df3d6f"))
                .collect(Collectors.toList());
    }

    @Override
    public Customer save(Customer type) {
        LOGGER.info("method save was called ");
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            LOGGER.info("object with id: [" + type.getId() + "] was updated");
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
        }
        else {
             LOGGER.info("object was created");
             DiscountCard discountCard = new DiscountCard();
             discountCard.setDiscount(1);
             discountCard.setDistance(1);
             List<String> cards = discountCardService.getAll().stream().map(DiscountCard::getCardNumber).collect(Collectors.toList());
             String number = RandomStringUtils.randomAlphanumeric(10);
             while (cards.contains(number)){
                 number = RandomStringUtils.randomAlphanumeric(10);

             }
            discountCard.setCardNumber(number);
             discountCardService.save(discountCard);

             type.setDiscountCard(discountCardRepository.findFirstByCardNumber(number));

        }
        if(Stream.of(type.getDiscountCard(), type.getName(),type.getPhoneNumber()).anyMatch(Objects::isNull)){
            throw new InvalidDataException("some field in object are null");
        }
        return repository.save(type);
    }

    @Override
    public Customer deleteById(String id) {
        LOGGER.info("method delete was called");
        Customer customer = this.getById(id);
        orderRepository.saveAll(orderRepository.findAllByCustomerId(id).stream()
                .peek(item -> item.setCustomer(this.getById("60929d99301cee4c79df3d6f")))
                .collect(Collectors.toList()));
        discountCardRepository.deleteAllByCardNumber(customer.getDiscountCard().getCardNumber());
        repository.deleteById(id);
        LOGGER.info("object with id:[" + id +"] was deleted");
        return customer;
    }

    public List<Customer> getCustomersWhoCanceledOrder(){
       return orderRepository.findAllByCompletedIsFalse().stream().map(Order::getCustomer).collect(Collectors.toList());
    }

    public List<Customer> get10CustomersWithBiggestDiscount(){
        return this.getAll().stream().sorted((Comparator.comparing(o -> o.getDiscountCard().getDiscount()))).limit(10).collect(Collectors.toList());
    }

}
