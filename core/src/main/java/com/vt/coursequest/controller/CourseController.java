package com.vt.coursequest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vt.coursequest.dao.ReviewRepository;
import com.vt.coursequest.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mysql.cj.util.StringUtils;
import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.Degree;
import com.vt.coursequest.service.CourseDataService;

import io.swagger.annotations.ApiOperation;

/**
 * @author: EugeneFeng
 * @date: 3/3/23 5:02 PM
 * @description: some desc
 */

@RestController
public class CourseController {

	@Autowired
	private CourseDataService cds;

	@ApiOperation("This service is used to get the list of all the courses available in the university")
	@GetMapping("/api/university/{universityId}/courses")
	public ResponseEntity<List<Course>> getCourseList(@PathVariable String universityId, @RequestParam(required = false) String pageNum,
													 @RequestParam(required = false) String pageSize) {
		List<Course> list;
		if (StringUtils.isNullOrEmpty(pageSize) && StringUtils.isNullOrEmpty(pageNum)) {
			list = cds.findAll(Integer.parseInt(universityId));
		} else {
			list = cds.getCourseList(Integer.parseInt(universityId), Integer.parseInt(pageNum),
					Integer.parseInt(pageSize), "");
		}
		return list.isEmpty()? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(list, HttpStatus.OK);
	}

	@ApiOperation("This service is used to get a particular coursedetails")
	@GetMapping("/api/university/{universityId}/courses/{courseId}")
	public ResponseEntity<Optional<Course>> getCourseDetails(@PathVariable String courseId, @PathVariable String universityId) {

		return new ResponseEntity<>(cds.findOne(Integer.parseInt(universityId), Integer.parseInt(courseId)), HttpStatus.OK);
	}

	@ApiOperation("This service is used to get the list of all the degree types available in the university\n")
	@GetMapping("/api/university/{universityId}/degreeTypes")
	public ResponseEntity<List<Degree>> getDegreeList(@PathVariable String universityId) {
		List<Degree> list;
		list = cds.getDegreeList(universityId);
		return list.isEmpty()? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(list, HttpStatus.OK);
	}

	@ApiOperation("This service is used to get the list of reviews in a specific course")
	@GetMapping("/api/university/{universityId}/courses/{courseId}/review")
	public ResponseEntity<List<Review>> getReviewList(@PathVariable String universityId, @PathVariable String courseId, @RequestParam(required = false) String pageNum,
									  @RequestParam(required = false) String pageSize) {
		List<Review> list = new ArrayList<>();
		if (StringUtils.isNullOrEmpty(pageSize) && StringUtils.isNullOrEmpty(pageNum)) {
			list = cds.findAllReview(Integer.parseInt(universityId), Integer.parseInt(courseId));
		} else {
			list = cds.getReviewList(Integer.parseInt(universityId), Integer.parseInt(courseId), Integer.parseInt(pageNum),
					Integer.parseInt(pageSize), "");
		}
		return list.isEmpty()? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/api/university/{universityId}/courses/{courseId}/review")
	public Review addReview(@RequestBody Review review) {
		return cds.createReview(review);
	}




}
