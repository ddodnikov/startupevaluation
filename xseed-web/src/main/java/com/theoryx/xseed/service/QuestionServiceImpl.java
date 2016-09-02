package com.theoryx.xseed.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.QuestionDTO;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.QuestionAnswerType;
import com.theoryx.xseed.model.Snapshot;
import com.theoryx.xseed.model.SnapshotLine;
import com.theoryx.xseed.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerOptionService answerOptionService;
	@Autowired
	private SnapshotlineService snapshotlineService;

	@Override
	public QuestionDTO convertQuestionToQuestionDto(Question question) {
		QuestionDTO questionDto = null;
		if (question != null) {
			questionDto = new QuestionDTO();
			questionDto.setId(question.getId());
			questionDto.setText(question.getText());
			questionDto.setAnswers(answerOptionService.getAnswerOptionDtos(question));
			questionDto.setType(question.getCategory().getType().name());
			questionDto.setHasOther(question.isHasOther());
			questionDto.setAlgo(question.isAlgo());
		}
		return questionDto;
	}

	@Override
	public Question convertQuestionDTOToQuestion(QuestionDTO questiondto) {
		if (questiondto != null) {
			Integer id = questiondto.getId();
			if (id != null) {
				return questionRepository.findOne(id);
			} else {
				Question question = new Question();
				String text = questiondto.getText();
				question.setText(text);

				return question;
			}
		} else {
			return null;
		}
	}

	@Override
	public Question findbyId(Integer questionId) {
		return questionRepository.findOne(questionId);
	}

	@Override
	public List<Question> findByFilter(boolean filter) {
		return questionRepository.findByFilter(filter);
	}

	@Override
	public List<Question> findByAlgo(boolean algo) {
		return questionRepository.findByAlgo(algo);
	}

	@Override
	public List<Question> findByKpi(boolean kpi) {
		return questionRepository.findByKpi(kpi);
	}

	@Override
	public List<QuestionDTO> convert(List<Question> filterQuestions) {
		List<QuestionDTO> questions = new ArrayList<QuestionDTO>();
		for (Question question : filterQuestions) {
			questions.add(convertQuestionToQuestionDto(question));
		}
		return questions;
	}
	
	@Override
	public List<Question> findAll() {
		List<Question> questions = (List<Question>) questionRepository.findAll();
		Collections.sort(questions, new Comparator<Question>() {
			@Override
			public int compare(Question o1, Question o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return questions;
	}

	@Override
	public Question save(Question question) {
		return questionRepository.save(question);
	}
	
	@Override
	public List<Question> filterAlgoQuestions(List<Snapshot> filteredSnapshots, List<Question> algoQuestions){
		List<Question> questions = new ArrayList<Question>();
		QUESTION: for (int i = 0; i < algoQuestions.size()-1; i++) {
			Question question = algoQuestions.get(i);
			System.out.println((i+1) + ". " + question.getText());
			if(question.getCategory().getType().name().equals(QuestionAnswerType.YES_NO.name()) ||
					question.getCategory().getType().name().equals(QuestionAnswerType.SINGLE_CHOICE.name())){
				System.out.println("Question is single or yes/no");
				Integer questionId = question.getId();
				Snapshot snapshotOne = filteredSnapshots.get(0);
				SnapshotLine lineOne = snapshotlineService.findByQuestionInList(snapshotOne.getSnapshotlines(), questionId);
				Integer answerIdOne = lineOne.getSelected_answer().getId();
				System.out.println(snapshotOne.getId() + " : " + answerIdOne);
				for (int j = i+1; j < filteredSnapshots.size(); j++) {
					Snapshot snapshotTwo = filteredSnapshots.get(j);
					SnapshotLine lineTwo = snapshotlineService.findByQuestionInList(snapshotTwo.getSnapshotlines(), questionId);
					Integer answerIdTwo = lineTwo.getSelected_answer().getId();
					System.out.println(snapshotTwo.getId() + " : " + answerIdTwo);
					if(!answerIdOne.equals(answerIdTwo)){
						System.out.println("Not the same , next question.");
						questions.add(question);
						continue QUESTION; 
					}
				}
			}
		}
		return questions;
	}

}
