package com.vt.coursequest.service;

import java.io.IOException;
import java.util.List;

import com.vt.coursequest.importdata.bean.CourseMetaData;

/**
 * 
 * This service contains all the method used for importing the courses related
 * data and saving it in the database
 * 
 * @author Swati Lamba
 *
 */
public interface CoursesMetaDataImportService {

	/**
	 * 
	 * This method is used to fetch all the courses related data from an external
	 * source which could be used to show on the application
	 * 
	 * @param universityId : the university id for which this data will be collected
	 * @param isFullImport : true if full data needs to be refreshed, false if only
	 *                     the differential data should be merged in the existing
	 *                     data present in the database
	 * @throws IOException
	 */
	void importCourseMetaData(Integer universityId, boolean isFullImport) throws IOException;

	/**
	 * 
	 * This method is used to scrap course description for courses from the
	 * university's web site and save it in the DB
	 * 
	 * @param universityId
	 * @param isFullImport
	 * @throws IOException
	 */
	void scrapCourseDescriptionMetaData(Integer universityId, boolean isFullImport) throws IOException;

}
