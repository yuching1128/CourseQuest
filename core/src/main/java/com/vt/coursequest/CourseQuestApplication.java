package com.vt.coursequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableElasticsearchRepositories("com.vt.coursequest.elasticsearch")
@EnableScheduling
public class  CourseQuestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseQuestApplication.class, args);
	}

}
