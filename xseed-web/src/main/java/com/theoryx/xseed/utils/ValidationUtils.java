package com.theoryx.xseed.utils;

public class ValidationUtils {
	
	private static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9\\.]+@[a-zA-Z0-9_\\.]+\\.[a-zA-Z]{2,6}$";
	private static final String WEBSITE_VALIDATION_REGEX = "^(http://|https://)?(www\\.)?([a-zA-Z0-9]+)\\.[a-zA-Z0-9]*\\.[a-z]{3}.?([a-z]+)?$";
	
	public static String validateSurveyName(String name){
		if(name == null || name.equals("")){
			name = DateUtils.getCurrentDateAsName();
		}
		return name;
	}
	
	public static String validateCalculationName(String name){
		if(name == null || name.equals("")) {
			name = DateUtils.getCurrentDateDefaultName();
		}
		return name;
	}
	
	public static boolean isValidStartupEmail(String email) {
		if (email.equals("")) {
			return true;
		} else {
			if (email.matches(EMAIL_VALIDATION_REGEX)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static boolean isValidStartupName(String name) {
		if (name.length() >= 3 && name.length() <= 50) {
			return true;
		}
		return false;
	}

	public static boolean isValidStartupPhone(String phone) {
		if (phone.equals("")) {
			return true;
		} else {
			if (phone.length() >= 4 && phone.length() <= 16) {
				return true;
			} else {
				return false;
			}
		}

	}

	public static boolean isValidStartupWebsite(String website) {
		if (website.equals("")) {
			return true;
		} else {
			if (website.matches(WEBSITE_VALIDATION_REGEX)) {
				return true;
			} else {
				return false;
			}
		}

	}

	public static boolean isValidStartupVAT(String vat) {
		if (vat.equals("")) {
			return true;
		} else {
			if (vat.length() >= 2 && vat.length() <= 16) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static boolean validateUserEmail(String email) {
		if (email == null) {
			return false;
		} else {
			email = email.trim();
			if (email.equals("") || !email.matches(EMAIL_VALIDATION_REGEX)) {
				return false;
			} else {
				return true;
			}
		}
	}

	public static boolean isValidUserName(String name) {
		if (name == null) {
			return false;
		} else {
			name = name.trim();
			if (name.length() >= 3 && name.length() <= 50) {
				return true;
			}
			return false;
		}
	}

	public static boolean isValidUserPassword(String password, String confirmationPassword) {
		if (password == null || confirmationPassword == null) {
			return false;
		} else {
			password = password.trim();
			confirmationPassword = confirmationPassword.trim();
			if (password.equals(confirmationPassword) && password.length() >= 4 && password.length() <= 20) {
				return true;
			}
			return false;
		}
	}

}
