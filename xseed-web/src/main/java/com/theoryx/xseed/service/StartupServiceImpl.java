package com.theoryx.xseed.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.CountryDTO;
import com.theoryx.xseed.dto.StartupDTO;
import com.theoryx.xseed.dto.UserDTO;
import com.theoryx.xseed.model.Startup;
import com.theoryx.xseed.model.User;
import com.theoryx.xseed.model.Country;
import com.theoryx.xseed.model.Error;
import com.theoryx.xseed.model.Membership;
import com.theoryx.xseed.repository.StartupRepository;
import com.theoryx.xseed.utils.ValidationUtils;

@Service
public class StartupServiceImpl implements StartupService {

	@Autowired
	private MessageSource  messageSource;
	@Autowired
	private StartupRepository startupRepository;
	@Autowired
	private CountryService countryService;
	@Autowired
	private UserService userService;
	@Autowired
	private MembershipService membershipService;

	@Override
	public Startup createStartup(StartupDTO startupDto) {
		Startup startup = convertStartupDTOToStartup(startupDto);
		startup = startupRepository.save(startup);
		if (startup != null) {
			return startup;
		} else {
			return null;
		}
	}

	@Override
	public Startup convertStartupDTOToStartup(StartupDTO startupDto) {
		if (startupDto != null) {
			Integer id = startupDto.getId();
			String name = startupDto.getName();
			String email = startupDto.getEmail();
			String vat = startupDto.getVat();
			String website = startupDto.getWebsite();
			String phone = startupDto.getPhone();
			CountryDTO country = startupDto.getCountry();

			Startup startup = new Startup(name, email, phone, website, vat);
			startup.setCountry(countryService.convertCountryDTOToCountry(country));
			startup.setId(id);
			return startup;
		} else {
			return null;
		}
	}

	@Override
	public StartupDTO convertStartupToStartupDTO(Startup startup) {
		if (startup != null) {
			Integer id = startup.getId();
			String name = startup.getName();
			String email = startup.getEmail();
			String vat = startup.getVat();
			String website = startup.getWebsite();
			String phone = startup.getPhone();
			Country country = startup.getCountry();

			List<User> users = startup.getUsers();
			List<UserDTO> userdtos = null;
			if (users != null) {
				userdtos = new ArrayList<UserDTO>();
				for (User user : users) {
					userdtos.add(userService.getUserDTOfromUser(user));
				}
			}
			StartupDTO startupDTO = new StartupDTO(name, email, phone, website, vat);
			startupDTO.setCountry(countryService.convertCountryToCountryDTO(country));
			startupDTO.setId(id);
			startupDTO.setUsers(userdtos);
			return startupDTO;
		} else {
			return null;
		}
	}

	@Override
	public List<Error> validateStartupInfo(StartupDTO startupDTO) {
		List<Error> errors = new LinkedList<Error>();
		String name = startupDTO.getName();
		String email = startupDTO.getEmail();
		String phone = startupDTO.getPhone();
		String website = startupDTO.getWebsite();
		String vat = startupDTO.getVat();
		
		if (name != null) {
			name = name.trim();
			if (!ValidationUtils.isValidStartupName(name)) {
				errors.add(new Error(messageSource.getMessage("error.invalid-startup-name", null, Locale.getDefault())));
			}
		} else {
			errors.add(new Error(messageSource.getMessage("error.invalid-startup-name", null, Locale.getDefault())));
		}
		if (email != null) {
			email = email.trim();
			if (!ValidationUtils.isValidStartupEmail(email)) {
				errors.add(new Error(messageSource.getMessage("error.invalid-startup-email", null, Locale.getDefault())));
			}
		}
		if (phone != null) {
			phone = phone.trim();
			if (!ValidationUtils.isValidStartupPhone(phone)) {
				errors.add(new Error(messageSource.getMessage("error.invalid-startup-phone", null, Locale.getDefault())));
			}
		}
		if (website != null) {
			website = website.trim();
			if (!ValidationUtils.isValidStartupWebsite(website)) {
				errors.add(new Error(messageSource.getMessage("error.invalid-startup-website", null, Locale.getDefault())));
			}
		}
		if (vat != null) {
			vat = vat.trim();
			if (!ValidationUtils.isValidStartupVAT(vat)) {
				errors.add(new Error(messageSource.getMessage("error.invalid-startup-vat", null, Locale.getDefault())));
			}
		}
		return errors;
	}

