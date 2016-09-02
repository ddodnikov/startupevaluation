package com.theoryx.xseed.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.theoryx.xseed.annotation.Link;
import com.theoryx.xseed.dto.StartupDTO;
import com.theoryx.xseed.dto.UserDTO;
import com.theoryx.xseed.service.MailService;
import com.theoryx.xseed.service.MembershipService;
import com.theoryx.xseed.service.StartupService;
import com.theoryx.xseed.service.UserService;
import com.theoryx.xseed.model.Error;
import com.theoryx.xseed.model.Membership;
import com.theoryx.xseed.model.Startup;
import com.theoryx.xseed.model.User;
import com.theoryx.xseed.model.UserRole;

@Controller
public class UserController {

	private static final String from = "xseed.theoryx@gmail.com";
	private static final String subject = "Reset password";

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private UserService userService;
	@Autowired
	private StartupService startupService;
	@Autowired
	private MembershipService membershipService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private MailService mailService;

	/**
	 * This method returns the index page of the application
	 * 
	 * @param request The request object
	 */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String getIndexPage(HttpServletRequest request) {
		if (request.getSession().getAttribute("currentUser") != null) {
			return "home";
		} else {
			return "index";
		}
	}

	/**
	 * This method returns the register page of the application
	 * 
	 * @param request
	 * @param model
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegistrationPage(HttpServletRequest request, Model model) {
		if (request.getSession().getAttribute("currentUser") != null) {
			return "home";
		} else {
			UserDTO userDto = new UserDTO();
			StartupDTO startupDto = new StartupDTO();
			userDto.setStartup(startupDto);
			model.addAttribute("userdto", userDto);
			model.addAttribute("startup", startupDto);
			return "register";
		}
	}

	/**
	 * This method returns the edit profile page of the application
	 * 
	 * @param request
	 * @param model
	 */

	@Link(label = "Edit profile", url = "/editprofile", parent = "Home")
	@RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	public String getEditProfilePage(HttpServletRequest request, Model model) {
		if (request.getSession().getAttribute("currentUser") != null) {
			model.addAttribute("userdto", userService.getCurrentUserDTO());
			request.getAttribute("breadCrumbs");
			model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
			return "editprofile";
		} else {
			return "redirect:/login";
		}
	}

	/**
	 * This method returns the login page of the application
	 * 
	 * @param request
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(HttpServletRequest request) {
		if (request.getSession().getAttribute("currentUser") != null) {
			return "home";
		} else {
			return "login";
		}
	}

	/**
	 * This method is used when the user submits the register form
	 * 
	 * @param request
	 * @param model
	 * @param user UserDTO The model that contains the submitted data
	 * 
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, Model model, @ModelAttribute("userdto") UserDTO user) {
		List<Error> errors = userService.validateRegisterInput(user);
		if (!errors.isEmpty()) {
			model.addAttribute("name", request.getParameter("name"));
			model.addAttribute("email", request.getParameter("email"));
			model.addAttribute("startup.name", request.getParameter("startup.name"));
			model.addAttribute("errors", errors);
			return "register";
		} else {
			user.setRole(UserRole.USER);
			user.setActive(true);
			User currentUser = userService.createUser(user);
			Startup currentStartup = startupService.createStartup(user.getStartup());
			Membership membership = new Membership(currentUser, currentStartup, UserRole.ADMIN);
			membership = membershipService.saveMembership(membership);

			if (currentUser != null && currentStartup != null && membership != null) {
				HttpSession session = request.getSession();
				String email = currentUser.getEmail();
				userService.authenticateUserAndInitializeSessionByUsername(email, userDetailsService);
				UserDTO currentUserDto = userService.convertUserToUserDTO(currentUser);
				StartupDTO currentStartupDto = startupService.convertStartupToStartupDTO(currentStartup);
				session.setAttribute("currentUser", currentUserDto);
				session.setAttribute("currentStartup", currentStartupDto);
				
				return "redirect:/home";
			} else {
				errors.add(
						new Error(messageSource.getMessage("error.registration", new Object[0], Locale.getDefault())));
				model.addAttribute("errors", errors);
				return "register";
			}
		}
	}

	/**
	 * This method is executed when the user submits the edit profile form
	 * 
	 * @param request
	 * @param model
	 * @param user UserDTO The model that contains the submitted data
	 */
	@Link(label = "Edit profile", url = "/editprofile", parent = "Home")
	@RequestMapping(value = "/editprofile", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String editProfile(HttpServletRequest request, Model model, @ModelAttribute("userdto") UserDTO user) {
		boolean isOnlyNameChanged = false;
		if (user.getPassword().isEmpty() && user.getConfirmationPassword().isEmpty()) {
			isOnlyNameChanged = true;
		}
		List<Error> errors = userService.validateEditProfileInput(user);
		if (!errors.isEmpty()) {
			model.addAttribute("name", request.getParameter("name"));
			model.addAttribute("errors", errors);
			model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
			return "editprofile";
		} else {
			HttpSession session = request.getSession();
			UserDTO currentUser = userService.updateUserInfo(user, (UserDTO) session.getAttribute("currentUser"),
					userService.getCurrentUser());
			if (currentUser != null) {
				if (!isOnlyNameChanged) {
					session.invalidate();
					return "redirect:/login";
				} else {
					session.setAttribute("currentUser", currentUser);
					model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
					return "redirect:/editprofile";
				}
			} else {
				errors.add(
						new Error(messageSource.getMessage("error.profile-edit", new Object[0], Locale.getDefault())));
				model.addAttribute("errors", errors);
				model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
				return "editprofile";
			}
		}
	}

	/**
	 * This method returns the home page of the application
	 * 
	 * @param request
	 */
	@Link(label = "Home", url = "/home", parent = "")
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHomePage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserDTO currentUserDto = userService.getCurrentUserDTO();
		if (session.getAttribute("currentUser") == null) {
			session.setAttribute("currentUser", currentUserDto);
		}
		session.setAttribute("currentStartup", currentUserDto.getStartup());
		return "home";
	}

