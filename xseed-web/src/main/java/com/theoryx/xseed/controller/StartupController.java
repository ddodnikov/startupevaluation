package com.theoryx.xseed.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.theoryx.xseed.annotation.Link;
import com.theoryx.xseed.dto.CountryDTO;
import com.theoryx.xseed.dto.StartupDTO;
import com.theoryx.xseed.dto.UserDTO;
import com.theoryx.xseed.model.Error;
import com.theoryx.xseed.model.Startup;
import com.theoryx.xseed.model.User;
import com.theoryx.xseed.model.UserRole;
import com.theoryx.xseed.service.CountryService;
import com.theoryx.xseed.service.MailService;
import com.theoryx.xseed.service.MembershipService;
import com.theoryx.xseed.service.StartupService;
import com.theoryx.xseed.service.UserService;
import com.theoryx.xseed.utils.ValidationUtils;

@Controller
public class StartupController {

	private static final String from = "xseed.theoryx@gmail.com";
	private static final String subject = "Startup Invitation";
	private static final String EMPTY_NAME = "";
	private static final String DUMMY_PASSWORD = "DummyPassword123Th30RyX";
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private StartupService startupService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private UserService userService;
	@Autowired
	private MembershipService membershipService;
	@Autowired
	private MailService mailService;

	/**
	 * This method shows the page to edit the current startup
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @return "editstartup" String
	 */
	@Link(label = "Edit startup", url = "/editstartup", parent = "Home")
	@RequestMapping(value = "/editstartup", method = RequestMethod.GET)
	public String getEditStartupPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		StartupDTO startupDTO = (StartupDTO) session.getAttribute("currentStartup");
		CountryDTO country = startupDTO.getCountry();
		if (country == null) {
			country = new CountryDTO();
			startupDTO.setCountry(country);
		}
		request.getAttribute("breadCrumbs");
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		model.addAttribute("startupdto", startupDTO);
		List<CountryDTO> countries = countryService.getAllContryDTOs();
		model.addAttribute("countries", countries);

