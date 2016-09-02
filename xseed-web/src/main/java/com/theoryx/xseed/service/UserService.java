package com.theoryx.xseed.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.theoryx.xseed.dto.UserDTO;
import com.theoryx.xseed.model.User;
import com.theoryx.xseed.model.Error;

public interface UserService {

	/**
	 * Converts User model to UserDTO
	 * 
	 * @param user
	 * @return
	 */
	UserDTO getUserDTOfromUser(User user);

	/**
	 * Saves user
	 * 
	 * @param user
	 * @return
	 */
	User createUser(UserDTO user);

	/**
	 * Deletes user
	 * 
	 * @param user
	 * @return
	 */
	boolean deleteUser(UserDTO user);

	/**
	 * Finds user by id
	 * 
	 * @param id
	 * @return
	 */
	Map<UserDTO, List<Error>> getUserById(Integer id);

	/**
	 * Finds user by email
	 * 
	 * @param email
	 * @return
	 */
	Map<UserDTO, List<Error>> getUserByEmail(String email);

	/**
	 * Convert User model of current user to UserDTO
	 * 
	 * @return
	 */
	UserDTO getCurrentUserDTO();

	/**
	 * Validates registration data
	 * 
	 * @param user
	 * @return
	 */
	List<Error> validateRegisterInput(UserDTO user);

	/**
	 * Validates the new user data
	 * 
	 * @param user
	 * @return List<Error>
	 */
	List<Error> validateNewUserProfile(UserDTO user);

	/**
	 * Validates the new user data
	 * 
	 * @param user
	 * @return List<Error>
	 */
	List<Error> validateEditProfileInput(UserDTO user);

	/**
	 * 
	 * @param editedUser
	 * @param currentUser
	 * @param user
	 * @return
	 */
	UserDTO updateUserInfo(UserDTO editedUser, UserDTO currentUser, User user);

	/**
	 * Returns the current user
	 * 
	 * @return
	 */
	User getCurrentUser();

	/**
	 * Updates user
	 * 
	 * @param userDTO
	 * @return boolean. If the update is successful - true, otherwise - false
	 */
	boolean updateUser(UserDTO userDTO);

	/**
	 * Generates the password token for reseting forgotten password
	 * 
	 * @param user
	 * @return
	 */
	String generatePasswordToken(UserDTO user);

	/**
	 * Generates the activation token for activating accounts
	 * 
	 * @param user
	 * @return token
	 */
	String generateActivationToken(UserDTO user);

	/**
	 * Finds a user by email
	 * 
	 * @param email
	 * @return User model
	 */
	User getByEmail(String email);

	/**
	 * Converts UserDTO to User model
	 * 
	 * @param user
	 * @return User model
	 */
	User convertUserDTOToUser(UserDTO user);

	/**
	 * Converts User model to UserDTO
	 * 
	 * @param user
	 * @return UserDTO
	 */
	UserDTO convertUserToUserDTO(User user);

	/**
	 * Checks there is already user with the email
	 * 
	 * @param email
	 * @return boolean. If there is already a user with this email - true,
	 *         otherwise - false;
	 */
	boolean ifExist(String email);

	/**
	 * Returns a list of all users
	 * 
	 * @param List<User>
	 */
	List<User> getAllUsers();

	/**
	 * Returns a list of all admins
	 * 
	 * @return List<User>
	 */
	List<User> getAllAdmins();

	/**
	 * This method is used to authenticate the user.
	 * 
	 * @param username
	 * @param userDetailsManager
	 * @param request
	 */
	void authenticateUserAndInitializeSessionByUsername(String username, UserDetailsService userDetailsManager);

}
