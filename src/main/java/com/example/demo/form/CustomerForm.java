package com.example.demo.form;

import com.example.demo.model.DiscountCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : CustomerForm
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerForm {
    private String name;
    private String phoneNumber;
    private String discountCard;
}
