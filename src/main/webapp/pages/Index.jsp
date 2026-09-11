<%@page import="jakarta.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Reports App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body>
	<div class="container">
	<h3 class="pb-3 pt-3">Reports Application</h3>
	
	<form:form action="search" modelAttribute="search" method="POST">
		<table>
			<tr>
				<td>Plan Name:</td>
				<td>
					<form:select path="planName">
					<form:option value="">--Select--</form:option>
					<form:options items="${names}"/>
					</form:select>
				</td>
				<td>Plan Status:</td>
				<td>
					<form:select path="planStatus">
						<form:option value="">--Select--</form:option>
						<form:options items="${status}"/>
					</form:select>
				</td>
				<td>Gender:</td>
				<td>
					<form:select path="gender">
						<form:option value="">--Select--</form:option>
						<form:option value="Male">Male</form:option>
						<form:option value="Female">Female</form:option>
					</form:select>
				</td>
			</tr>
			
			<tr>
				<td>Start Date:</td>
				<td>
					<form:input type="date" path="startDate"/>
				</td>
				<td>End Date:</td>
				<td>
					<form:input type="date" path="endDate"/>
				</td>				
			</tr>
			
			<tr>
				<td>
					<a href="/" class="btn btn-secondary">Reset</a>
				</td>
				<td>
					<input type="submit" value="Search" class="btn btn-primary"/>
				</td>			
			</tr>
			
		</table>
	</form:form>
	<hr/>
	
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>S.No</th>
				<th>Holder Name</th>
				<th>Gender</th>
				<th>Plan Name</th>
				<th>Plan Status</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Benefit Amt</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${plans}" var="plan" varStatus="Index">
				<tr>
					<td>${plan.citizenId}</td>
					<td>${plan.citizenName}</td>
					<td>${plan.gender}</td>
					<td>${plan.planName}</td>
					<td>${plan.planStatus}</td>
					<td>${plan.planStartDate}</td>
					<td>${plan.planEndDate}</td>
					<td>${plan.benefitAmt}</td>
				</tr>
			</c:forEach>
			<tr>
				<c:if test="${empty plans}">
					<td colspan="8" style="text-align:  center">No Records Found</td>
				</c:if>			
			</tr>
		</tbody>
	</table>
	
	<hr>
		
		Export: <a href="excel">Excel </a> <a href="pdf"> Pdf</a>
	</div>
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>

</body>
</html>






















