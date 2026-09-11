package com.nit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nit.entity.CitizenPlan;
import com.nit.repo.CitizenPlanRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {
	
	public void generate(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception{
		Workbook workbook=new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Plan-Data");
		Row headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("CitizenName");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("Plan Start Date");
		headerRow.createCell(6).setCellValue("Plan End Date");
		headerRow.createCell(7).setCellValue("Plan Benefit Amt");
		
		int dataRowIndex=1;
		
		for(CitizenPlan plan : records) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());
			
			if(null != plan.getPlanStartDate()) {
				dataRow.createCell(5).setCellValue(plan.getPlanStartDate()+"");
			}else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			
			if(null !=plan.getPlanEndDate()) {
				dataRow.createCell(6).setCellValue(plan.getPlanEndDate()+"");
			}else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			
			if(null != plan.getBenefitAmt()) {
				dataRow.createCell(7).setCellValue(plan.getBenefitAmt());
			}else {
				dataRow.createCell(7).setCellValue("N/A");
			}
			
			
			dataRowIndex++;
		}
		FileOutputStream fos=new FileOutputStream(file);//create file in same folder
		workbook.write(fos);
		fos.close();
		
		ServletOutputStream outputStream = response.getOutputStream();//send the file to browser
		workbook.write(outputStream);
		workbook.close();
		
	}
}



















