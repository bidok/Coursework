package com.example.demo.business;

import com.example.demo.model.DriverTimeTable;
import com.example.demo.model.OperatorTimeTable;
import com.example.demo.repository.OperatorTimeTable.OperatorTimeTableRepository;
import com.example.demo.repository.driverTimeTable.DriverTimeTableRepository;
import com.example.demo.repository.timeTable.TimeTableArchiveRepository;
import com.example.demo.service.driverTimeTable.impl.DriverTimeTableServiceImpl;
import com.example.demo.service.operatorTimeTable.impl.OperatorTimeTableServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Vasyl Bidiak
 * @created : 26.05.2021, среда
 * @className : removeTimeTableRecords
 **/
@Component
@RequiredArgsConstructor
public class removeTimeTableRecords {
	private final DriverTimeTableServiceImpl driverTimeTableService;
	private final DriverTimeTableRepository driverTimeTableRepository;
	private final OperatorTimeTableServiceImpl operatorTimeTableService;
	private final OperatorTimeTableRepository operatorTimeTableRepository;
	private final TimeTableArchiveRepository timeTableArchiveRepository;

	@Scheduled(cron = "1 */30 * * * *")
	public void removeDriverTimeTable() {
		List<DriverTimeTable> driverTimeTableList = driverTimeTableService.getAll().stream()
				.filter(item -> LocalTime.now().isAfter(item.getEndWork()))
				.collect(Collectors.toList());
		driverTimeTableRepository.deleteAll(driverTimeTableList);
		timeTableArchiveRepository.saveAll(driverTimeTableList);
		driverTimeTableList.clear();
	}
	@Scheduled(cron = "1 */30 * * * *")
	public void removeOperatorTimeTable() {
		List<OperatorTimeTable> operatorTimeTableList = operatorTimeTableService.getAll().stream()
				.filter(item -> LocalTime.now().isAfter(item.getEndWork()))
				.collect(Collectors.toList());
		System.out.println(operatorTimeTableList);
		operatorTimeTableRepository.deleteAll(operatorTimeTableList);
		timeTableArchiveRepository.saveAll(operatorTimeTableList);
		System.out.println("deleted");
		operatorTimeTableList.clear();
	}
}