	/**
	 * This method returns the 403 page - access denied - of the application.
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String getForbiddenPage() {
		return "forbidden";
	}

	/**
	 * This method returns the reset password page of the application
	 */
	@RequestMapping(value = "/reset-password", method = RequestMethod.GET)
	public String resetPassword() {
		return "/reset-password";
	}

	/**
	 * This method is executed when the user submits the reset password form
	 * 
	 * @param email The email for sending the reset password link.
	 * @param model
	 */
	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public String sendResetPasswordEmail(@RequestParam("email") String email, Model model, HttpServletRequest request) {
		Map<UserDTO, List<Error>> result = userService.getUserByEmail(email);
		UserDTO userDTO = null;
		List<Error> errors = null;
		for (Entry<UserDTO, List<Error>> entry : result.entrySet()) {
			userDTO = entry.getKey();
			errors = entry.getValue();
		}
		if (userDTO == null) {
			model.addAttribute("errors", errors);
		} else {
			String token = userService.generatePasswordToken(userDTO);
			/*URI url = null;
			try {
				url = new URI(request.getRequestURL().toString());
			} catch (URISyntaxException e) {
				errors.add(new Error(messageSource.getMessage("error.uri", new Object[0], Locale.getDefault())));
				e.printStackTrace();
			}*/
			boolean mailResult = mailService.sendChangePasswordLink(from, email, subject, token);
			if (mailResult) {
				model.addAttribute("success",
						messageSource.getMessage("success.email", new Object[0], Locale.getDefault()));
			} else {
				errors.add(new Error(messageSource.getMessage("error.mail-error", new Object[0], Locale.getDefault())));
				model.addAttribute("errors", errors);
			}
		}
		return "/reset-password";
	}

	/**
	 * This method returns the forgotten password page where the user can change
	 * his password
	 * 
	 * @param token
	 * @param email
	 * @param model
	 */
	@RequestMapping(value = "/forgottenpassword", method = RequestMethod.GET)
	public String createNewPassword(@RequestParam("token") String token, @RequestParam("email") String email,
			Model model) {
		Map<UserDTO, List<Error>> result = userService.getUserByEmail(email);
		UserDTO userDTO = null;
		List<Error> errors = null;
		for (Entry<UserDTO, List<Error>> entry : result.entrySet()) {
			userDTO = entry.getKey();
			errors = entry.getValue();
		}
		if (userDTO == null) {
			model.addAttribute("errors", errors);
		} else {
			String generatedToken = userService.generatePasswordToken(userDTO);
			if (token.equals(generatedToken)) {
				// ok
				model.addAttribute("email", email);
				model.addAttribute("token", token);
			} else {
				// invalid token
				errors.add(
						new Error(messageSource.getMessage("error.invalid-token", new Object[0], Locale.getDefault())));
				model.addAttribute("errors", errors);
				model.addAttribute("email", email);
				model.addAttribute("token", token);
			}
		}
		return "/forgotten-password";
	}

	/**
	 * This method is executed when the user submits the forgotten password form
	 * and saves the new password.
	 * 
	 * @param password
	 * @param congirmationPassword
	 * @param email
	 * @param model
	 */
	@RequestMapping(value = "/forgottenpassword", method = RequestMethod.POST)
	public String createNewPassword(@RequestParam("password") String password,
			@RequestParam("confirmationPassword") String confirmationPassword, @RequestParam("email") String email,
			@RequestParam("token") String token, Model model) {
		// validation for null, empty or invalid email is in userservice;
		Map<UserDTO, List<Error>> result = userService.getUserByEmail(email);
		UserDTO userDTO = null;
		List<Error> errors = null;
		for (Entry<UserDTO, List<Error>> entry : result.entrySet()) {
			userDTO = entry.getKey();
			errors = entry.getValue();
		}
		if (userDTO == null) {
			model.addAttribute("errors", errors);
			model.addAttribute("email", email);
			model.addAttribute("token", token);
			return "/forgotten-password";
		} else {
			userDTO.setPassword(password);
			userDTO.setConfirmationPassword(confirmationPassword);
			boolean update = userService.updateUser(userDTO);
			if (!update) {
				errors = new LinkedList<Error>();
				errors.add(new Error(
						messageSource.getMessage("error.invalid-password", new Object[0], Locale.getDefault())));
				model.addAttribute("errors", errors);
				model.addAttribute("email", email);
				model.addAttribute("token", token);
				return "/forgotten-password";
			}
			return "redirect:/login";
		}
	}

