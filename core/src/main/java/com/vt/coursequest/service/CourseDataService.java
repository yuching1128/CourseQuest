package com.vt.coursequest.service;

import java.util.List;
import java.util.Optional;

import com.vt.coursequest.elasticsearch.dto.CourseListDTO;
import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.Degree;
import com.vt.coursequest.entity.Review;
import com.vt.coursequest.entity.User;

/**
 * 
 * This service contains all the courses related services.
 * 
 * @author Swati Lamba
 *
 */
public interface CourseDataService {

	/**
	 * This function is used to get exhaustive list of all the courses list specific
	 * to a university
	 * 
	 * @param universityid : the unique id of the university
	 * @return List<Course> for a particular university
	 */
	List<Course> findAll(Integer universityid);

	/**
	 * This function is used to get list of all the courses list specific on a
	 * specific page and size to a university eg. if page number is 3 and page size
	 * is 10 then the item from 31 to 40 will be returned.
	 * 
	 * @param universityid : the unique id of the university
	 * @param pageNum      : the pagenumber
	 * @param pageSize     :the pagesize
	 * @param orderBy      :the orderBy column name
	 * 
	 * @return List<Course> for a particular university
	 */
	CourseListDTO getCourseList(Integer universityid, Integer pageNum, Integer pageSize, String orderBy);

	/**
	 * 
	 * This function is used to get a particular course details based of the
	 * courseId and the universityId
	 * 
	 * @param courseId:     the unique id of a course
	 * @param universityId: the unique id of the university
	 * @return Course containing details of the course
	 */
	Optional<Course> findOne(Integer courseId, Integer universityId);

	/**
	 * 
	 * This function is used to get all the degrees associated with a university
	 * 
	 * @param universityId: the unique id of the university
	 * @return List<Degree> for a particular university
	 */
	List<Degree> getDegreeList(Integer universityId);


	/**
	 *
	 * This function is used to get all the reviews associated with a course in a specific university
	 *
	 * @param universityId: the unique id of the university
	 * @param courseId: the unique id of a course
	 * @param pageNum      : the pageNumber
	 * @param pageSize     :the pageSize
	 * @param orderBy      :the orderBy column name
	 * @return List<Review> for a particular course of university
	 */
	List<Review> getReviewList(Integer universityId, Integer courseId, Integer pageNum, Integer pageSize, String orderBy);


	/**
	 *
	 * This function is used to get all the reviews associated with a course in a specific university
	 *
	 * @param universityId: the unique id of the university
	 * @param courseId: the unique id of a course
	 * @return List<Review> for a particular course of university
	 */
	List<Review> findAllReview(Integer universityId, Integer courseId);

	Optional<Review> findOneReview(Integer universityId, Integer courseId, Integer userId);

	/**
	 *
	 * This function is used to create the review associated with a course in a specific university
	 *
	 * @param review: the review body
	 * @return Review: for a particular course of university
	 */
	Review createReview(Review review, User user);

	/**
	 *
	 * This function is used to update the review associated with a course in a specific university
	 *
	 * @param reviewId: the unique id of the review
	 * @param review: the review body
	 * @return Review: for a particular course of university
	 */
	Review updateReview(Integer reviewId, Review review) throws Exception;


	/**
	 *
	 * This function is used to delete the review associated with a course in a specific university
	 *
	 * @param reviewId: the unique id of the review
	 */
	void deleteReview(Integer reviewId);

	/**
	 * This function is used to get all reviews written by a user
	 * @param userId: the unique id of the user
	 * @return a list of reviews written by a user
	 */
	List<Review> findUserReviews(Integer userId);


	Optional<Course> addFollowCourse(User user, Integer courseId);

	Optional<Course> deleteFollowCourse(User user, Integer courseId);


}
