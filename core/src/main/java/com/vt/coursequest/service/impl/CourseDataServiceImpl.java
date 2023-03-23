package com.vt.coursequest.service.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.vt.coursequest.dao.ReviewRepository;
import com.vt.coursequest.entity.Review;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vt.coursequest.dao.CourseRepository;
import com.vt.coursequest.dao.DegreeRepository;
import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.Degree;
import com.vt.coursequest.service.CourseDataService;

@Service
public class CourseDataServiceImpl implements CourseDataService {

	@Resource
	private CourseRepository courseRepository;

	@Resource
	private DegreeRepository degreeRepository;

	@Resource
	private ReviewRepository reviewRepository;

	@Override
	public List<Course> findAll(Integer universityid) {
		return courseRepository.findByUniversityId(universityid);
	}

	@Override
	public Optional<Course> findOne(Integer universityId, Integer courseId) {
		return courseRepository.findByUniversityIdAndId(universityId, courseId);
	}

	@Override
	public List<Degree> getDegreeList(Integer universityid) {
		return degreeRepository.findAll();
	}

	@Override
	public List<Review> getReviewList(Integer universityId, Integer courseId, Integer pageNum, Integer pageSize, String orderBy) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return reviewRepository.findByCourseIdAndUniversityId(universityId, courseId, pageable);
	}

	@Override
	public List<Review> findAllReview(Integer universityId, Integer courseId) {
		return reviewRepository.findByCourseIdAndUniversityId(universityId, courseId);
	}

	@Override
	public List<Course> getCourseList(Integer universityId, Integer pageNum, Integer pageSize, String orderBy) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return courseRepository.findByUniversityId(universityId, pageable);
	}

	@Override
	public Review createReview(Review review) {
		return reviewRepository.save(review);
	}

}
