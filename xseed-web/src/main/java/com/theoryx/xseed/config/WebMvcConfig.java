package com.theoryx.xseed.config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.theoryx.xseed.annotation.Link;
import com.theoryx.xseed.interceptor.BreadCrumbInterceptor;
import com.theoryx.xseed.interceptor.RegisterInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends /* WebMvcAutoConfigurationAdapter */ WebMvcConfigurerAdapter {
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };

	@Autowired
	private ListableBeanFactory listableBeanFactory;
	
	

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("locale/language");
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("ln");
		registry.addInterceptor(changeInterceptor);
		
		BreadCrumbInterceptor breadcrumbInterceptor = new BreadCrumbInterceptor();
		breadcrumbInterceptor.setLinks(getAllLinks());
		registry.addInterceptor(breadcrumbInterceptor);
		
		RegisterInterceptor reg = new RegisterInterceptor();
		registry.addInterceptor(reg).addPathPatterns("/register");
		
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}

	private HashMap<String, Link> getAllLinks() {
		Map<String, Object> controllers;
		HashMap<String, Link> links = new HashMap<String, Link>();
		controllers = listableBeanFactory.getBeansWithAnnotation(Controller.class);
		for (String controllerName : controllers.keySet()) {
			for (Method method : controllers.get(controllerName).getClass().getMethods()) {
				if (method.isAnnotationPresent(Link.class)) {
					Link link = method.getDeclaredAnnotation(Link.class);
					links.put(link.label(), link);
				}
			}
		}
		return links;
	}

}
