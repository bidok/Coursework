package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author : bidok
 * @created : 10.02.2021, среда
 * @className : Operator
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Operator extends Audit{
    @Id
    @ApiModelProperty("operator id, must be UUID")
    private String id;
    @ApiModelProperty("operator name, full name")
    private String name;
    @ApiModelProperty(value = "operator phone", example = "+38(xxx)-xxx-xxxx")
    private String phoneNumber;
    @ApiModelProperty(value = "driver identificationCode")
    private String identificationCode;
    @ApiModelProperty("operator pass")
    private String passportNumber;

    public Operator( String name, String phoneNumber, String identificationCode, String passportNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.identificationCode = identificationCode;
        this.passportNumber = passportNumber;
    }
}

