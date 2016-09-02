package com.theoryx.xseed.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.theoryx.xseed.annotation.Link;
import com.theoryx.xseed.dto.QuestionDTO;
import com.theoryx.xseed.dto.UserDTO;
import com.theoryx.xseed.model.Error;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.User;
import com.theoryx.xseed.model.UserRole;
import com.theoryx.xseed.service.MailService;
import com.theoryx.xseed.service.QuestionService;
import com.theoryx.xseed.service.UserService;
import com.theoryx.xseed.utils.ValidationUtils;

@Controller
public class AdminController {

	private static final String from = "xseed.theoryx@gmail.com";
	private static final String subject = "Admin Invitation";
	private static final String EMPTY_NAME = "";
	private static final String DUMMY_PASSWORD = "DummyPassword123Th30RyX";
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailService;
	@Autowired
	private QuestionService questionService;
	
	/**
	 * This method shows the administrators' login page
	 * 
	 * @return "/admin" String
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAdminLoginPage() {
		return "/admin";
	}

	/**
	 * This method shows a page with all the administrators
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return "admins" String
	 */
	@Link(label = "Administrators", parent = "Home", url = "/admins")
	@RequestMapping(value = "/admins", method = RequestMethod.GET)
	public String showAdmins(Model model, HttpServletRequest request) {
		List<User> admins = userService.getAllAdmins();
		if (admins == null) {
			admins = new LinkedList<User>();
		}
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		model.addAttribute("admins", admins);
		return "/admins";
	}

	/**
	 * This method shows a page to add administrators
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return "addadmin" String
	 */
	@Link(label = "Add administrator", parent = "Administrators", url = "/addadmin")
	@RequestMapping(value = "/addadmin", method = RequestMethod.GET)
	public String getAddingAddminsPage(Model model, HttpServletRequest request) {
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		return "/addadmin";
	}

	/**
	 * This method is used when a request to add admin is sent
	 * 
	 * @param request HttpServletRequest
	 * @param email String
	 * @param model Model
	 * @return "addadmin" String
	 */
	@Link(label = "Add administrator", parent = "Administrators", url = "/addadmin")
	@RequestMapping(value = "/addadmin", method = RequestMethod.POST)
	public String addAddmin(HttpServletRequest request, Model model, @RequestParam("email") String email) {
		List<Error> errors = new LinkedList<Error>();
		if (ValidationUtils.validateUserEmail(email)) {
			email = email.trim();
			if (userService.ifExist(email)) {
				errors.add(new Error(messageSource.getMessage("error.user-exists", new Object[0], Locale.getDefault())));
			} else {
				UserDTO userDTO = new UserDTO(EMPTY_NAME, email, DUMMY_PASSWORD, DUMMY_PASSWORD, false, UserRole.ADMIN);
				User user = userService.createUser(userDTO);
				userDTO = userService.convertUserToUserDTO(user);

				String token = userService.generateActivationToken(userDTO);

			
				/*URI url = null;
				try {
					url = new URI(request.getRequestURL().toString());
				} catch (URISyntaxException e) {
					errors.add(new Error(messageSource.getMessage("error.uri", new Object[0], Locale.getDefault())));
					e.printStackTrace();
				}*/
				boolean mailResult = mailService.sendAdminCreationEmail(from, email, subject, token);
				if (mailResult) {
					model.addAttribute("success", messageSource.getMessage("success.email", new Object[0], Locale.getDefault()));
				} else {
					errors.add(new Error(messageSource.getMessage("error.mail-error", new Object[0], Locale.getDefault())));
				}
			}
		} else {
			errors.add(new Error(messageSource.getMessage("error.invalid-email", new Object[0], Locale.getDefault())));
		}
		model.addAttribute("errors", errors);
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		return "/addadmin";
	}

	/**
	 * This method is used when an added administrator clicks on the activation link
	 * 
	 * @param token String
	 * @param email String
	 * @param model Model
	 * @return "addadmin" String
	 */
	@RequestMapping(value = "/createadmin", method = RequestMethod.GET)
	public String createAdmin(@RequestParam("token") String token, @RequestParam("email") String email, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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
			if (userDTO.isActive()) {
				if (token.equals(generatedToken)) {
					errors.add(new Error(messageSource.getMessage("error.account-already-activated", new Object[0], Locale.getDefault())));
				} else {
					errors.add(new Error(
							messageSource.getMessage("error.expired-token", new Object[0], Locale.getDefault())));
				}
				model.addAttribute("errors", errors);
				return "/login";
			} else {
				if (token.equals(generatedToken)) {
					UserController.Logout(request, response);
					model.addAttribute("email", email);
				} else {
					String newToken = userService.generateActivationToken(userDTO);
					/*URI url = null;
					try {
						url = new URI(request.getRequestURL().toString());
					} catch (URISyntaxException e) {
						errors.add(new Error(messageSource.getMessage("error.uri", new Object[0], Locale.getDefault())));
						e.printStackTrace();
					}*/
					mailService.sendAdminCreationEmail(from, email, subject, newToken);
					errors.add(new Error(messageSource.getMessage("error.new-activation-link", new Object[0], Locale.getDefault())));
					model.addAttribute("errors", errors);
					return "/login";
				}
			}
		}
		model.addAttribute("userdto", userDTO);
		return "/createadmin";
	}
	
	@Link(label = "Algo questions", parent = "Home", url = "/algoquestions")
	@RequestMapping(value = "/algoquestions", method = RequestMethod.GET)
	public String getAlgoQuestions(HttpServletRequest request, Model model) {
		List<Question> algoQuestions = questionService.findAll();
		List<QuestionDTO> algoQuestionDtos = questionService.convert(algoQuestions);
		model.addAttribute("algoQuestions",algoQuestionDtos);
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		return "algoquestions";
	}

	@RequestMapping(value = "/algoquestions", method = RequestMethod.POST)
	public String saveAlgoQuestions(HttpServletRequest request, Model model) {
		List<Question> algoQuestions = questionService.findAll();
		for (Question question : algoQuestions) {
			String value = request.getParameter(question.getId().toString());
			if(value != null && !question.isAlgo()) {
				question.setAlgo(true);
				questionService.save(question);
			}
			if(value == null && question.isAlgo()) {
				question.setAlgo(false);
				questionService.save(question);
			}
		}
		return "redirect:/algoquestions";
	}

}
