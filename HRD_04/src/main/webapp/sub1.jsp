<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="exam.TeacherDAO"%>
<%@page import="exam.TeacherDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
TeacherDAO dao = new TeacherDAO();
List<TeacherDTO> list = dao.sub1Action();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sub1</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="nav.jsp"></jsp:include>
	
	<div align="center">
		<h2>강사조회</h2>
		<table border="1" width="80%">
		<tr>
			<th>강사코드</th>
			<th>강사명</th>
			<th>강의명</th>
			<th>수강료</th>
			<th>강사자격취득일</th>
		</tr>
			<% for(TeacherDTO dto : list) {%>
				<tr align="center">
					<td><%= dto.getTeacherCode() %></td>
					<td><%= dto.getTeacherName() %></td>
					<td><%= dto.getClassName() %></td>
					<%
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
					%>
					<td><%= currencyFormatter.format(dto.getTeacingPrice()) %></td>
					<%
					    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
					    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
					    String rawDate = dto.getLincesDate();
					    LocalDate localDate = LocalDate.parse(rawDate, inputFormatter);
					    String formattedDate = localDate.format(outputFormatter);
					%>
					<td><%= formattedDate %></td>
				</tr>
			<% } %>
	</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>