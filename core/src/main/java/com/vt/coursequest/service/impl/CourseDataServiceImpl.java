package com.vt.coursequest.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vt.coursequest.dao.CourseRepository;
import com.vt.coursequest.dao.DegreeRepository;
import com.vt.coursequest.dao.InstructorRepository;
import com.vt.coursequest.dao.ReviewRepository;
import com.vt.coursequest.dao.UniversityRepository;
import com.vt.coursequest.dao.UserRepository;
import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.Degree;
import com.vt.coursequest.entity.Instructor;
import com.vt.coursequest.entity.Review;
import com.vt.coursequest.entity.University;
import com.vt.coursequest.entity.User;
import com.vt.coursequest.service.CourseDataService;

@Service
public class CourseDataServiceImpl implements CourseDataService {

	@Resource
	private CourseRepository courseRepository;

	@Resource
	private DegreeRepository degreeRepository;

	@Resource
	private ReviewRepository reviewRepository;

	@Resource
	private UserRepository userRepository;

	@Resource
	private InstructorRepository instructorRepository;

	@Resource
	private UniversityRepository universityRepository;

	@Override
	public List<Course> findAll(Integer universityid) {
		List<Course> list = courseRepository.findByUniversityId(universityid);
		for (Course curCourse : list) {
			curCourse.setRating(courseRepository.getAverageRatingForCourse(curCourse.getId()));
		}
		return courseRepository.findByUniversityId(universityid);
	}

	@Override
	public Optional<Course> findOne(Integer universityId, Integer courseId) {
		Optional<Course> course = courseRepository.findByUniversityIdAndId(universityId, courseId);
		if (course.isPresent()) {
			Course curCourse = course.get();
			curCourse.setRating(courseRepository.getAverageRatingForCourse(curCourse.getId()));
		}
		return course;
	}

	@Override
	public List<Degree> getDegreeList(Integer universityid) {
		return degreeRepository.findAll();
	}

	@Override
	public List<Review> getReviewList(Integer universityId, Integer courseId, Integer pageNum, Integer pageSize,
			String orderBy) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return reviewRepository.findByCourseIdAndUniversityId(courseId, universityId, pageable);
	}

	@Override
	public List<Review> findAllReview(Integer universityId, Integer courseId) {
		return reviewRepository.findByCourseIdAndUniversityId(courseId, universityId);
	}

	@Override
	public Optional<Review> findOneReview(Integer universityId, Integer courseId, Integer userId) {
		return reviewRepository.findByCourseIdAndUniversityIdAndUserId(courseId, universityId, userId);
	}

	@Override
	public List<Course> getCourseList(Integer universityId, Integer pageNum, Integer pageSize, String orderBy) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		List<Course> list = courseRepository.findByUniversityId(universityId, pageable);
		for (Course curCourse : list) {
			Double curVal = courseRepository.getAverageRatingForCourse(curCourse.getId());
			curCourse.setRating(curVal);
		}
		return list;
	}

	@Override
	public Review createReview(Review review) {
		if (null != review.getCourse() && null != review.getCourse().getId()) {
			Optional<Course> course = courseRepository.findById(review.getCourse().getId());
			if (course.isPresent()) {
				review.setCourse(course.get());
			}
		}

		if (null != review.getUser() && null != review.getUser().getId()) {
			Optional<User> user = userRepository.findById(review.getUser().getId());
			if (user.isPresent()) {
				review.setUser(user.get());
			}
		}

		if (null != review.getUniversity() && null != review.getUniversity().getId()) {
			Optional<University> university = universityRepository.findById(review.getUniversity().getId());
			if (university.isPresent()) {
				review.setUniversity(university.get());
			}
		}

		if (null != review.getInstructor() && null != review.getInstructor().getId()) {
			Optional<Instructor> instructor = instructorRepository.findById(review.getInstructor().getId());
			if (instructor.isPresent()) {
				review.setInstructor(instructor.get());
			}
		}

		review.setCreatedAt(new Date());
		return reviewRepository.save(review);
	}

	@Override
	public Review updateReview(Integer reviewId, Review review) throws Exception {
		return reviewRepository.findById(reviewId).map(newReview -> {
			newReview.setUser(review.getUser());
			// newReview.setUniversity(review.getUniversity());
			newReview.setAnonymous(review.getAnonymous());
			newReview.setContent(review.getContent());
			newReview.setDelivery(review.getDelivery());
			newReview.setWorkload(review.getWorkload());
			newReview.setInstructor(review.getInstructor());
			newReview.setRating(review.getRating());
			newReview.setCreatedAt(new Date());
			return reviewRepository.save(newReview);
		}).orElseThrow(() -> new Exception("Review not found with id " + reviewId));
	}

	@Override
	public void deleteReview(Integer reviewId) {
		reviewRepository.deleteById(reviewId);
	}

	@Override
	public List<Review> findUserReviews(Integer userId) {
		return reviewRepository.findByUserId(userId);
	}

}
