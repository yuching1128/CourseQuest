package com.vt.coursequest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vt.coursequest.dao.DepartmentRepository;
import com.vt.coursequest.dao.LevelRepository;
import com.vt.coursequest.entity.Department;
import com.vt.coursequest.entity.Level;
import com.vt.coursequest.service.MetaDataService;

@Service
public class MetaDataServiceImpl implements MetaDataService {

	@Resource
	private LevelRepository levelRepository;
	
	@Resource
	private DepartmentRepository departmentRepository;
	
	@Override
	public List<Level> getLevelList(int universityId) {
		return levelRepository.findByUniversityId(universityId);
	}

	@Override
	public List<Department> getDepartmentList(int universityId) {
		return departmentRepository.findByUniversityId(universityId);
	}

}
