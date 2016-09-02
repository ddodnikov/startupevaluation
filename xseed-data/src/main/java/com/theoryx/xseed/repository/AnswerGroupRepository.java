package com.theoryx.xseed.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.AnswerGroup;

@Repository
@Transactional
public interface AnswerGroupRepository extends PagingAndSortingRepository<AnswerGroup, Integer> {

}
