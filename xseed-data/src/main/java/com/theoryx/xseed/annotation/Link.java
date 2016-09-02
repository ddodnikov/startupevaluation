package com.theoryx.xseed.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Link {

	public String label();
	public String url();
	public String parent();
	
}