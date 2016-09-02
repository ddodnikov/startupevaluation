package com.theoryx.xseed.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.Snapshot;

@Repository
@Transactional
public interface SnapshotRepository extends PagingAndSortingRepository<Snapshot, Integer> {

	List<Snapshot> findByStartupIdOrderByDateDesc(Integer startupId);
	
	Snapshot findById(Integer id);
	
	@Query(value = "SELECT * FROM snapshots WHERE startup_id = :startupId ORDER BY date DESC LIMIT 1", nativeQuery = true)
	Snapshot getLatestSnapshotByStartupId(@Param("startupId") Integer startupId);
	
}
