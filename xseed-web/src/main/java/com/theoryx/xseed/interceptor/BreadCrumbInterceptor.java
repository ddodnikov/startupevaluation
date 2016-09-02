package com.theoryx.xseed.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.theoryx.xseed.annotation.Link;
import com.theoryx.xseed.dto.UrlLabelMapping;

public class BreadCrumbInterceptor extends HandlerInterceptorAdapter {

	private HashMap<String, Link> links = new HashMap<String, Link>();

	public void setLinks(HashMap<String, Link> links) {
		if (links == null) {
			links = new HashMap<String, Link>();
		}
		this.links = links;
	}

	/**
	 * This method is executed before the execution of the controller when a
	 * request is sent.
	 * 
	 * @param request
	 * @param response
	 * @param hanler
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Annotation[] declaredAnnotations = getDeclaredAnnotationsForHandler(handler);
		for (Annotation annotation : declaredAnnotations) {
			if (annotation.annotationType().equals(Link.class)) {
				List<UrlLabelMapping> breadcrumbs = processAnnotation(request, annotation);
				request.setAttribute("breadCrumbs", breadcrumbs);
			}
		}
		return true;
	}

	/**
	 * This method gets the current link and generates the breadcrumbs.
	 * 
	 * @param request
	 * @param annotation
	 */
	private List<UrlLabelMapping> processAnnotation(HttpServletRequest request, Annotation annotation) {
		Link link = (Link) annotation;
		List<UrlLabelMapping> breadcrumbs = new LinkedList<UrlLabelMapping>();
		generateBreadCrumbs(link, breadcrumbs);
		return breadcrumbs;
	}

	/**
	 * This method returns the previous link. The link is found in the map of
	 * label-links
	 * 
	 * @param link
	 */
	private Link getPrevious(Link link) {
		return links.get(link.parent());
	}

	/**
	 * This method generates the bread crumbs for a specific link
	 * 
	 * @param link
	 * @param breadcrumbs
	 */
	private void generateBreadCrumbs(Link link, List<UrlLabelMapping> breadcrumbs) {
		if (link == null) {
			return;
		} else {
			if (!link.parent().equals("")) {
				generateBreadCrumbs(getPrevious(link), breadcrumbs);
			}
			breadcrumbs.add(new UrlLabelMapping(link.url(), link.label()));
		}
	}

	/**
	 * This method returns all annotations of the requested controller method
	 * 
	 * @param handler
	 */
	private Annotation[] getDeclaredAnnotationsForHandler(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
		return declaredAnnotations;
	}

}
