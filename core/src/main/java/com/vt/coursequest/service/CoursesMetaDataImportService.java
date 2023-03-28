package com.vt.coursequest.service;

import java.io.IOException;

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

}
