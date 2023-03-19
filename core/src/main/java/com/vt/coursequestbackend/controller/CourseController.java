package com.vt.coursequestbackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.util.StringUtils;
import com.vt.coursequestbackend.entity.Course;
import com.vt.coursequestbackend.entity.Degree;
import com.vt.coursequestbackend.service.CourseDataService;

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
	@GetMapping("/api/university/{universityid}/courses")
	public List<Course> getCourseList(@PathVariable String universityid, @RequestParam String pageNum,
			@RequestParam String pageSize) {
		List<Course> list = new ArrayList<>();
		if (StringUtils.isNullOrEmpty(pageSize) && StringUtils.isNullOrEmpty(pageNum)) {
			list = cds.findAll(Integer.parseInt(universityid));
		} else {
			list = cds.getCourseList(Integer.parseInt(universityid), Integer.parseInt(pageNum),
					Integer.parseInt(pageSize), "");
		}
		return list;
	}

	@ApiOperation("This service is used to get a particular coursedetails")
	@GetMapping("/api/university/{universityid}/courses/{courseid}")
	public Optional<Course> getCourseDetails(@PathVariable String courseid, @PathVariable String universityid) {
		return cds.findOne(Integer.parseInt(universityid), Integer.parseInt(courseid));
	}

	@ApiOperation("This service is used to get the list of all the degree types available in the university\n")
	@GetMapping("/api /university/{universityid}/degreetypes")
	public List<Degree> getDegreeList(@PathVariable String universityId) {
		return cds.getDegreeList(universityId);
	}

}
