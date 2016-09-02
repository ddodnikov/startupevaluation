package com.theoryx.xseed.service;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.theoryx.xseed.dto.StartupDTO;
import com.theoryx.xseed.dto.UserDTO;
import com.theoryx.xseed.model.User;
import com.theoryx.xseed.model.UserRole;
import com.theoryx.xseed.model.Error;
import com.theoryx.xseed.model.Startup;
import com.theoryx.xseed.repository.UserRepository;
import com.theoryx.xseed.utils.ValidationUtils;

@Service
public class UserServiceImpl implements UserService {
	
	private static final String KEY = "Th30RyXx533D"; // THEORYXXSEED
	@Autowired
	private MessageSource  messageSource;	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StartupService startupService;

	@Override
	public User createUser(UserDTO user) {
		User userModel = convertUserDTOToUser(user);
		userModel.setIsActive(user.isActive());
		userModel.setRole(user.getRole());
		userModel = userRepository.save(userModel);

		if (userModel != null) {
			return userModel;
		} else {
			return null;
		}
	}

	@Override
	public List<Error> validateRegisterInput(UserDTO user) {
		String name = "";
		if (user.getName() != null) {
			name = user.getName().trim();
		}
		String email = "";
		if (user.getEmail() != null) {
			email = user.getEmail().trim();
		}
		String password = "";
		if (user.getPassword() != null) {
			password = user.getPassword().trim();
		}
		String confirmationPassword = "";
		if (user.getConfirmationPassword() != null) {
			confirmationPassword = user.getConfirmationPassword().trim();
		}
		String startupName = "";
		if (user.getStartup().getName() != null) {
			startupName = user.getStartup().getName().trim();
		}
		
		List<Error> errors = new LinkedList<Error>();
		if (!ValidationUtils.validateUserEmail(email)) {
			errors.add(new Error(messageSource.getMessage("error.invalid-email", null, Locale.getDefault())));
		}
		
		if(ifExist(email)){
			errors.add(new Error(messageSource.getMessage("error.user-exist", null, Locale.getDefault())));
		}
		
		if (!ValidationUtils.isValidUserName(name)) {
			errors.add(new Error(messageSource.getMessage("error.invalid-username", null, Locale.getDefault())));
		}
		if (!ValidationUtils.isValidUserPassword(password, confirmationPassword)) {
			errors.add(new Error(messageSource.getMessage("error.invalid-password", null, Locale.getDefault())));
		}
		if (!ValidationUtils.isValidStartupName(startupName)) {
			errors.add(new Error(messageSource.getMessage("error.invalid-startup-name", null, Locale.getDefault())));
		}

		return errors;
	}

	@Override
	public UserDTO updateUserInfo(UserDTO editedUser, UserDTO currentUser, User user) {
		String name = "";
		if (editedUser.getName() != null) {
			name = editedUser.getName().trim();
		}
		String password = "";
		if (editedUser.getPassword() != null) {
			password = editedUser.getPassword().trim();
		}
		if (!name.isEmpty()) {
			user.setName(name);
			currentUser.setName(name);
		}
		if (!password.isEmpty()) {
			user.setHashedPassword(new BCryptPasswordEncoder().encode(password));
		}

		user = userRepository.save(user);

		if (user != null) {
			return currentUser;
		} else {
			return null;
		}
	}

	public List<Error> validateNewUserProfile(UserDTO user) {
		List<Error> errors = new ArrayList<Error>();
		String name = "";
		if (user.getName() != null) {
			name = user.getName().trim();
		}
		String password = "";
		if (user.getPassword() != null) {
			password = user.getPassword().trim();
		}
		String confirmationPassword = "";
		if (user.getConfirmationPassword() != null) {
			confirmationPassword = user.getConfirmationPassword().trim();
		}
		if (name.isEmpty() || password.isEmpty() || confirmationPassword.isEmpty()) {
			errors.add(new Error(messageSource.getMessage("error.no-input", null, Locale.getDefault())));

			return errors;
		} else {
			if (!ValidationUtils.isValidUserName(name)) {
				errors.add(new Error(messageSource.getMessage("error.invalid-username", null, Locale.getDefault())));
			}

			if (!ValidationUtils.isValidUserPassword(password, confirmationPassword)) {
				errors.add(new Error(messageSource.getMessage("error.invalid-password", null, Locale.getDefault())));
			}
		}
		return errors;
	}

