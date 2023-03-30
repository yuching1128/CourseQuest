package com.vt.coursequest.service;

import java.util.List;

import com.vt.coursequest.entity.Department;
import com.vt.coursequest.entity.Level;

public interface MetaDataService {

	/**
	 * 
	 * This method is used to get the levels provided by a university
	 * 
	 * @param universityId
	 * @return
	 */
	public List<Level> getLevelList(int universityId);
	
	
	/**
	 * 
	 * This method is used to get all the departments of a university
	 * 
	 * @param universityId
	 * @return
	 */
	public List<Department> getDepartmentList(int universityId);

}
