package com.vt.coursequest.dao;

import com.vt.coursequest.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: EugeneFeng
 * @date: 4/3/23 11:57 PM
 * @description: some desc
 */
public interface MajorRepository extends JpaRepository<Major, Integer> {
}
