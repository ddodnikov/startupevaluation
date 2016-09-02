package com.theoryx.xseed.service;

import java.util.List;

import com.theoryx.xseed.dto.SnapshotDTO;
import com.theoryx.xseed.dto.StartupDTO;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.Snapshot;
import com.theoryx.xseed.model.SnapshotLine;
import com.theoryx.xseed.model.Startup;
import com.theoryx.xseed.model.Survey;
import com.theoryx.xseed.model.User;

public interface SnapshotService {

	/**
	 * Finds all snapshots by startup
	 * 
	 * @param startup
	 * @return List<SnapshotDTO>
	 */
	List<SnapshotDTO> findAllSnapshotsByStartup(StartupDTO startup);

	/**
	 * Returns a SnapshotDTO found by id and Startup
	 * 
	 * @param id
	 * @param startup
	 * @return SnapshotDTO
	 */
	SnapshotDTO getByIdAndStartup(String id, StartupDTO startup);

	/**
	 * Converts Snapshot model to SnapshotDTO
	 * 
	 * @param snapshot
	 * @return SnapshotDTO
	 */
	SnapshotDTO convertSnapshotTOSnapshotDTO(Snapshot snapshot);

	/**
	 * Converts Snapshot to SnapshotDTO that has only necessary attributes
	 * 
	 * @param snashot
	 * @return SnapshotDTO
	 */
	SnapshotDTO convertSnapshotToLightSnapshotDTO(Snapshot snapshot);

	/**
	 * Saves a snapshot
	 * 
	 * @param snapshot
	 * @return Snapshot model with generated id
	 */
	Snapshot saveSnapshot(Snapshot snapshot);

	/**
	 * Finds the latest snapshots for each startup
	 *
	 * @return List<Snapshot>
	 */
	List<Snapshot> getAllLatestSnapshots();

	/**
	 * Finds the latest snapshot for a startup
	 * 
	 * @param startup
	 * @return Snapshot
	 */
	Snapshot getLatestSnapshot(Startup startup);

	/**
	 * This method groups the filtered snapshots if all the algo questions have the same answers
	 * 
	 * @param filteredSnapshots
	 * @return List<List<Snapshot>>
	 * */
	List<List<Snapshot>> groupSnapshots(List<Snapshot> filteredSnapshots);

	/**
	 * This method returns only those snapshots that pass the filter
	 * 
	 * @param filterQuestions
	 * @param snapshotLines
	 * @return List<Snapshot>
	 * */
	List<Snapshot> filterSnapshots(List<Question> filterQuestions, List<SnapshotLine> snapshotLines);

	/**
	 * Finds all snapshots for a startup by its id
	 * 
	 * @param startupId
	 * @return List<Snapshot>
	 */
	List<Snapshot> findAllSnapshotsByStartupId(Integer startupId);

	Snapshot save(String name, Survey survey, User user, Startup startup, List<SnapshotLine> lines);

}
