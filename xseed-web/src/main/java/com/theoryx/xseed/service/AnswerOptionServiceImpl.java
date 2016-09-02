package com.theoryx.xseed.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.AnswerOptionDTO;
import com.theoryx.xseed.model.AnswerOption;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.repository.AnswerOptionRepository;

@Service
public class AnswerOptionServiceImpl implements AnswerOptionService {

	@Autowired
	private AnswerOptionRepository answerOptionRepository;

	@Override
	public List<AnswerOption> getAnswerOptions(Question question) {
		if (question != null) {
			Integer groupId = question.getAnswergroup().getId();
			return answerOptionRepository.findByGroupId(groupId);
		} else {
			return null;
		}
	}

	@Override
	public List<AnswerOptionDTO> getAnswerOptionDtos(Question question) {
		if (question != null) {
			List<AnswerOption> answerOptions = getAnswerOptions(question);
			List<AnswerOptionDTO> answerOptionDtos = new ArrayList<AnswerOptionDTO>();
			for (AnswerOption answerOption : answerOptions) {
				answerOptionDtos.add(convertAnswerOptionToAnswerOptionDto(answerOption));
			}
			return answerOptionDtos;
		} else {
			return null;
		}
	}

	@Override
	public AnswerOptionDTO convertAnswerOptionToAnswerOptionDto(AnswerOption answerOption) {
		AnswerOptionDTO answerOptionDto = null;
		if (answerOption != null) {
			answerOptionDto = new AnswerOptionDTO();
			answerOptionDto.setId(answerOption.getId());
			answerOptionDto.setIdentifier(answerOption.getIdentifier());
			answerOptionDto.setText(answerOption.getText());
		}
		return answerOptionDto;
	}

	@Override
	public AnswerOption findByText(String upperCase) {
		return answerOptionRepository.findByText(upperCase);
	}

	@Override
	public AnswerOption findById(Integer object) {
		return answerOptionRepository.findOne(object);
	}

	@Override
	public Set<Integer> getAnswerOptionOtherId() {
		Set<Integer> answerOptionsId = new HashSet<Integer>();
		for(AnswerOption ao:answerOptionRepository.findByTextLike("Other")){
			answerOptionsId.add(ao.getId());
		}
		return answerOptionsId;
	}

	@Override
	public List<AnswerOptionDTO> convert(List<AnswerOption> answerOptions) {
		if (answerOptions != null) {
			List<AnswerOptionDTO> answerOptionDtos = new ArrayList<AnswerOptionDTO>();
			for (AnswerOption answerOption : answerOptions) {
				answerOptionDtos.add(convertAnswerOptionToAnswerOptionDto(answerOption));
			}
			return answerOptionDtos;
		} else {
			return null;
		}
	}
	
	@Override
	public boolean isOtherChecked(String id) {
		Set<Integer> ids = getAnswerOptionOtherId();
		if (ids.contains(Integer.valueOf(id))) {
			return true;
		} else {
			return false;
		}
	}

}
