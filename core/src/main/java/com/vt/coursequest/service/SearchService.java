package com.vt.coursequest.service;

import java.util.Set;

import com.vt.coursequest.elasticsearch.dto.SearchDTO;
import com.vt.coursequest.entity.Course;

public interface SearchService {

	Set<Course> getSearchResults(SearchDTO courseInfo);

	void dataExport();

}