	@Override
	public List<Error> validateEditProfileInput(UserDTO user) {
		List<Error> errors = new ArrayList<Error>();
		String name = "";
		if (user.getName() != null) {
			name = user.getName().trim();
		}
		String password = "";
		if (user.getPassword() != null) {
			password = user.getPassword().trim();
		}
		String confirmationPassword = "";
		if (user.getConfirmationPassword() != null) {
			confirmationPassword = user.getConfirmationPassword().trim();
		}
		if (name.isEmpty() && password.isEmpty() && confirmationPassword.isEmpty()) {
			errors.add(new Error(messageSource.getMessage("error.no-input", null, Locale.getDefault())));
			return errors;
		}
		if (!name.isEmpty() && !ValidationUtils.isValidUserName(name)) {
			errors.add(new Error(messageSource.getMessage("error.invalid-username", null, Locale.getDefault())));
		}
		if ((!password.isEmpty() || !confirmationPassword.isEmpty())
				&& !ValidationUtils.isValidUserPassword(password, confirmationPassword)) {
			errors.add(new Error(messageSource.getMessage("error.invalid-password", null, Locale.getDefault())));
		}
		return errors;
	}

	@Override
	public User convertUserDTOToUser(UserDTO userDto) {
		Integer id = null;
		if (userDto.getId() != null) {
			id = userDto.getId();
		}
		String name = null;
		if (userDto.getName() != null) {
			name = userDto.getName();
		}
		String email = null;
		if (userDto.getEmail() != null) {
			email = userDto.getEmail();
		}
		String password = null;
		if (userDto.getPassword() != null) {
			password = userDto.getPassword();
		}
		String hashedPassword = new BCryptPasswordEncoder().encode(password);
		User user = new User(name, email, hashedPassword);
		user.setId(id);
		return user;
	}

	@Override
	public UserDTO getUserDTOfromUser(User user) {
		if (user != null) {
			String name = user.getName();
			String email = user.getEmail();
			UserRole role = user.getRole();
			Integer id = user.getId();

			UserDTO userDto = new UserDTO();
			userDto.setId(id);
			userDto.setRole(role);
			userDto.setEmail(email);
			userDto.setName(name);
			userDto.setPassword("");
			userDto.setConfirmationPassword("");

			return userDto;
		} else {
			return null;
		}
	}

	@Override
	public UserDTO convertUserToUserDTO(User user) {
		UserDTO userDto = getUserDTOfromUser(user);
		List<Startup> startups = user.getStartups();
		List<StartupDTO> startupdtos = new ArrayList<StartupDTO>();
		if (startups != null && !startups.isEmpty()) {
			for (Startup startup : startups) {
				startupdtos.add(startupService.convertStartupToStartupDTO(startup));
			}
			userDto.setStartup(startupdtos.get(0));
		} else {
			userDto.setStartup(null);
		}
		return userDto;
	}

	@Override
	public User getByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Map<UserDTO, List<Error>> getUserById(Integer id) {
		User userModel = userRepository.findOne(id);
		List<Error> errors = new LinkedList<Error>();
		Map<UserDTO, List<Error>> result = new HashMap<UserDTO, List<Error>>();
		if (userModel != null) {
			UserDTO user = new UserDTO();
			user.setId(userModel.getId());
			user.setEmail(userModel.getEmail());
			user.setName(userModel.getName());
			user.setPassword(userModel.getHashedPassword());
			user.setConfirmationPassword(userModel.getHashedPassword());
			user.setRole(user.getRole());
			result.put(user, errors);
		} else {
			errors.add(new Error(messageSource.getMessage("error.no-such-user", null, Locale.getDefault())));
			result.put(null, errors);
		}
		return result;
	}

