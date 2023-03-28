package com.vt.coursequest.service;

import java.util.List;

import com.vt.coursequest.entity.Department;
import com.vt.coursequest.entity.Level;

public interface MetaDataService {

	public List<Level> getLevelList(int universityId);
	
	public List<Department> getDepartmentList(int universityId);

}
