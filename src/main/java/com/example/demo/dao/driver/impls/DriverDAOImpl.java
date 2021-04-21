package com.example.demo.dao.driver.impls;

import com.example.demo.dao.driver.interfaces.IDriverDAO;
import com.example.demo.data.FakeData;
import com.example.demo.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : bidok
 * @created : 07.04.2021, среда
 * @className : DriverDAOImpl
 **/

@Repository
public class DriverDAOImpl implements IDriverDAO {

    @Autowired
    FakeData fakeData;

    @Override
    public List<Driver> getAll() {
        return fakeData.getDrivers();
    }

    @Override
    public Driver getById(String id) {
        return this.getAll().stream()
                .filter(item -> item.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public Driver create(Driver driver) {
        String id = String.valueOf(
                        this.getAll().stream()
                            .mapToInt(item -> Integer.parseInt(item.getId()))
                            .max().orElse(0) +1);
        driver.setId(id);
        this.getAll().add(driver);
        return driver;
    }

    @Override
    public Driver update(Driver driver) {
        driver.setUpdateTime(LocalDateTime.now());
        int index = this.getAll().indexOf(this.getById(driver.getId()));
        this.getAll().set(index, driver);
        return driver;
    }

    @Override
    public Driver delete(String id) {
        Driver driver = this.getById(id);
        this.getAll().remove(driver);
        return driver;
    }
}
