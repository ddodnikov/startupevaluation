package com.theoryx.xseed.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.theoryx.xseed.annotation.Link;
import com.theoryx.xseed.dto.ReportRow;
import com.theoryx.xseed.dto.StartupDTO;
import com.theoryx.xseed.model.Error;
import com.theoryx.xseed.service.ReportsService;

@Controller
public class ReportsController {
	
	@Autowired
	private ReportsService reportsService;
	@Autowired
	private MessageSource messageSource;

	
	@Link(label = "Reports", parent = "Home", url = "/reports")
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String getAllReports(Model model, HttpServletRequest request) {
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		return "/reports";
	}
	
	@Link(label = "Progress report", parent = "Reports", url = "/progressreport")
	@RequestMapping(value = "/progressreport", method = RequestMethod.GET)
	public String getReport(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		StartupDTO startupDTO = (StartupDTO) session.getAttribute("currentStartup");
		LinkedHashMap<String, LinkedHashMap<String, LinkedList<ReportRow>>> rows = reportsService.getReportRows(startupDTO.getId());
		if(rows!=null){
			HashMap<String, Integer> phaseRows = new HashMap<String, Integer>();
			
			//calculates the number of snapshots
			int numberOfSnapshots = 0;
			for(String phase: rows.keySet()){
				for(String group:rows.get(phase).keySet()){
					numberOfSnapshots = rows.get(phase).get(group).get(0).getAnswers().size();
					break;
				}
			}
			
			//calculates how many rows have to be merged for each phase category
			for(String phaseCategory:rows.keySet()){
				int numberOfRows = 0;
				for(LinkedList<ReportRow> reports : rows.get(phaseCategory).values()){
					numberOfRows += reports.size(); 
				}
				phaseRows.put(phaseCategory, numberOfRows);
			}
			model.addAttribute("data", rows);
			model.addAttribute("months", numberOfSnapshots);
			model.addAttribute("phaseRows", phaseRows);
		}else{
			List<Error> errors = new LinkedList<Error>();
			errors.add(new Error(messageSource.getMessage("error.reports-error", new Object[0], Locale.getDefault())));
			model.addAttribute("errors", errors);
		}
		model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
		return "/report-details";
	}
}
