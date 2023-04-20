package com.vt.coursequest.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vt.coursequest.elasticsearch.model.CourseInfo;
import com.vt.coursequest.entity.Course;
import com.vt.coursequest.service.SearchService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SearchController {

	@Autowired
	private SearchService searchService;

	@ApiOperation("This service is used to search for  course")
	@PostMapping("/api/university/{universityId}/search")
	public ResponseEntity<Set<Course>> searchCourses(@RequestBody CourseInfo courseInfo) {
		Set<Course> searchResponse = searchService.getSearchResults(courseInfo);
		return new ResponseEntity<>(searchResponse, HttpStatus.OK);
	}
}
