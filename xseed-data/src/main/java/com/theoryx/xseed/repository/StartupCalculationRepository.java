package com.theoryx.xseed.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.StartupCalculation;

@Repository
@Transactional
public interface StartupCalculationRepository extends JpaRepository<StartupCalculation, Integer>{
	
	StartupCalculation findBySnapshotIdAndCalculationId(Integer snapshotId, Integer calculationId);
	
}
