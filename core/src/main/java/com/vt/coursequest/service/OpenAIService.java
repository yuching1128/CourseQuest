package com.vt.coursequest.service;

import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.User;

import java.util.List;
import java.util.Set;

public interface OpenAIService {
    List<String> requestRecommendation(String concentration, Set<Course> courses) throws Exception;
}
