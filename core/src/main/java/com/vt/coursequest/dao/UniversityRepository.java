package com.vt.coursequest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.coursequest.entity.University;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Integer>{

    @Query("SELECT u FROM University u")
    List<University> getAllUniversity();
}