	@Override
	public Map<UserDTO, List<Error>> getUserByEmail(String email) {
		List<Error> errors = new LinkedList<Error>();
		Map<UserDTO, List<Error>> result = new HashMap<UserDTO, List<Error>>();
		if (ValidationUtils.validateUserEmail(email)) {
			User user = userRepository.findByEmail(email);
			if (user != null) {
				UserDTO userDTO = new UserDTO();
				userDTO.setId(user.getId());
				userDTO.setEmail(user.getEmail());
				userDTO.setName(user.getName());
				userDTO.setPassword(user.getHashedPassword());
				userDTO.setConfirmationPassword(user.getHashedPassword());
				userDTO.setRole(user.getRole());
				userDTO.setActive(user.getIsActive());

				if (user.getRole().equals(UserRole.USER)) {
					userDTO.setStartup(startupService.convertStartupToStartupDTO(user.getStartups().get(0)));
				} else {
				}
				result.put(userDTO, errors);
			} else {
				errors.add(new Error(messageSource.getMessage("error.no-such-user", null, Locale.getDefault())));
				result.put(null, errors);
			}
		} else {
			errors.add(new Error(messageSource.getMessage("error.invalid-email", null, Locale.getDefault())));
			result.put(null, errors);
		}
		return result;
	}

	@Override
	public UserDTO getCurrentUserDTO() {
		User currentUser = getCurrentUser();
		UserDTO currentUserDto = convertUserToUserDTO(currentUser);
		if (currentUserDto != null) {
			return currentUserDto;
		} else {
			return null;
		}
	}

	@Override
	public User getCurrentUser() {
		org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String email = springUser.getUsername();
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public boolean updateUser(UserDTO userDTO) {
		String password = userDTO.getPassword();
		String confirmationPassword = userDTO.getConfirmationPassword();
		if (ValidationUtils.isValidUserPassword(password, confirmationPassword)) {
			String hashedPassword = new BCryptPasswordEncoder().encode(password);
			userDTO.setPassword(hashedPassword);
			userDTO.setConfirmationPassword(hashedPassword);
			User user = userRepository.findByEmail(userDTO.getEmail());
			user.setName(userDTO.getName());
			user.setHashedPassword(userDTO.getPassword());
			user.setIsActive(userDTO.isActive());
			userRepository.save(user);
			return true;
		} else {
			return false;
		}
	}

	public String generatePasswordToken(UserDTO user) {
		String email = user.getEmail();
		String name = user.getName();
		LocalDate d = LocalDate.now();
		String token = email + KEY + name + KEY + d.getDayOfMonth() + "-" + d.getMonthValue() + "-" + d.getDayOfYear();
		String hash = DigestUtils.md5Hex(token);
		return hash;
	}

	public String generateActivationToken(UserDTO user) {
		String email = user.getEmail();
		LocalDate d = LocalDate.now();
		String token = email + KEY + d.getDayOfMonth() + "-" + d.getMonthValue() + "-" + d.getDayOfYear() + KEY;
		String hash = DigestUtils.md5Hex(token);
		return hash;
	}

	@Override
	public boolean ifExist(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public boolean deleteUser(UserDTO user) {
		if (user != null && user.getId() != null) {
			userRepository.delete(user.getId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<User> getAllAdmins() {
		List<User> admins = userRepository.findByRole(UserRole.ADMIN);
		return admins;
	}
	
	@Override
	public void authenticateUserAndInitializeSessionByUsername(String username, UserDetailsService userDetailsManager) {
		try {
			UserDetails user = userDetailsManager.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
					user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
