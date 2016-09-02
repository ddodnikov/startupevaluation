package com.theoryx.xseed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.CountryDTO;
import com.theoryx.xseed.model.Country;
import com.theoryx.xseed.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<Country> getAllCountries() {
		List<Country> countries = (List<Country>) countryRepository.findAll();
		if (countries == null) {
			countries = new ArrayList<>();
		}
		return countries;
	}

	@Override
	public CountryDTO convertCountryToCountryDTO(Country country) {
		if (country != null) {
			CountryDTO dto = new CountryDTO();
			dto.setName(country.getName());
			dto.setId(country.getId());
			return dto;
		} else {
			return null;
		}
	}

	@Override
	public Country convertCountryDTOToCountry(CountryDTO dto) {
		if (dto != null) {
			Country country = new Country();
			country.setName(dto.getName());
			country.setId(dto.getId());
			return country;
		} else {
			return null;
		}
	}

	@Override
	public List<CountryDTO> getAllContryDTOs() {
		List<Country> countries = getAllCountries();
		List<CountryDTO> countriesDTO = new ArrayList<CountryDTO>(countries.size());
		for (Country country : countries) {
			CountryDTO countryDTO = convertCountryToCountryDTO(country);
			countriesDTO.add(countryDTO);
		}
		return countriesDTO;
	}

	public CountryDTO getCountryDTOByName(CountryDTO countryDTO) {
		if (countryDTO != null) {
			String countryName = countryDTO.getName();
			Country country = countryRepository.findByName(countryName);
			if (country != null) {
				return convertCountryToCountryDTO(country);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
}
