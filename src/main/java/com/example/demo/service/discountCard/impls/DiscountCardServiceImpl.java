package com.example.demo.service.discountCard.impls;

import com.example.demo.data.FakeData;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.DiscountCard;
import com.example.demo.model.Driver;
import com.example.demo.repository.customer.CustomerRepository;
import com.example.demo.repository.discountCard.DiscountCardRepository;
import com.example.demo.repository.operator.OperatorRepository;
import com.example.demo.service.IGenericService;
import com.example.demo.service.discountCard.interfaces.IDiscountCardService;
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
 * @className : DiscountCardServiceImpl
 **/
@Service
@RequiredArgsConstructor
public class DiscountCardServiceImpl implements IDiscountCardService, IGenericService<DiscountCard> {
    private final DiscountCardRepository repository;
    private final CustomerRepository customerRepository;
    private final FakeData fakeData;

    @Override
    public DiscountCard getById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("discound card with id: [" + id +"] no found"));
    }

    @Override
    public List<DiscountCard> getAll() {
        return repository.findAll();
    }

    @Override
    public DiscountCard save(DiscountCard type) {
        if(this.getAll().stream().anyMatch(item -> item.getId().equals(type.getId()))){
            type.setUpdateTime(LocalDateTime.now());
            type.setCreateTime(getById(type.getId()).getCreateTime());
        }
        if(this.getAll().stream().anyMatch(item -> item.getCardNumber().equals(type.getCardNumber()))){
            throw new InvalidDataException("this card are exist");
        }
        return repository.save(type);
    }

    @Override
    public DiscountCard deleteById(String id) {
        DiscountCard discountCard = this.getById(id);
        customerRepository.saveAll(customerRepository.findAllByDiscountCardId(id).stream()
                .peek(item -> item.setDiscountCard(this.getById("60929cc7301cee4c79df3d6e")))
                .collect(Collectors.toList()));
        repository.deleteById(id);
        return discountCard;
    }

}
