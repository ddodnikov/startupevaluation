package com.theoryx.xseed.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.Question;

@Repository
@Transactional
public interface QuestionRepository extends PagingAndSortingRepository<Question, Integer>{
	
	List<Question> findByFilter(boolean filter);
	List<Question> findByKpi(boolean kpi);
	List<Question> findByAlgo(boolean algo);

}
