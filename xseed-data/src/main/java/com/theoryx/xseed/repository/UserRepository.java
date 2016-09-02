package com.theoryx.xseed.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.theoryx.xseed.model.User;
import com.theoryx.xseed.model.UserRole;

@Repository
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	
	public User findByEmail(String email);
	public List<User> findByName(String name);
	public List<User> findByIsActive(Boolean isActive);
	public List<User> findByRole(UserRole role);
}
