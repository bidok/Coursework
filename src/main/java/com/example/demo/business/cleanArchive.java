package com.example.demo.business;

import com.example.demo.repository.timeTable.TimeTableArchiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : Vasyl Bidiak
 * @created : 26.05.2021, среда
 * @className : cleanArchive
 **/
@Component
@RequiredArgsConstructor
public class cleanArchive {
	private final TimeTableArchiveRepository timeTableArchiveRepository;

	@Scheduled(cron = "1 1 1 1 */3 *")
	public void cleanArchive() {
		timeTableArchiveRepository.deleteAll();
	}
}
