package com.example.demo.dao.taxiOffice.interfaces;

import com.example.demo.dao.IGenericDAO;
import com.example.demo.model.TaxiOffice;
import com.example.demo.service.taxiOffice.interfaces.ITaxiOfficeService;

/**
 * @author : bidok
 * @created : 17.02.2021, среда
 * @className : ITaxiServiceDAO
 **/

public interface ITaxiOfficeDAO extends IGenericDAO<TaxiOffice> {
    TaxiOffice getByName(String name);
}
