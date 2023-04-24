package com.vt.coursequest.elasticsearch.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vt.coursequest.elasticsearch.model.CourseModel;


public interface ICourseESRepository extends ElasticsearchRepository<CourseModel, Integer> {

//    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
//    Page<CourseModel> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);
}