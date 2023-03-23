package com.vt.coursequest;

import com.vt.coursequest.service.CourseDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Console;

@SpringBootTest
class CourseQuestApplicationTests {

	@Autowired
	CourseDataService courseDataService;

	@Test
	void contextLoads() {
		//System.out.println(courseDataService.findAllReview(1, 2));
		System.out.println(courseDataService.getDegreeList(1).toString());
	}

}
