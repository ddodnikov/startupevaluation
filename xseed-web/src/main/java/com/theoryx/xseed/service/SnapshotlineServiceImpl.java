package com.theoryx.xseed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.AjaxQuestion;
import com.theoryx.xseed.dto.AnswerOptionDTO;
import com.theoryx.xseed.dto.SnapshotLineDTO;
import com.theoryx.xseed.model.AnswerOption;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.SnapshotLine;
import com.theoryx.xseed.repository.SnapshotlineRepository;

@Service
public class SnapshotlineServiceImpl implements SnapshotlineService {

	@Autowired
	private SnapshotlineRepository snapshotlineRepository;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerOptionService answerOptionService;

	@Override
	public SnapshotLineDTO convertSnapshotlineToSnapshotlineDTO(SnapshotLine snapshotLine) {
		if (snapshotLine != null) {
			SnapshotLineDTO snapshotlineDTO = new SnapshotLineDTO();
			snapshotlineDTO.setId(snapshotLine.getId());
			snapshotlineDTO.setTextResponse(snapshotLine.getTextResponse());
			snapshotlineDTO.setQuestion(questionService.convertQuestionToQuestionDto(snapshotLine.getQuestion()));
			snapshotlineDTO.setSelected_answer(
					answerOptionService.convertAnswerOptionToAnswerOptionDto(snapshotLine.getSelected_answer()));

			AnswerOption[] multipleSelectedAnswers = snapshotLine.getMultipleSelectedAnswers();
			AnswerOptionDTO[] multipleSelectedAnswersDTO;
			if (multipleSelectedAnswers == null || multipleSelectedAnswers.length == 0) {
				multipleSelectedAnswersDTO = null;
			} else {
				multipleSelectedAnswersDTO = new AnswerOptionDTO[snapshotLine.getMultipleSelectedAnswers().length];
				for (int i = 0; i < multipleSelectedAnswersDTO.length; i++) {
					multipleSelectedAnswersDTO[i] = answerOptionService
							.convertAnswerOptionToAnswerOptionDto(snapshotLine.getMultipleSelectedAnswers()[i]);
				}
			}
			snapshotlineDTO.setMultipleSelectedAnswers(multipleSelectedAnswersDTO);
			return snapshotlineDTO;
		} else {
			return null;
		}
	}

	@Override
	public List<SnapshotLine> findFilterSnapshotLines(Integer snapshotId) {
		return snapshotlineRepository.findFilterSnapshotLines(snapshotId);
	}

	@Override
	public SnapshotLine findByQuestionIdAndSnapshotId(Integer questionId, Integer snapshotId) {
		return snapshotlineRepository.findByQuestionIdAndSnapshotId(questionId, snapshotId);
	}

	@Override
	public SnapshotLine findByQuestionInList(List<SnapshotLine> filterSnapshotLines, Integer id) {
		if (filterSnapshotLines != null && id != null && !filterSnapshotLines.isEmpty()) {
			for (SnapshotLine snapshotLine : filterSnapshotLines) {
				if (snapshotLine.getQuestion().getId() == id) {
					return snapshotLine;
				}
			}
			return null;
		} else {
			return null;
		}
	}

	@Override
	public List<SnapshotLine> findAlgoSnapshotLinesBySnapshotId(Integer snapshotId) {
		return snapshotlineRepository.findAlgoSnapshotLinesBySnapshotId(snapshotId);
	}
	
	@Override
	public List<SnapshotLine> createFilterSnapshotlines(List<AjaxQuestion> questions, List<Question> filterQuestions) {
		List<SnapshotLine> snapshotLines = new ArrayList<SnapshotLine>();	
		for (int i = 0; i < filterQuestions.size(); i++) {
			SnapshotLine snapshotLine = new SnapshotLine();
			snapshotLine.setQuestion(filterQuestions.get(i));
			
			List<Integer> multipleSelectedAnswers = questions.get(i).getAnswerIds();
			List<AnswerOption> answerOptions = new ArrayList<AnswerOption>();	
			for (Integer id : multipleSelectedAnswers) {
				answerOptions.add(answerOptionService.findById(id));
			}	
			AnswerOption[] selectedAnswers = new AnswerOption[answerOptions.size()];
			answerOptions.toArray(selectedAnswers);
			if (answerOptions.isEmpty()) {
				snapshotLine.setMultipleSelectedAnswers(null);
			} else {
				snapshotLine.setMultipleSelectedAnswers(selectedAnswers);
				snapshotLines.add(snapshotLine);
			}
		}
		return snapshotLines;
	}

}
