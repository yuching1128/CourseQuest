package com.vt.coursequest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.coursequest.entity.University;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Integer>{

    @Query("SELECT u FROM University u")
    List<University> getAllUniversity();

    Optional<University> findByHd(String hd);
}
