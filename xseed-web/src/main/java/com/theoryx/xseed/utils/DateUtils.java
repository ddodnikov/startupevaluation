package com.theoryx.xseed.utils;

import java.time.LocalDate;

public class DateUtils {
	
	private static LocalDate date = LocalDate.now();
	
	public static String getCurrentDateAsName(){
		String monthUpperCase = date.getMonth().toString();
		String month = monthUpperCase.replace(monthUpperCase.substring(1, monthUpperCase.length()), monthUpperCase.substring(1, monthUpperCase.length()).toLowerCase());
		String snapshotName = month + "_" + date.toString();
		return snapshotName;
	}
	
	public static String getCurrentDateDefaultName(){
		String name = date.toString();
		return name;
	}

}
