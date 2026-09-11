package com.nit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nit.entity.CitizenPlan;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {
	public void generate(HttpServletResponse response, List<CitizenPlan> plans, File f) throws Exception {
		Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		
		
		//Creating font and setting font style and size
		Font fontTitle=FontFactory.getFont(FontFactory.TIMES_ROMAN);		
		fontTitle.setSize(20);
		
		Paragraph p=new Paragraph("Citizen Plan", fontTitle);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
		
		PdfPTable table=new PdfPTable(8);
		table.setSpacingBefore(7);
		table.addCell("ID");
		table.addCell("CitizenName");
		table.addCell("Gender");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Plan Start Date");
		table.addCell("Plan End Date");
		table.addCell("Plan Benefit Amt");
		
		for(CitizenPlan plan: plans) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			if(null != plan.getPlanStartDate()) {
				table.addCell(String.valueOf(plan.getPlanStartDate()));
			}else {
				table.addCell("N/A");
			}
			
			if(null != plan.getPlanStartDate()) {
				table.addCell(String.valueOf(plan.getPlanEndDate()));
			}else {
				table.addCell("N/A");
			}
			
			if(null != plan.getPlanStartDate()) {
				table.addCell(String.valueOf(plan.getBenefitAmt()));
			}else {
				table.addCell("N/A");
			}
			
			
			
		}
		
		document.add(table);
		document.close();
	}
}




