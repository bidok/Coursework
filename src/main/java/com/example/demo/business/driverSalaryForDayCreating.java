package com.example.demo.business;

import com.example.demo.model.DispatchServiceSalaryForDay;
import com.example.demo.model.DriverSalaryForDay;
import com.example.demo.model.TaxiOfficeSalaryForDay;
import com.example.demo.repository.dispatchServiceSalaryForDay.DispatchServiceSalaryForDayRepository;
import com.example.demo.repository.driverSalaryForDay.DriverSalaryForDayRepository;
import com.example.demo.repository.taxiOfficeSalaryForDay.TaxiOfficeSalaryForDayRepository;
import com.example.demo.service.dispatchServiceSalaryForDay.impls.DispatchServiceSalaryForDayServiceImpl;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverSalaryForDay.impls.DriverSalaryForDayServiceImpl;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForDay.impls.TaxiOfficeSalaryForDayServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Vasyl Bidiak
 * @created : 25.05.2021, вторник
 * @className : driverSalaryForDayCreating
 **/
@Component
@RequiredArgsConstructor
public class driverSalaryForDayCreating {
	private final DriverSalaryForDayRepository driverSalaryForDayRepository;
	private final DriverSalaryForDayServiceImpl driverSalaryForDayService;
	private final DriverServiceImpl driverService;
	private final TaxiOfficeSalaryForDayRepository taxiOfficeSalaryForDayRepository;
	private final TaxiOfficeSalaryForDayServiceImpl taxiOfficeSalaryForDayService;
	private final TaxiOfficeServiceImpl taxiOfficeService;
	private final DispatchServiceSalaryForDayRepository dispatchServiceSalaryForDayRepository;
	private final DispatchServiceSalaryForDayServiceImpl dispatchServiceSalaryForDayService;


	@Scheduled(cron = "*/59 0-3 0 * * *")
	public void createDriverSalary() {
		if(driverSalaryForDayService.getAll().stream().anyMatch(item -> item.getCreateTime().equals(LocalDate.now()))){
			return;
		}
		List<DriverSalaryForDay> driverSalaryForDays = new ArrayList<>();
		driverService.getAll().forEach(item ->
				driverSalaryForDays.add(new DriverSalaryForDay(item, 0)));
		driverSalaryForDayRepository.saveAll(driverSalaryForDays);
		driverSalaryForDays.clear();
	}
	@Scheduled(cron = "*/59 0-3 0 * * *")
	public void createTaxiOfficeSalary() {
		if(taxiOfficeSalaryForDayService.getAll().stream().anyMatch(item -> item.getCreateTime().equals(LocalDate.now()))){
			return;
		}
		List<TaxiOfficeSalaryForDay> taxiOfficeSalaryForDays = new ArrayList<>();
		taxiOfficeService.getAll().forEach(item ->
				taxiOfficeSalaryForDays.add(new TaxiOfficeSalaryForDay(item, 0)));
		taxiOfficeSalaryForDayRepository.saveAll(taxiOfficeSalaryForDays);
		taxiOfficeSalaryForDays.clear();
	}
	@Scheduled(cron = "*/59 0-3 0 * * *")
	public void createDispatchServiceSalary() {
		if((dispatchServiceSalaryForDayService.getAll().stream().anyMatch(item -> item.getCreateTime().equals(LocalDate.now())))){
			return;
		}
		dispatchServiceSalaryForDayRepository.save(new DispatchServiceSalaryForDay(0));
	}

}
