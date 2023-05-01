package com.vt.coursequest.service;

import java.io.IOException;

import com.vt.coursequest.elasticsearch.dto.CourseListDTO;
import com.vt.coursequest.elasticsearch.dto.SearchDTO;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;

public interface SearchService {

	CourseListDTO getSearchResults(SearchDTO courseInfo, Integer pageNum, Integer pageSize) throws ElasticsearchException, IOException;

}
