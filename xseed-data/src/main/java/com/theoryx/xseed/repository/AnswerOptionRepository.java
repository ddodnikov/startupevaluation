package com.theoryx.xseed.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.AnswerOption;

@Repository
@Transactional
public interface AnswerOptionRepository extends PagingAndSortingRepository<AnswerOption, Integer>{
	
	List<AnswerOption> findByGroupId(Integer groupId);
	AnswerOption findByText(String text);
	Set<AnswerOption> findByTextLike(String text);
	
}
