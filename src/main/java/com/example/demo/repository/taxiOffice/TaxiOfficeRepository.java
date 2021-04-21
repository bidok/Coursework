package com.example.demo.repository.taxiOffice;

import com.example.demo.model.TaxiOffice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : bidok
 * @created : 03.03.2021, среда
 * @className : TaxiOfficeRepository
 **/

@Repository
public interface TaxiOfficeRepository extends MongoRepository<TaxiOffice, String> {
}
