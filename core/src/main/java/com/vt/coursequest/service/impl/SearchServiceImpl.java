package com.vt.coursequest.service.impl;

import com.vt.coursequest.dao.DepartmentRepository;
import com.vt.coursequest.dao.UniversityRepository;
import com.vt.coursequest.dao.UserRepository;
import com.vt.coursequest.elasticsearch.dto.SearchDTO;
import com.vt.coursequest.entity.*;

import com.vt.coursequest.interceptor.UserDetailsFromGoogle;
import com.vt.coursequest.service.SearchService;
import com.vt.coursequest.service.UserDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author: EugeneFeng
 * @date: 3/30/23 11:19 PM
 * @description: some desc
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UniversityRepository universityRepository;

    @Resource
    private DepartmentRepository departmentRepository;

	@Override
	public Set<Course> getSearchResults(SearchDTO courseInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dataExport() {
		// TODO Auto-generated method stub
		
	}


}
