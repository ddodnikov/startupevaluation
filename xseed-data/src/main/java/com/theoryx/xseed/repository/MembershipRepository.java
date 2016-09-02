package com.theoryx.xseed.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.Membership;

@Repository
@Transactional
public interface MembershipRepository extends PagingAndSortingRepository<Membership, Integer>{
	
	List<Membership> findByUserId(Integer userId);
	List<Membership> findByStartupId(Integer startupId);

}
