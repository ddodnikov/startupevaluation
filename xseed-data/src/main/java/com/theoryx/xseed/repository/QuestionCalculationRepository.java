package com.theoryx.xseed.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.QuestionCalculation;

@Repository
@Transactional
public interface QuestionCalculationRepository extends JpaRepository<QuestionCalculation, Integer>{
	
	List<QuestionCalculation> findByCalculationId(Integer calculationId);

}
