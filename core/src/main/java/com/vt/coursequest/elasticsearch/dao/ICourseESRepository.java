package com.vt.coursequest.elasticsearch.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vt.coursequest.elasticsearch.model.CourseModel;
import com.vt.coursequest.entity.Course;


public interface ICourseESRepository extends ElasticsearchRepository<CourseModel, Integer> {

   // Page<Course> findByAuthorsName(String name, Pageable pageable);

//    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
//    Page<Course> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);
}