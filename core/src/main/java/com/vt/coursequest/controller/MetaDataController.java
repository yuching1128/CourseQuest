package com.vt.coursequest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vt.coursequest.entity.Department;
import com.vt.coursequest.entity.Level;
import com.vt.coursequest.service.MetaDataService;

import io.swagger.annotations.ApiOperation;

@RestController
public class MetaDataController {

	@Autowired
	private MetaDataService mds;

	@ApiOperation("This service is used to get the list of all the departments available in the university")
	@GetMapping("/api/university/{universityId}/departments")
	public ResponseEntity<List<Department>> getDepartmentList(@PathVariable String universityId,
			@RequestParam(required = false) String pageNum, @RequestParam(required = false) String pageSize) {
		List<Department> list = mds.getDepartmentList(Integer.parseInt(universityId));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiOperation("This service is used to get the list of all the levels available in the university")
	@GetMapping("/api/university/{universityId}/levels")
	public ResponseEntity<List<Level>> getLevelList(@PathVariable String universityId,
			@RequestParam(required = false) String pageNum, @RequestParam(required = false) String pageSize) {
		List<Level> list = mds.getLevelList(Integer.parseInt(universityId));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
