package com.example.demo.data;


import com.example.demo.model.*;
import com.example.demo.repository.taxiOffice.TaxiOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.jws.WebParam;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Autowired
    TaxiOfficeRepository taxiOfficeRepository;

    private List<TaxiOffice> taxiOffices = new ArrayList<>(
            Arrays.asList(
                    new TaxiOffice("608abc4e3fceec0af3849f35" ,"superTaxi", "+380965632456", "Vasyl", "1234567890"),
                    new TaxiOffice("megaTaxi", "+380965632456", "Oleg", "09876543210"),
                    new TaxiOffice("justTaxi", "+380965632456", "Alex", "1234098765")
            )
    );

    private List<Driver> drivers = new ArrayList<>(Arrays.asList(
            new Driver("608ac682aa36226ea359afb5",
                    "oleg",
                    "0996238756",
                    (long)5,
                    "123459877678",
                    taxiOffices.get(0)
                    )
    ));

    private List<Modell> modells = new ArrayList<>(Arrays.asList(
            new Modell("608b0339fa5be03093af15a7","2107", Marka.Mazda, CarClass.Econom ,LocalDate.now()),
            new Modell("2107", Marka.Mazda,LocalDate.now(), CarClass.Econom)
    ));

    List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car( "608bbe6c40401775f59a3392",   "some test number", false,"some place", "ssome number",
                    taxiOffices.get(0), drivers.get(0), modells.get(0)
                    )
    ));

    List<Operator> operators = new ArrayList<>(Arrays.asList(
            new Operator("608bfb8cd447796b7c130e9d","some name", "some number", "some code", "some pass")
    ));

    List<DriverTimeTable> operatorTimeTables = new ArrayList<>(Arrays.asList(
            new DriverTimeTable(LocalTime.now(), LocalTime.now(), drivers.get(0))
    ));

    List<DiscountCard> discountCards = new ArrayList<>(Arrays.asList(
            new DiscountCard("608d4daefd842705a3b3aff6" ,"1234", 1, 50)
    ));

    List<Customer> customers = new ArrayList<>(Arrays.asList(
       new Customer("608d52febd6c412f8bacc340" ,"alex", "123354353", discountCards.get(0))
    ));

    List<Order> orders = new ArrayList<>(Arrays.asList(
            new Order("608d711aecb6683062d31724",
                    "1234567890",
                    "addres start",
                    "address end",
                    false,
                    customers.get(0),
                    operators.get(0),
                    cars.get(0),
                  //  drivers.get(0),
                    false

                    )
    ));

    List<Check> checks = new ArrayList<>(Arrays.asList(
            new Check("number", orders.get(0), 400, 20)
    ));

    List<DispatchServiceSalaryForDay> driverSalaryForDays = new ArrayList<>(Arrays.asList(
            new DispatchServiceSalaryForDay( 200)
    ));

    public List<Driver> getDrivers(){
        return  drivers;
    }

    public List<TaxiOffice> getTaxiServices() {
        return taxiOffices;
    }

    public List<Modell> getModells(){
        return modells;
    }

    public List<Car> getCars(){
        return cars;
}

    public List<Operator> getOperators() {
        return operators;
    }
    public List<DriverTimeTable> getOperatorTimeTables(){
        return operatorTimeTables;
    }
    public List<DiscountCard> getDiscountCards() {
        return discountCards;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Order> getOrders(){
        return orders;
    }
    public List<Check> getChecks() {
        return checks;
    }
    public List<DispatchServiceSalaryForDay> getDriverSalaryForDays() {
        return driverSalaryForDays;
    }
}
