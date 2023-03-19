package com.vt.coursequestbackend.service.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vt.coursequestbackend.dao.CourseRepository;
import com.vt.coursequestbackend.dao.DegreeRepository;
import com.vt.coursequestbackend.entity.Course;
import com.vt.coursequestbackend.entity.Degree;
import com.vt.coursequestbackend.service.CourseDataService;

@Service
public class CourseDataServiceImpl implements CourseDataService {

	@Resource
	private CourseRepository courseRepository;

	@Resource
	private DegreeRepository degreeRepository;

	@Override
	public List<Course> findAll(Integer universityid) {
		return courseRepository.findByUniversityId(universityid);
	}

	@Override
	public Optional<Course> findOne(Integer courseid, Integer universityId) {
		return courseRepository.findByUniversityIdAndId(universityId, courseid);
	}

	@Override
	public List<Degree> getDegreeList(String universityid) {
		return degreeRepository.findAll();
	}

	@Override
	public List<Course> getCourseList(Integer universityId, Integer pageNum, Integer pageSize, String orderBy) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return courseRepository.findByUniversityId(universityId, pageable);
	}

}
