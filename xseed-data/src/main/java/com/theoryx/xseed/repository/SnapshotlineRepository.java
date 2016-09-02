package com.theoryx.xseed.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.SnapshotLine;

@Repository
@Transactional
public interface SnapshotlineRepository extends PagingAndSortingRepository<SnapshotLine, Integer>{
	
	SnapshotLine findByQuestionIdAndSnapshotId(Integer questionId, Integer snapshotId);
	
	@Query(value = "SELECT * FROM snapshotlines WHERE snapshot_id = :snapshotId AND question_id IN (SELECT id FROM questions WHERE filter = 'TRUE')", nativeQuery = true)
	List<SnapshotLine> findFilterSnapshotLines(@Param("snapshotId") Integer snapshotId);
	
	@Query(value = "SELECT * FROM snapshotlines WHERE snapshot_id = :snapshotId AND question_id IN (SELECT id FROM questions WHERE algo = 'TRUE')", nativeQuery = true)
	List<SnapshotLine> findAlgoSnapshotLinesBySnapshotId(@Param("snapshotId") Integer snapshotId);

	@Query(value = "SELECT * FROM snapshotlines WHERE snapshot_id = :snapshotId AND question_id = :questionId LIMIT 1", nativeQuery = true)
	SnapshotLine findBySnapshotIdAndQuestionId(Integer snapshotId, Integer questionId);

}
