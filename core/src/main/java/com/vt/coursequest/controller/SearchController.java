package com.vt.coursequest.controller;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vt.coursequest.elasticsearch.dto.SearchDTO;
import com.vt.coursequest.entity.Course;
import com.vt.coursequest.service.SearchService;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SearchController {

	@Autowired
	private SearchService searchService;

	@ApiOperation("This service is used to search for courses")
	@PostMapping("/api/courses/search")
	public ResponseEntity<Set<Course>> searchCourses(@RequestBody SearchDTO courseInfo, @RequestParam Integer pageNum,
			@RequestParam Integer pageSize) {
		Set<Course> searchResponse = null;
		try {
			searchResponse = searchService.getSearchResults(courseInfo, pageNum, pageSize);
		} catch (ElasticsearchException | IOException e) {
			log.error("Some error occured",e);
			e.printStackTrace();
		}
		return new ResponseEntity<>(searchResponse, HttpStatus.OK);
	}
}
