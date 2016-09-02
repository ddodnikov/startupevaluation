package com.theoryx.xseed.service;

import java.util.List;
import java.util.Map;

import com.theoryx.xseed.dto.StartupDTO;
import com.theoryx.xseed.dto.UserDTO;
import com.theoryx.xseed.model.Startup;
import com.theoryx.xseed.model.User;
import com.theoryx.xseed.model.Error;

public interface StartupService {

	/**
	 * Saves a startup
	 * 
	 * @param startupDto
	 * @return Startup model with generated id
	 */
	Startup createStartup(StartupDTO startupDto);

	/**
	 * Converts StartupDTO to Startup
	 * 
	 * @param startupDto
	 * @return Startup model
	 */
	Startup convertStartupDTOToStartup(StartupDTO startupDto);

	/**
	 * Converts Startup model to StartupDTO
	 * 
	 * @param startup
	 * @return StartupDTO
	 */
	StartupDTO convertStartupToStartupDTO(Startup startup);

	/**
	 * Updates Startup
	 * 
	 * @param startupDTO
	 * @return Map<StartupDTO, List<Error>>
	 */
	Map<StartupDTO, List<Error>> updateStartup(StartupDTO startupDTO);

	/**
	 * Validates startup info(name, email, phone, website, vat)
	 * 
	 * @param startupDTO
	 * @return List<Error>
	 */
	List<Error> validateStartupInfo(StartupDTO startupDTO);

	/**
	 * Normalizes startup info
	 * 
	 * @param startupDTO
	 * @return Map<StartupDTO, List<Error>>
	 */
	Map<StartupDTO, List<Error>> normalizeStartup(StartupDTO startupDTO);

	/**
	 * Adds a user to a startup
	 * 
	 * @param user
	 * @param startup
	 * @return Map<UserDTO, List<Error>>
	 */
	Map<UserDTO, List<Error>> addUser(User user, Startup startup);

	/**
	 * Finds all startups
	 * 
	 * @return List<Startup>
	 */
	List<Startup> getAllStartups();

}
