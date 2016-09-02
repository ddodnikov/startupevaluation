package com.theoryx.xseed.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.Survey;

@Repository
@Transactional
public interface SurveyRepository extends PagingAndSortingRepository<Survey, Integer>{

}
