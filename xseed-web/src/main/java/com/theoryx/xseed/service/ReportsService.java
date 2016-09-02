package com.theoryx.xseed.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.theoryx.xseed.dto.ReportRow;

public interface ReportsService {

	//List<Question> getAllQuestions();
	
	LinkedHashMap<String, LinkedHashMap<String, LinkedList<ReportRow>>> getReportRows(Integer startUpID);
}
