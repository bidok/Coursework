package com.example.demo.controller.ui;

import com.example.demo.service.car.impl.CarServiceImpl;
import com.example.demo.service.check.impls.CheckServiceImpl;
import com.example.demo.service.customer.impls.CustomerServiceImpl;
import com.example.demo.service.driver.impls.DriverServiceImpl;
import com.example.demo.service.driverSalaryForDay.impls.DriverSalaryForDayServiceImpl;
import com.example.demo.service.driverTimeTable.impl.DriverTimeTableServiceImpl;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;
import com.example.demo.service.taxiOfficeSalaryForInterval.impls.TaxiOfficeSalaryForIntervalServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * @author : Vasyl Bidiak
 * @created : 22.05.2021, суббота
 * @className : RequestController
 **/
@Controller
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {
	private final CheckServiceImpl checkService;
	private final CustomerServiceImpl customerService;
	private final CarServiceImpl carService;
	private final TaxiOfficeServiceImpl taxiOfficeService;
	private final DriverServiceImpl driverService;
	private final DriverSalaryForDayServiceImpl driverSalaryForDayService;
	private final DriverTimeTableServiceImpl driverTimeTableService;
	private final TaxiOfficeSalaryForIntervalServiceImpl taxiOfficeSalaryForIntervalService;

	@RequestMapping("/1/{date}")
	public String request1(@PathVariable String date, Model model) {
		model.addAttribute("orders", checkService.getOrdersByDateAndSortedForDistance(LocalDate.parse(date)));
		return "order/showAll";
	}
	@RequestMapping("/2")
	public String request2(Model model) {
		model.addAttribute("customer", customerService.getCustomersWhoCanceledOrder().stream()
		.filter(item -> !item.getName().equals("undefined")).collect(Collectors.toList()));
	return "customer/showAll";
	}
	@RequestMapping("/3")
	public String request3(Model model) {
		model.addAttribute("cars", carService.getAllAndOrderByModel());
		return "car/showAll";
	}
	@RequestMapping("/4/{taxiOfficeId}")
	public String request4(@PathVariable String taxiOfficeId, Model model) {
		model.addAttribute("taxiOffice", taxiOfficeService.getById(taxiOfficeId));
		model.addAttribute("statistic", taxiOfficeService.getStatisticSameTaxiOfficeByDriverMarks(taxiOfficeId));
		return "requests/showStatistic";
	}
	@RequestMapping("/5/{taxiOfficeId}/{mark}")
	public String request5(@PathVariable String taxiOfficeId, @PathVariable String mark, Model model) {
		model.addAttribute("driver", driverService.getDriversWithSomeTaxiOfficeAndMark(taxiOfficeId, Integer.parseInt(mark)));
		return "driver/showAll";
	}
	@RequestMapping("/6/{taxiOfficeId}")
	public String request6(@PathVariable String taxiOfficeId, Model model) {
		model.addAttribute("driver", driverService.getFirst10DriversWithBestMarkForSomeTaxiOffice(taxiOfficeId));
		return "driver/showAll";
	}
	@RequestMapping("/7/{taxiOfficeId}/{from}/{to}")
	public String request7(@PathVariable String taxiOfficeId,
			@PathVariable String from, @PathVariable String to, Model model) {
		model.addAttribute("driverSalaryForDay", driverSalaryForDayService.getSalaryForSomeIntervalAndSomeTaxiOffice(
				taxiOfficeId,
				LocalDate.parse(from),
				LocalDate.parse(to)));
	return "driverSalaryForDay/showAll";

	}
	@RequestMapping("/8/{from}/{to}")
	public String request8(@PathVariable String from, @PathVariable String to, Model model) {
		model.addAttribute("taxiOfficeSalaryForInterval", taxiOfficeSalaryForIntervalService
				.getSortedSalaryForSomeInterval(LocalDate.parse(from), LocalDate.parse(to)));
		return "requests/taxiOfficeSalaries";
	}
	@RequestMapping("/9/{date}")
	public String request9(@PathVariable String date, Model model) {
		model.addAttribute("driver", driverTimeTableService
				.getDriverWhoWorksAtSomeDate(LocalDate.parse(date)));
		return "driver/showAll";
	}
	@RequestMapping("/10")
	public String request10( Model model) {
		model.addAttribute("customer", customerService.get10CustomersWithBiggestDiscount());
		return "customer/showAll";
	}
	@RequestMapping("/11/{taxiOfficeId}/{carClass}")
	public String request11(@PathVariable String taxiOfficeId, @PathVariable String carClass, Model model) {
		model.addAttribute("cars", carService.getCarsFromSomeTaxiOfficeAndWithSomeCarClass(taxiOfficeId, carClass));
		return "car/showAll";
	}
	@RequestMapping("/12/{taxiOfficeId}")
	public String request12(@PathVariable String taxiOfficeId,Model model) {
		model.addAttribute("statistic", taxiOfficeService.getStatisticForSomeTaxiOfficeByOrders(taxiOfficeId));
		return "requests/showStatisticByOrderForTaxiOffice";
	}
	@RequestMapping("/13")
	public String request13(Model model) {
		model.addAttribute("statistic", taxiOfficeService.getStatistic());
		return "requests/comparisonTaxiOffice";
	}
	@RequestMapping("/14/{taxiOfficeId}")
	public String request14(@PathVariable String taxiOfficeId, Model model) {
		model.addAttribute("statistic", driverService.getDriverStatisticBySalaryAndOrderForSomeTaxiOffice(taxiOfficeId));
		return "requests/comparisonDriver";
	}
	@RequestMapping("/15")
	public String request15(Model model) {
		model.addAttribute("driver", driverService.get10DriverWithBiggestNumOfCompletedOrders());
		return "driver/showAll";
	}
}
