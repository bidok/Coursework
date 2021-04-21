package com.example.demo.service.driver.interfaces;

import com.example.demo.model.Driver;
import com.example.demo.model.TaxiOffice;

import java.util.List;

/**
 * @author : bidok
 * @created : 07.04.2021, среда
 * @className : IDriverService
 **/

public interface IDriverService {
    Driver getById(String id);
    Driver create(Driver driver);
    Driver update(Driver driver);
    Driver delete(String id);
    List<Driver> getAll();
}
