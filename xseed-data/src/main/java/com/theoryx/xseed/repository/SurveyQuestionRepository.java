package com.theoryx.xseed.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.SurveyQuestion;

@Repository
@Transactional
public interface SurveyQuestionRepository extends PagingAndSortingRepository<SurveyQuestion, Integer>{
	
	@Query(value = "SELECT COUNT(*) FROM survey_questions WHERE survey_id = :surveyId", nativeQuery = true)
	int getNumberOfSurveyQuestions(@Param("surveyId") Integer surveyId);
	
	SurveyQuestion findByQuestionId(Integer questionId);

	@Query(value = "SELECT COUNT(DISTINCT (pagenumber)) FROM survey_questions WHERE survey_id = :surveyId", nativeQuery = true)
	int getNumberOfPagesBySurveyId(@Param("surveyId") Integer surveyId);
}
