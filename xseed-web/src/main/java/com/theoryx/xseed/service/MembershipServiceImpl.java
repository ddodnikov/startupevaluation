package com.theoryx.xseed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.MembershipDTO;
import com.theoryx.xseed.model.Membership;
import com.theoryx.xseed.model.Startup;
import com.theoryx.xseed.model.User;
import com.theoryx.xseed.model.UserRole;
import com.theoryx.xseed.repository.MembershipRepository;

@Service
public class MembershipServiceImpl implements MembershipService {

	@Autowired
	private MembershipRepository membershipRepository;

	@Override
	public Membership createMembership(User user, Startup startup) {
		Membership membership = new Membership(user, startup, UserRole.USER);
		membership = membershipRepository.save(membership);
		if (membership != null) {
			return membership;
		} else {
			return null;
		}
	}

	@Override
	public Membership saveMembership(Membership membership) {
		Membership mem = membershipRepository.save(membership);
		if (mem != null) {
			return mem;
		} else {
			return null;
		}
	}

	@Override
	public void deleteMembership(Membership membership) {
		membershipRepository.delete(membership);
	}

	public MembershipDTO convertMembershipToMembershipDTO(Membership membership) {
		MembershipDTO membershipDTO = new MembershipDTO();
		if (membership != null) {
			membershipDTO.setId(membership.getId());
		}
		return membershipDTO;
	}

	@Override
	public void deleteMembership(MembershipDTO membership) {
		if (membership != null) {
			membershipRepository.delete(membership.getId());
		}
	}

}
