package com.nit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nit.entity.CitizenPlan;
import com.nit.request.SearchRequest;
import com.nit.service.ReportServices;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {

	@Autowired
	private ReportServices service;
	
	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse response, Model model) throws Exception {
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=plan.pdf");
		service.exportPdf(response);
		
	}
	
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response, Model model) throws Exception {
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename=plan.xls");
		service.exportExcel(response);
		
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest search, Model model) {

		List<CitizenPlan> plans = service.search(search);
		model.addAttribute("plans", plans);
		init(model);
		return "Index";
	}
	
	@GetMapping("/")
	public String indexPage(Model model) {
		SearchRequest searchObj=new SearchRequest();
		model.addAttribute("search", searchObj);
		init(model);
		return "Index";
	}

	private void init(Model model) {
		
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatus());
	}
}










