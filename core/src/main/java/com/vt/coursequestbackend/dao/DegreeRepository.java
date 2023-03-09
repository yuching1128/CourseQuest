package com.vt.coursequestbackend.dao;

import com.vt.coursequestbackend.entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: EugeneFeng
 * @date: 3/10/23 12:23 AM
 * @description: some desc
 */
public interface DegreeRepository extends JpaRepository<Degree, Integer> {

}
