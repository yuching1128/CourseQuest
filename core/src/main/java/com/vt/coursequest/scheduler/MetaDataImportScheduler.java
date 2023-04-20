package com.vt.coursequest.scheduler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vt.coursequest.service.CoursesMetaDataImportService;

@Service
public class MetaDataImportScheduler {

	@Autowired
	private CoursesMetaDataImportService importService;

	@Scheduled(cron = "${application.jobs.cronSchedule}")
	public void cronJobSch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println("Java cron job expression:: " + strDate);

		try {
			importService.importCourseMetaData(1, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
