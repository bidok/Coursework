package com.example.demo.service.taxiOffice.impls;

import com.example.demo.dao.taxiOffice.impls.TaxiOfficeDAOImpl;
import com.example.demo.dao.taxiOffice.interfaces.ITaxiOfficeDAO;
import com.example.demo.model.TaxiOffice;
import com.example.demo.service.taxiOffice.interfaces.ITaxiOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : bidok
 * @created : 17.02.2021, среда
 * @className : TaxiOfficeServiceImpl
 **/
@Service
public class TaxiOfficeServiceImpl implements ITaxiOfficeService {

    @Autowired
    ITaxiOfficeDAO taxiOfficeDAO;

    @Override
    public TaxiOffice getById(String id) {
        return taxiOfficeDAO.getById(id);
    }

    @Override
    public TaxiOffice create(TaxiOffice taxiOffice) {
        return taxiOfficeDAO.create(taxiOffice);
    }

    @Override
    public TaxiOffice update(TaxiOffice taxiOffice) {
        return taxiOfficeDAO.update(taxiOffice);
    }

    @Override
    public TaxiOffice delete(String id) {
        return taxiOfficeDAO.delete(id);
    }

    @Override
    public List<TaxiOffice> getAll() {
        return taxiOfficeDAO.getAll();
    }

    @Override
    public TaxiOffice getByName(String name) {
        return taxiOfficeDAO.getByName(name);
    }
}
