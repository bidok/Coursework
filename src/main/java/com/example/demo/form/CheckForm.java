package com.example.demo.form;

import com.example.demo.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * @author : bidok
 * @created : 01.05.2021, суббота
 * @className : CheckForm
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckForm {
    private String checkNumber;
    private String order;
    private String distance;
    private String price;

}
