package com.theoryx.xseed.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.KPIAdjustment;

@Repository
@Transactional
public interface KPIAdjustmentRepository extends JpaRepository<KPIAdjustment, Integer>{
	
	KPIAdjustment findByAnswerIdAndQuestionId(Integer answerId, Integer questionId);

	@Query(value = "SELECT SUM(value) FROM kpi_adjustments WHERE question_id = :kpiQuestionId", nativeQuery = true)
	Integer getSumOfValueByQuestionId(@Param("kpiQuestionId") Integer kpiQuestionId);

}
