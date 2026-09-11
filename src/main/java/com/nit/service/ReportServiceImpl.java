package com.nit.service;

import java.io.File;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nit.entity.CitizenPlan;
import com.nit.repo.CitizenPlanRepository;
import com.nit.request.SearchRequest;
import com.nit.utils.EmailUtils;
import com.nit.utils.ExcelGenerator;
import com.nit.utils.PdfGenerator;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportServices {

	@Autowired
	private CitizenPlanRepository planRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public List<String> getPlanNames() {
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity=new CitizenPlan();
		if(null !=request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		
		if(null !=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		if(null !=request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		
		if(null != request.getStartDate() && !"".equals(request.getStartDate())) {
			entity.setPlanStartDate(request.getStartDate());
		}
		
		if(null != request.getEndDate() && !"".equals(request.getEndDate())) {
			entity.setPlanEndDate(request.getEndDate());
		}
		
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception{
		File f = new File("Plans.xls");
		
		List<CitizenPlan> plans = planRepo.findAll();
		excelGenerator.generate(response, plans, f);
		
		String subject="Test Mail Subject";
		String body="<h1>Test Mail body</h1>";
		String to="dikeshsir78@gmail.com";
		
		emailUtils.sendMail(subject, body, to, f);
		f.delete();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		
		File f = new File("Plans.pdf");
		
		List<CitizenPlan> plans = planRepo.findAll();
		pdfGenerator.generate(response, plans, f);
		String subject="Test Mail Subject";
		String body="<h1>Test Mail body</h1>";
		String to="dikeshsir78@gmail.com";
		
		emailUtils.sendMail(subject, body, to, f);
		f.delete();
		return true;
	}

}
