		return "/editstartup";
	}

	/**
	 * This method is used when a request to edit the current startup is sent
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @param startupDTO
	 *            StartupDTO
	 * @return "editstartup" String
	 */
	@Link(label = "Edit startup", url = "/editstartup", parent = "Home")
	@RequestMapping(value = "editstartup", method = RequestMethod.POST)
	public String editStartup(Model model, HttpServletRequest request,
			@ModelAttribute("StartupDTO") StartupDTO startupDTO) {
		List<Error> errors = null;
		// normalization		
		Map<StartupDTO, List<Error>> result = startupService.normalizeStartup(startupDTO);
		if (result.containsKey(null)) {
			errors = result.get(null);
			model.addAttribute("errors", errors);
		} else {
			// no errors after normalization
			// validation
			errors = startupService.validateStartupInfo(startupDTO);
			if (errors.isEmpty()) {
				StartupDTO oldStartupDTO = (StartupDTO) request.getSession().getAttribute("currentStartup");
				startupDTO.setId(oldStartupDTO.getId());
				Map<StartupDTO, List<Error>> updateResult = startupService.updateStartup(startupDTO);
				if (updateResult.containsKey(null)) {
					model.addAttribute("errors", updateResult.get(null));
				} else {
					startupDTO = (StartupDTO) updateResult.keySet().toArray()[0];
					request.getSession().setAttribute("currentStartup", startupDTO);
				}
			} else {
				model.addAttribute("errors", errors);
			}
		}
		
		


		model.addAttribute("startupdto", startupDTO);
		List<CountryDTO> countries = countryService.getAllContryDTOs();
		model.addAttribute("countries", countries);
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		return "/editstartup";
	}

	/**
	 * This method shows the page to add a user to the current startup
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @return "adduser" String
	 */
	@Link(label = "New user", url = "/add-user-to-startup", parent = "Edit startup")
	@RequestMapping(value = "add-user-to-startup", method = RequestMethod.GET)
	public String addUserToStartupGet(HttpServletRequest request, Model model) {
		request.getAttribute("breadCrumbs");
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		return "/adduser";
	}

	/**
	 * This method shows all the users in the current startup
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @return "startupusers" String
	 */
	@Link(label = "Users", url = "/showusers", parent = "Edit startup")
	@RequestMapping(value = "showusers", method = RequestMethod.GET)
	public String showStartupUsersGet(HttpServletRequest request, Model model) {
		request.getAttribute("breadCrumbs");
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		return "/startupusers";
	}

	/**
	 * This method is used when a request to add a user to the current startup
	 * is sent
	 * 
	 * @param session
	 *            HttpSession
	 * @param model
	 *            Model
	 * @param email
	 *            String
	 * @return "adduser" String
	 */
	@Link(label = "New user", url = "/add-user-to-startup", parent = "Edit startup")
	@RequestMapping(value = "add-user-to-startup", method = RequestMethod.POST)
	public String addUserToStartup(HttpServletRequest request, HttpSession session, Model model,
			@RequestParam("email") String email) {
		List<Error> errors = new LinkedList<Error>();
		StartupDTO startupDTO = (StartupDTO) session.getAttribute("currentStartup");
		model.addAttribute("startupdto", startupDTO);
		if (ValidationUtils.validateUserEmail(email)) {
			email = email.trim();
			if (userService.ifExist(email)) {
				// add existing user to startup
				// check if user is added to the current startup
				errors.add(new Error(messageSource.getMessage("error.user-exists", new Object[0], Locale.getDefault())));
			} else {
				// create new user with role USER
				UserDTO userDTO = new UserDTO(EMPTY_NAME, email, DUMMY_PASSWORD, DUMMY_PASSWORD, false, UserRole.USER);
				userDTO.setStartup(startupDTO);
				User user = userService.createUser(userDTO);
				Startup startup = startupService.convertStartupDTOToStartup(startupDTO);
				Map<UserDTO, List<Error>> result = startupService.addUser(user, startup);
				if (result.containsKey(null)) {
					errors = result.get(null);
				} else {
					userDTO = (UserDTO) result.keySet().toArray()[0];
					String token = userService.generateActivationToken(userDTO);
					
					/*URI url = null;
					try {
						url = new URI(request.getRequestURL().toString());
					} catch (URISyntaxException e) {
						errors.add(new Error(messageSource.getMessage("error.uri", new Object[0], Locale.getDefault())));
						e.printStackTrace();
					}
					System.out.println("********************************");
					  InetAddress ip;
					  try {

						ip = InetAddress.getLocalHost();
						System.out.println("HostName: " + ip.getHostName());
						System.out.println("Current IP address : " + ip.getHostAddress());

					  } catch (UnknownHostException e) {

						e.printStackTrace();

					  }

					System.out.println(url.getAuthority().toString());
					System.out.println(url.getHost());
					System.out.println(url.getPort());*/
					boolean mailResult = mailService.sendInvitationLinkForStartup(from, email, subject, token,
							startupDTO.getName());
					if (mailResult) {
						UserDTO currentUserDto = userService.getCurrentUserDTO();
						session.setAttribute("currentStartup", currentUserDto.getStartup());
						model.addAttribute("success",
								messageSource.getMessage("success.email", new Object[0], Locale.getDefault()));
						return "/adduser";
					} else {
						membershipService.deleteMembership(userDTO.getMembership());
						userService.deleteUser(userDTO);
						errors.add(new Error(
								messageSource.getMessage("error.mail-error", new Object[0], Locale.getDefault())));
					}
				}
			}
		} else {
			errors.add(new Error(messageSource.getMessage("error.invalid-email", new Object[0], Locale.getDefault())));
		}
		model.addAttribute("errors", errors);
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		return "/adduser";
	}
}
