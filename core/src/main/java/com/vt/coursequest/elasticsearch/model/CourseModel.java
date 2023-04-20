package com.vt.coursequest.elasticsearch.model;

import java.util.Set;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName = "courses")
@Data
public class CourseModel {
    private Integer id;
    private String courseName;
    private String desc;
    private String courseNum;
    private Set<String> instructors;
    private Set<String> crns;
    // getters and setters
}