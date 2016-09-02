package com.theoryx.xseed.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.Calculation;

@Repository
@Transactional
public interface CalculationRepository extends JpaRepository<Calculation, Integer>{

	@Query(value = "SELECT * FROM calculations AS c JOIN startup_calculations AS sc ON sc.calculation_id=c.id"
			+ " WHERE sc.startup_id = :startupId ORDER BY c.date DESC LIMIT 1", nativeQuery = true)
	Calculation findLatestCalculation(@Param("startupId") Integer startupId);
}
