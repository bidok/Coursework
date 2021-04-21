package com.example.demo.data;


import com.example.demo.model.Driver;
import com.example.demo.model.TaxiOffice;
import org.springframework.stereotype.Repository;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : bidok
 * @created : 10.02.2021, среда
 * @className : FakeData
 **/
@Repository
public class FakeData {

    private List<TaxiOffice> taxiOffices = new ArrayList<>(
            Arrays.asList(
                    new TaxiOffice("1","superTaxi", "+380965632456", "Vasyl", "1234567890"),
                    new TaxiOffice("2","megaTaxi", "+380965632456", "Oleg", "09876543210"),
                    new TaxiOffice("3","justTaxi", "+380965632456", "Alex", "1234098765")
            )
    );

    private List<Driver> drivers = new ArrayList<>(
            Arrays.asList(
                    new Driver("1","oleg", "0996238756", 4, "123459877678", taxiOffices.get(0)),
                    new Driver("2","vasia", "0996784321", 5, "0987654321", taxiOffices.get(1)),
                    new Driver("3","artur", "0966256756", 4, "1243098756", taxiOffices.get(0)),
                    new Driver("4","andrii", "0956738756", 4, "45709812344", taxiOffices.get(2))
            )
    );



    public List<Driver> getDrivers(){
        return  drivers;
    }

    public List<TaxiOffice> getTaxiServices() {
        return taxiOffices;
    }




}
