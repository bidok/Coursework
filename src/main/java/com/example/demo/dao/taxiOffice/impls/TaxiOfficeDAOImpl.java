package com.example.demo.dao.taxiOffice.impls;

import com.example.demo.dao.IGenericDAO;
import com.example.demo.dao.taxiOffice.interfaces.ITaxiOfficeDAO;
import com.example.demo.data.FakeData;
import com.example.demo.model.TaxiOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : bidok
 * @created : 17.02.2021, среда
 * @className : TaxiServiceDAOImpl
 **/
@Repository
public class TaxiOfficeDAOImpl implements ITaxiOfficeDAO {

    @Autowired
    FakeData fakeData;//фейкова база данних

    @Override
    public TaxiOffice getById(String id) {
        return this.getAll().stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public TaxiOffice create(TaxiOffice taxiOffice) {
       String id = String.valueOf(this.getAll().stream()
               .mapToInt(item -> Integer.parseInt(item.getId())).max().orElse(0) +1);
       taxiOffice.setId(id);
       this.getAll().add(taxiOffice);
       return taxiOffice;
    }

    @Override
    public TaxiOffice update(TaxiOffice taxiOffice) {
        taxiOffice.setUpdateTime(LocalDateTime.now());
        int index = this.getAll().indexOf(this.getById(taxiOffice.getId()));
        this.getAll().set(index, taxiOffice);
        return taxiOffice;
    }

    @Override
    public TaxiOffice delete(String id) {
        TaxiOffice taxiOffice = this.getById(id);
        this.getAll().remove(taxiOffice);
        return taxiOffice;
    }

    @Override
    public List<TaxiOffice> getAll() {
        return fakeData.getTaxiServices();
    }

    @Override
    public TaxiOffice getByName(String name) {
        return this.getAll()
                .stream()
                .filter(item -> item.getName().equals(name))
                .findFirst().orElse(null);
    }
}
