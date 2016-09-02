package com.theoryx.xseed.service;

import java.util.List;

import com.theoryx.xseed.dto.CountryDTO;
import com.theoryx.xseed.model.Country;

public interface CountryService {

	/**
	 * Returns all countries saved in the database
	 * 
	 * @return List<Country>
	 */
	List<Country> getAllCountries();

	/**
	 * Converts Country model to CountryDTO
	 * 
	 * @param country
	 * @return CountryDTO
	 */
	CountryDTO convertCountryToCountryDTO(Country country);

	/**
	 * Converts CountryDTO to Country model
	 * 
	 * @param dto
	 * @return Country
	 */
	Country convertCountryDTOToCountry(CountryDTO dto);

	/**
	 * Returns all Country models converted to CountryDTO
	 * 
	 * @return List<CountryDTO>
	 */
	List<CountryDTO> getAllContryDTOs();

	/**
	 * Returns CountryDTO by name if such country exists.
	 * 
	 * @param countryDTO
	 * @return CountryDTO or null if country does not exist.
	 */
	CountryDTO getCountryDTOByName(CountryDTO countryDTO);

}
