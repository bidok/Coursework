package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author : bidok
 * @created : 04.02.2021, четверг
 * @className : driver
 **/
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Driver extends Audit{
    @Id
    @ApiModelProperty("driver id, must be UUID")
    private String id;
    @ApiModelProperty("driver name, full name")
    private String name;
    @ApiModelProperty(value = "driver phone", example = "+38(xxx)-xxx-xxxx")
    private String phone;
    @ApiModelProperty("driver mark, from 1 to 5")
    private Integer mark;
    @ApiModelProperty("driver license, full name")
    private String licenseNumber;
    @DBRef
    @ApiModelProperty("driver taxi office, must be exist")
    private TaxiOffice taxiOffice;

    public Driver( String name, String phone, int mark, String licenseNumber, TaxiOffice taxiOffice) {
        this.name = name;
        this.phone = phone;
        this.mark = mark;
        this.licenseNumber = licenseNumber;
        this.taxiOffice = taxiOffice;
    }

}
