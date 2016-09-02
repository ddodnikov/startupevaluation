package com.theoryx.xseed.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.GroupCalculation;

@Repository
@Transactional
public interface GroupCalculationRepository extends JpaRepository<GroupCalculation, Integer>{
	
	List<GroupCalculation> findByCalculationId(Integer calculationId);

}