	/**
	 * This method returns the show all users page
	 * 
	 * @param model
	 * @param request
	 */
	@Link(label = "All Users", parent = "Home", url = "/showallusers")
	@RequestMapping(value = "/showallusers", method = RequestMethod.GET)
	public String getAllUsers(Model model, HttpServletRequest request) {
		List<User> users = userService.getAllUsers();
		List<UserDTO> userDtos = new ArrayList<UserDTO>();
		for (User user : users) {
			userDtos.add(userService.convertUserToUserDTO(user));
		}
		model.addAttribute("users", userDtos);
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		return "showallusers";
	}

	/**
	 * This method returns the show all startups page
	 * 
	 * @param model
	 * @param request
	 */
	@Link(label = "Startups", parent = "Home", url = "/showallstartups")
	@RequestMapping(value = "/showallstartups", method = RequestMethod.GET)
	public String getAllStartups(Model model, HttpServletRequest request) {
		List<Startup> startups = startupService.getAllStartups();
		List<StartupDTO> startupDtos = new ArrayList<StartupDTO>();
		for (Startup startup : startups) {
			startupDtos.add(startupService.convertStartupToStartupDTO(startup));
		}
		model.addAttribute("startups", startupDtos);
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		return "showallstartups";
	}

	/**
	 * This method logout the user and redirects him to index page.
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Logout(request, response);
		return "redirect:/index";
	}

	/**
	 * Invalidates the session and logout the user
	 */
	public static void Logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			request.getSession().invalidate();
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
	}
	
	/**
	 * This method shows the page where a newly added user can activate his account
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param model Model
	 * @param user UserDTO
	 * @return "login" String
	 */
	@RequestMapping(value = "/updatenewuser", method = RequestMethod.POST)
	public String updateNewUserInfo(HttpServletRequest request,HttpServletResponse response, Model model, @ModelAttribute("UserDTO") UserDTO user) {
		List<Error> errors = userService.validateNewUserProfile(user);
		if (!errors.isEmpty()) {
			model.addAttribute("email", user.getEmail());
			model.addAttribute("errors", errors);
			model.addAttribute("userdto", user);
			System.out.println("1");
			return "/editnewuserprofile";
		} else {
			user.setActive(true);
			boolean result = userService.updateUser(user);
			if (result) {
				return "redirect:/login";
			} else {
				errors.add(new Error(messageSource.getMessage("error.user-not-updated", new Object[0], Locale.getDefault())));
				model.addAttribute("errors", errors);
				model.addAttribute("userdto", user);
				System.out.println("2");
				return "/editnewuserprofile";
			}
		}
	}
	

	/**
	 * This method shows the page where a newly added user can activate his account
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param model Model
	 * @param email String
	 * @param token String
	 * @return "editnewuserprofile" String
	 */
	@RequestMapping(value = "/invitation", method = RequestMethod.GET)
	public String editAddedUser(@RequestParam("token") String token, @RequestParam("email") String email, Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<UserDTO, List<Error>> result = userService.getUserByEmail(email);
		UserDTO userDTO = null;
		List<Error> errors = null;
		for (Entry<UserDTO, List<Error>> entry : result.entrySet()) {
			userDTO = entry.getKey();
			errors = entry.getValue();
		}
		if (userDTO == null) {
			model.addAttribute("errors", errors);
		} else {
			String generatedToken = userService.generateActivationToken(userDTO);
			if(userDTO.isActive()){
				if (token.equals(generatedToken)) {
					errors.add(new Error(messageSource.getMessage("error.account-already-activated", new Object[0], Locale.getDefault())));
				}else{
					errors.add(new Error(messageSource.getMessage("error.expired-token", new Object[0], Locale.getDefault())));
				}
				model.addAttribute("errors", errors);
				return "/login";
			}else{			
				if (token.equals(generatedToken)) {
					Logout(request, response);
					model.addAttribute("email", email);
				}else{
					String newToken = userService.generateActivationToken(userDTO);
					/*URI url = null;
					try {
						url = new URI(request.getRequestURL().toString());
					} catch (URISyntaxException e) {
						errors.add(new Error(messageSource.getMessage("error.uri", new Object[0], Locale.getDefault())));
						e.printStackTrace();
					}*/
					mailService.sendInvitationLinkForStartup(from, email, subject, newToken, userDTO.getStartup().getName());
					errors.add(new Error(messageSource.getMessage("error.new-activation-link", new Object[0], Locale.getDefault())));
					model.addAttribute("errors", errors);
					return "/login";
				}
			}
		}

		model.addAttribute("userdto", userDTO);
		return "/editnewuserprofile";
	}

}
