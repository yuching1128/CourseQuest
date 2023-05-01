package com.vt.coursequest.service;

import com.vt.coursequest.entity.Course;

import java.util.List;
import java.util.Set;

public interface OpenAIService {
    List<Course> requestRecommendation(String concentration, Set<Course> courses) throws Exception;
}
