package com.vt.coursequest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.coursequest.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	Optional<Instructor> findByName(String name);
}
