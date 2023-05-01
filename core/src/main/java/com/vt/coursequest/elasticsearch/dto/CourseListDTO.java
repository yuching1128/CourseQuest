package com.vt.coursequest.elasticsearch.dto;

import java.util.ArrayList;
import java.util.List;

import com.vt.coursequest.entity.Course;

import lombok.Data;

@Data
public class CourseListDTO {
	private List<Course> courses = new ArrayList<>();
	private Integer totalCourses;

	public CourseListDTO(List<Course> courses, Integer totalCourses) {
		this.courses = courses;
		this.totalCourses = totalCourses;
	}

}
