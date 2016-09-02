package com.theoryx.xseed.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.QuestionCategory;

@Repository
@Transactional
public interface QuestionCategoryRepository extends PagingAndSortingRepository<QuestionCategory, Integer>{

}
