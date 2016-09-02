package com.theoryx.xseed.service;

import com.theoryx.xseed.dto.MembershipDTO;
import com.theoryx.xseed.model.Membership;
import com.theoryx.xseed.model.Startup;
import com.theoryx.xseed.model.User;

public interface MembershipService {

	/**
	 * Creates a membership
	 * 
	 * @param user
	 * @param startup
	 * @return Membership model
	 */
	Membership createMembership(User user, Startup startup);

	/**
	 * Saves membership
	 * 
	 * @param membership
	 * @return Membership. The returned membership contains the generated id.
	 */
	Membership saveMembership(Membership membership);

	/**
	 * Delete membership
	 * 
	 * @param membership
	 * @return nothing
	 */
	void deleteMembership(Membership membership);

	/**
	 * Delete membership by MembershipDTO
	 * 
	 * @param membership
	 * @return nothing
	 */
	void deleteMembership(MembershipDTO membership);

	/**
	 * Converts Membership model to MembershipDTO
	 * 
	 * @param membership
	 * @return MembershipDTO
	 */
	MembershipDTO convertMembershipToMembershipDTO(Membership membership);

}
