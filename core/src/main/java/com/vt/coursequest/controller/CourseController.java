package com.vt.coursequest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.util.StringUtils;
import com.vt.coursequest.elasticsearch.dto.CourseListDTO;
import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.Degree;
import com.vt.coursequest.entity.Review;
import com.vt.coursequest.entity.User;
import com.vt.coursequest.interceptor.UserDetailsFromGoogle;
import com.vt.coursequest.service.CourseDataService;
import com.vt.coursequest.service.UserDataService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: EugeneFeng
 * @date: 3/3/23 5:02 PM
 * @description: some desc
 */

@RestController
@Slf4j
public class CourseController {

	@Autowired
	private CourseDataService cds;

	@Autowired
	private HttpSession session;

	@Autowired
	private UserDataService uds;

	@ApiOperation("This service is used to get the list of all the courses available in the university")
	@GetMapping("/api/university/{universityId}/courses")
	public ResponseEntity<CourseListDTO> getCourseList(@PathVariable String universityId,
			@RequestParam(required = false) String pageNum, @RequestParam(required = false) String pageSize) {
		List<Course> list;
		CourseListDTO resp = null;
		if (StringUtils.isNullOrEmpty(pageSize) && StringUtils.isNullOrEmpty(pageNum)) {
			list = cds.findAll(Integer.parseInt(universityId));

			resp = new CourseListDTO(list, list.size());
		} else {
			resp = cds.getCourseList(Integer.parseInt(universityId), Integer.parseInt(pageNum),
					Integer.parseInt(pageSize), "");
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@ApiOperation("This service is used to get a particular coursedetails")
	@GetMapping("/api/university/{universityId}/courses/{courseId}")
	public ResponseEntity<Optional<Course>> getCourseDetails(@PathVariable String courseId,
			@PathVariable String universityId) {

		return new ResponseEntity<>(cds.findOne(Integer.parseInt(universityId), Integer.parseInt(courseId)),
				HttpStatus.OK);
	}

	@ApiOperation("This service is used to get the list of all the degree types available in the university\n")
	@GetMapping("/api/university/{universityId}/degreeTypes")
	public ResponseEntity<List<Degree>> getDegreeList(@PathVariable String universityId) {
		List<Degree> list;
		list = cds.getDegreeList(Integer.parseInt(universityId));
		return list.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(list, HttpStatus.OK);
	}

	@ApiOperation("This service is used to get the list of reviews in a specific course")
	@GetMapping("/api/university/{universityId}/courses/{courseId}/review")
	public ResponseEntity<List<Review>> getReviewList(@PathVariable String universityId, @PathVariable String courseId,
			@RequestParam(required = false) String pageNum, @RequestParam(required = false) String pageSize) {
		List<Review> list = new ArrayList<>();
		if (StringUtils.isNullOrEmpty(pageSize) && StringUtils.isNullOrEmpty(pageNum)) {
			list = cds.findAllReview(Integer.parseInt(universityId), Integer.parseInt(courseId));
		} else {
			list = cds.getReviewList(Integer.parseInt(universityId), Integer.parseInt(courseId),
					Integer.parseInt(pageNum), Integer.parseInt(pageSize), "");
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// @ApiOperation("This service is used to get a specific review in a specific
	// course")
	// @GetMapping("/api/university/{universityId}/courses/{courseId}/review")
	// public ResponseEntity<Optional<Review>> findOneReview(@PathVariable String
	// universityId, @PathVariable String courseId,
	// @RequestBody String userId) {
	// Optional<Review> review = cds.findOneReview(Integer.parseInt(universityId),
	// Integer.parseInt(courseId), Integer.parseInt(userId));
	// return review.isEmpty()? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new
	// ResponseEntity<>(review, HttpStatus.OK);
	// }


	@ApiOperation("This service is used to follow a course and add it in interested course")
	@PostMapping("/api/university/{universityId}/courses/{courseId}/follow")
	public ResponseEntity<Optional<Course>> followCourse(@PathVariable Integer courseId) {
		UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
		User user = uds.findOrCreateUser(userDetails);
		Optional<Course> curCourse = cds.addFollowCourse(user, courseId);
		return new ResponseEntity<>(curCourse, HttpStatus.OK);
	}

	@ApiOperation("This service is used to unfollow a course and delete it from interested course")
	@DeleteMapping("/api/university/{universityId}/courses/{courseId}/unfollow")
	public ResponseEntity<?> unfollowCourse(@PathVariable Integer courseId) {
		UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
		User user = uds.findOrCreateUser(userDetails);
		Optional<Course> curCourse = cds.deleteFollowCourse(user, courseId);
		return new ResponseEntity<>(curCourse, HttpStatus.OK);
	}

	@ApiOperation("This service is used to create a review for a specific course")
	@PostMapping("/api/university/{universityId}/courses/{courseId}/review")
	public ResponseEntity<Review> addReview(@RequestBody Review review) {
		Review reviewResponse = new Review();
		try {
			UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
			User user = uds.findOrCreateUser(userDetails);
			reviewResponse = cds.createReview(review, user);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<>(reviewResponse, HttpStatus.OK);
	}

	@ApiOperation("This service is used to update a review for a specific course")
	@PutMapping("/api/university/{universityId}/courses/{courseId}/review/{reviewId}")
	public ResponseEntity<Review> updateReview(@PathVariable Integer reviewId, @RequestBody Review review) {
		Review reviewResponse = new Review();
		try {
			UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
			User user = uds.findOrCreateUser(userDetails);
			review.setUser(user);
			reviewResponse = cds.updateReview(reviewId, review);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<>(reviewResponse, HttpStatus.OK);
	}

	@ApiOperation("This service is used to delete a review for a specific course")
	@DeleteMapping("/api/university/{universityId}/courses/{courseId}/review/{reviewId}")
	public void deleteReview(@PathVariable Integer reviewId) {
		cds.deleteReview(reviewId);
	}

	@ApiOperation("This service is used to get all reviews written by a user")
	@GetMapping("/api/user/reviews")
	public ResponseEntity<List<Review>> findUserReviews() {
		UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
		User user = uds.findOrCreateUser(userDetails);
		return new ResponseEntity<>(cds.findUserReviews(user.getId()), HttpStatus.OK);
	}

}
