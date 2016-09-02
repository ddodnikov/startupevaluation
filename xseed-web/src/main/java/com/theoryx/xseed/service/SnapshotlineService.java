package com.theoryx.xseed.service;

import java.util.List;

import com.theoryx.xseed.dto.AjaxQuestion;
import com.theoryx.xseed.dto.SnapshotLineDTO;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.SnapshotLine;

public interface SnapshotlineService {

	/**
	 * Converts SnapshotLine model to SnapshotlineDTO
	 * 
	 * @param snapshotLine
	 * @return SnapshotLineDTO
	 */
	SnapshotLineDTO convertSnapshotlineToSnapshotlineDTO(SnapshotLine snapshotLine);

	/**
	 * Finds filter snapshotLines by snapshot id
	 * 
	 * @param snapshotId
	 * @return List<SnapshotLine>
	 */
	List<SnapshotLine> findFilterSnapshotLines(Integer snapshotId);

	/**
	 * Finds a snasphotline by question id and snapshot id
	 * 
	 * @param questionId
	 * @param snapshotId
	 * @return SnapshotLine model
	 */
	SnapshotLine findByQuestionIdAndSnapshotId(Integer questionId, Integer snapshotId);

	/**
	 * Finds a SnapshotLine model in list of SnapshotLine models by question id
	 * 
	 * @param filterSnapshotLines
	 * @param id
	 * @return SnapshotLine
	 */
	SnapshotLine findByQuestionInList(List<SnapshotLine> filterSnapshotLines, Integer id);

	/**
	 * Finds algo snapshot lines by snapshot id
	 * 
	 * @param snapshotId
	 * @return List<SnapshotLine>
	 */
	List<SnapshotLine> findAlgoSnapshotLinesBySnapshotId(Integer snapshotId);

	/**
	 * Creates calculation filter snapshot lines
	 * 
	 * @param questions
	 *            List<AjaxQuestion>
	 * @param filterQuesrions
	 *            List<Question>
	 * @return List<SnapshotLine>
	 */
	List<SnapshotLine> createFilterSnapshotlines(List<AjaxQuestion> questions, List<Question> filterQuestions);
}
