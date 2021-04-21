package com.example.demo.service.driver.impls;

import com.example.demo.dao.driver.interfaces.IDriverDAO;
import com.example.demo.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : bidok
 * @created : 07.04.2021, среда
 * @className : DriverServiceImpl
 **/
@Service
public class DriverServiceImpl implements IDriverDAO{
    @Autowired
    IDriverDAO driverDAO;


    @Override
    public List<Driver> getAll() {
        return driverDAO.getAll();
    }

    @Override
    public Driver getById(String id) {
        return driverDAO.getById(id);
    }

    @Override
    public Driver create(Driver driver) {
        return driverDAO.create(driver);
    }

    @Override
    public Driver update(Driver driver) {
        return driverDAO.update(driver);
    }

    @Override
    public Driver delete(String id) {
        return driverDAO.delete(id);
    }
}