	@Override
	public Map<StartupDTO, List<Error>> normalizeStartup(StartupDTO startupDTO) {
		List<Error> errors = new LinkedList<Error>();
		if (startupDTO == null) {
			errors.add(new Error(messageSource.getMessage("error.null-startup", null, Locale.getDefault())));
		} else {
			CountryDTO countryDTO = startupDTO.getCountry();
			if (countryDTO == null) {
				errors.add(new Error(messageSource.getMessage("error.null-country", null, Locale.getDefault())));
			} else {
				String name = startupDTO.getName();
				String email = startupDTO.getEmail();
				String phone = startupDTO.getPhone();
				String website = startupDTO.getWebsite();
				String vat = startupDTO.getVat();
				if (name != null) {
					name = name.trim();
				}
				if (email != null) {
					email = email.trim();
				}
				if (phone != null) {
					phone = phone.trim();
				}
				if (website != null) {
					website = website.trim();
				}
				if (vat != null) {
					vat = vat.trim();
				}
				startupDTO.setName(name);
				startupDTO.setEmail(email);
				startupDTO.setPhone(phone);
				startupDTO.setWebsite(website);
				startupDTO.setVat(vat);

				if (countryDTO.getName() == null) {
					errors.add(new Error(messageSource.getMessage("error.null-country-name", null, Locale.getDefault())));
				} else {
					if (countryDTO.getName().equals("0")) {
						startupDTO.setCountry(null);
					} else {
						countryDTO = countryService.getCountryDTOByName(countryDTO);
						if (countryDTO == null) {
							errors.add(new Error(messageSource.getMessage("error.null-country", null, Locale.getDefault())));
						} else {
							startupDTO.setCountry(countryDTO);
						}
					}
				}
			}
		}

		Map<StartupDTO, List<Error>> result = new HashMap<StartupDTO, List<Error>>();
		result.put(startupDTO, errors);
		return result;
	}

	@Override
	public Map<StartupDTO, List<Error>> updateStartup(StartupDTO startupDTO) {
		Map<StartupDTO, List<Error>> result = new HashMap<StartupDTO, List<Error>>();
		List<Error> errors = new LinkedList<Error>();
		if (startupDTO != null) {
			String name = startupDTO.getName();
			String email = startupDTO.getEmail();
			String phone = startupDTO.getPhone();
			String website = startupDTO.getWebsite();
			String vat = startupDTO.getVat();
			Startup startup = startupRepository.findOne(startupDTO.getId());
			if (startup != null) {
				startup.setName(name);
				startup.setEmail(email);
				startup.setPhone(phone);
				startup.setWebsite(website);
				startup.setVat(vat);
				startup.setCountry(countryService.convertCountryDTOToCountry(startupDTO.getCountry()));
				Startup saved = startupRepository.save(startup);
				StartupDTO startupDTOSaved = convertStartupToStartupDTO(saved);
				result.put(startupDTOSaved, errors);
			} else {
				errors.add(new Error(messageSource.getMessage("error.update-startup", null, Locale.getDefault())));
				result.put(null, errors);
			}
		} else {
			errors.add(new Error(messageSource.getMessage("error.null-startup", null, Locale.getDefault())));
			result.put(null, errors);
		}
		return result;
	}

	@Override
	public Map<UserDTO, List<Error>> addUser(User user, Startup startup) {
		List<Error> errors = new LinkedList<Error>();
		UserDTO userDTO = null;
		if (user == null) {
			errors.add(new Error(messageSource.getMessage("error.creating-user", null, Locale.getDefault())));
		} else {
			if (startup == null) {
				errors.add(new Error(messageSource.getMessage("error.no-such-startup", null, Locale.getDefault())));
			} else {
				Membership membership = membershipService.createMembership(user, startup);
				if (membership == null) {
					errors.add(new Error(messageSource.getMessage("error.creating-membership", null, Locale.getDefault())));
				} else {
					List<Membership> memberships = new LinkedList<Membership>();
					if(user.getMemberships()!= null){
						memberships = user.getMemberships();
					}
					memberships.add(membership);
					user.setMemberships(memberships);
					userDTO = userService.convertUserToUserDTO(user);
					userDTO.setMembership(membershipService.convertMembershipToMembershipDTO(membership));
				}
			}
		}
		Map<UserDTO, List<Error>> result = new HashMap<UserDTO, List<Error>>();
		result.put(userDTO, errors);
		return result;
	}

	@Override
	public List<Startup> getAllStartups() {
		return (List<Startup>) startupRepository.findAll();
	}
}
