package com.example.demo.service.discountCard.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.DiscountCard;
import com.example.demo.model.Driver;
import com.example.demo.repository.customer.CustomerRepository;
import com.example.demo.repository.discountCard.DiscountCardRepository;
import com.example.demo.repository.operator.OperatorRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.discountCard.interfaces.IDiscountCardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : DiscountCardServiceImpl
 **/
@Service
@RequiredArgsConstructor
public class DiscountCardServiceImpl implements IDiscountCardService, IGenericService<DiscountCard> {
    private final DiscountCardRepository repository;
    private final CustomerRepository customerRepository;
    private final FakeData fakeData;

    private static  final Logger LOGGER = LoggerFactory.getLogger(DiscountCardServiceImpl.class);

    @Override
    public DiscountCard getById(String id) {
        LOGGER.info("method get by id [" + id + "] was called");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("discound card with id: [" + id +"] no found"));
    }

    @Override
    public List<DiscountCard> getAll() {
        LOGGER.info("method get all was called");
        return repository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getCardNumber() != null && !item.getCardNumber().equals("undefined"))
                .collect(Collectors.toList());
    }

    @Override
    public DiscountCard save(DiscountCard type) {
        LOGGER.info("method save was called ");
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            LOGGER.info("object with id: [" + type.getId() + "] was updated");
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
        }
        else {
            LOGGER.info("object was created");
            if (this.getAll().stream()
                    .anyMatch(item -> item.getCardNumber().equals(type.getCardNumber()))) {
                throw new InvalidDataException("this card are exist");
            }
        }
        if(Stream.of(type.getDiscount(), type.getCardNumber(), type.getDistance()).anyMatch(Objects::isNull)){
            throw new InvalidDataException("some field in object are null");
        }
        return repository.save(type);
    }

    @Override
    public DiscountCard deleteById(String id) {
        LOGGER.info("method delete was called");
        DiscountCard discountCard = this.getById(id);
        customerRepository.saveAll(customerRepository.findAllByDiscountCardId(id).stream()
                .peek(item -> item.setDiscountCard(this.getById("60929cc7301cee4c79df3d6e")))
                .collect(Collectors.toList()));
        repository.deleteById(id);
        LOGGER.info("object with id:[" + id +"] was deleted");
        return discountCard;
    }

}
