package com.example.demo.service.taxiOffice.interfaces;

import com.example.demo.model.TaxiOffice;

import java.util.List;

/**
 * @author : bidok
 * @created : 17.02.2021, среда
 * @className : ITaxiService
 **/

public interface ITaxiOfficeService {
    TaxiOffice getById(String id);
    TaxiOffice create(TaxiOffice taxiOffice);
    TaxiOffice update(TaxiOffice taxiOffice);
    TaxiOffice delete(String id);
    List<TaxiOffice> getAll();
    TaxiOffice getByName (String name);
}
