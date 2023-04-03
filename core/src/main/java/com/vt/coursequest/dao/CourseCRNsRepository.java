package com.vt.coursequest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.coursequest.entity.CourseCRN;

public interface CourseCRNsRepository extends JpaRepository<CourseCRN, Integer> {
	Optional<CourseCRN> findByCrnNumber(String crnNumber);
}
