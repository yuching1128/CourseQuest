package com.vt.coursequestbackend.service;

import java.util.List;
import java.util.Optional;

import com.vt.coursequestbackend.entity.Course;
import com.vt.coursequestbackend.entity.Degree;

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
	List<Course> getCourseList(Integer universityid, Integer pageNum, Integer pageSize, String orderBy);

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
	 * @param universityid: the unique id of the university
	 * @return List<Degree> for a particular university
	 */
	List<Degree> getDegreeList(String universityid);

}
