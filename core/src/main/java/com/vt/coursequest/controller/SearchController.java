package com.vt.coursequest.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vt.coursequest.elasticsearch.dto.CourseListDTO;
import com.vt.coursequest.elasticsearch.dto.SearchDTO;
import com.vt.coursequest.service.CourseDataService;
import com.vt.coursequest.service.SearchService;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SearchController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private CourseDataService courseDBService;

	@ApiOperation("This service is used to search for courses")
	@GetMapping("/api/university/courses/search")
	public ResponseEntity<CourseListDTO> searchCourses(@RequestParam Integer pageNum,
													   @RequestParam Integer pageSize,
													   @RequestParam(required = false) String universityId,
													   @RequestParam(required = false) String fullTextSearch,
													   @RequestParam(required = false) String dept,
													   @RequestParam(required = false) String level) {

		CourseListDTO searchResponse = null;
		try {
			SearchDTO courseInfo = new SearchDTO();
			courseInfo.setDept(dept);
			courseInfo.setFullTextSearch(fullTextSearch);
			courseInfo.setLevel(level);
			courseInfo.setUniversityId(universityId);
			searchResponse = searchService.getSearchResults(courseInfo, pageNum, pageSize);
			if (searchResponse.getCourses().isEmpty() && courseInfo.isEmpty()) {
				searchResponse = courseDBService.getCourseList(Integer.valueOf(courseInfo.getUniversityId()), pageNum,
						pageSize, null);
			}
		} catch (ElasticsearchException | IOException e) {
			log.error("Some error occured", e);
			e.printStackTrace();
		}
		return new ResponseEntity<>(searchResponse, HttpStatus.OK);
	}

}

