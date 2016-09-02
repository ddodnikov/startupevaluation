package com.theoryx.xseed.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.Country;

@Repository
@Transactional
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {
	
	Country findByName(String name);

}
