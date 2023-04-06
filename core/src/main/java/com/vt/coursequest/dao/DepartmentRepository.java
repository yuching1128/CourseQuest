package com.vt.coursequest.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.coursequest.entity.Department;

/**
 * @description: some desc
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	List<Department> findByUniversityId(int universityId);

	Optional<Department> findByNameAndUniversityId(String dept, int universityId);

}
