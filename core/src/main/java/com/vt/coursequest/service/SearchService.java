package com.vt.coursequest.service;

import java.util.Set;

import com.vt.coursequest.elasticsearch.model.CourseInfo;
import com.vt.coursequest.entity.Course;

public interface SearchService {

	Set<Course> getSearchResults(CourseInfo courseInfo);

	void dataExport();

}
