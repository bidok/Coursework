package com.example.demo.repository.discountCard;

import com.example.demo.model.DiscountCard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : DiscountCardRepository
 **/

public interface DiscountCardRepository extends MongoRepository<DiscountCard, String> {
	DiscountCard findFirstByCardNumber(String cardNumber);
	void deleteAllByCardNumber(String cardNumber);
}
