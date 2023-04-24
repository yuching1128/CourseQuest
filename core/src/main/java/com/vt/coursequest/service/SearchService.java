package com.vt.coursequest.service;

import java.io.IOException;
import java.util.Set;

import com.vt.coursequest.elasticsearch.dto.SearchDTO;
import com.vt.coursequest.entity.Course;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;

public interface SearchService {

	Set<Course> getSearchResults(SearchDTO courseInfo, Integer pageNum, Integer pageSize) throws ElasticsearchException, IOException;

}
