package com.theoryx.xseed.utils;

public class NumberUtils {
	
	public static Double round(Double number) {
		Double roundedNumber = number * 1000;
		Long l = Math.round(roundedNumber);
		roundedNumber = (double)l / 1000;
		return roundedNumber;
	}
	
}
