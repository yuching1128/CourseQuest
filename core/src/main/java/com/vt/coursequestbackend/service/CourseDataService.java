package com.vt.coursequestbackend.service;

import java.util.List;

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
	 * This function is used to get exhaustive list of all the courses list specific to a university
	 * 
	 * @param universityid : the unique id of the university
	 * @return List<Course> for a particular university
	 */
	List<Course> getCourseList(String universityid);

	/**
	 * 
	 * This function is used to get a particular course details based of the courseId and the universityId
	 * 
	 * @param courseid: the unique id of a course
	 * @param universityId: the unique id of the university
	 * @return Course containing details of the course
	 */
	Course getCourseDetails(String courseid, String universityId);

	/**
	 * 
	 * This function is used to get all the degrees associated with a university
	 * 
	 * @param universityid: the unique id of the university
	 * @return List<Degree> for a particular university
	 */
	List<Degree> getDegreeList(String universityid);

}
