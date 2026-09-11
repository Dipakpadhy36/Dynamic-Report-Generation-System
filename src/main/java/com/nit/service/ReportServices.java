package com.nit.service;

import java.util.List;

import com.nit.entity.CitizenPlan;
import com.nit.request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportServices {
	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;
	
}











