<%@page import="java.text.DecimalFormat"%>
<%@page import="exam.ScoreDTO"%>
<%@page import="java.util.List"%>
<%@page import="exam.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
StudentDAO dao = new StudentDAO();
List<ScoreDTO> list = dao.selectSub3();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sub3</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="nav.jsp" />

	<section>
		<div align="center">
			<h3>성적현황</h3>

			<table border='1' width = 80%>
				<tr>
					<th>학번</th>
					<th>성명</th>
					<th>교과목</th>
					<th>교과목코드</th>
					<th>담당교수</th>
					<th>중간</th>
					<th>기말</th>
					<th>출석</th>
					<th>레포트</th>
					<th>기타</th>
					<th>평균</th>
					<th>총점</th>
					<th>학점</th>
				</tr>
				
				<% for(ScoreDTO dto: list) {%>
					<tr align = 'center'>
						<td><%= dto.getStuid() %></td>
						<td><%= dto.getSname() %></td>
						<td><%= dto.getSubname() %></td>
						<td><%= dto.getSubcode() %></td>
						<td><%= dto.getProname() %></td>
						<td><%= dto.getMidscore() %></td>
						<td><%= dto.getFinalscore() %></td>
						<td><%= dto.getAttend() %></td>
						<td><%= dto.getReport() %></td>
						<td><%= dto.getEtc() %></td>
						<td><%= dto.getAvg() %></td>
						
						<%
							double total = dto.getTotal();
							DecimalFormat df = new DecimalFormat("0.0");
						%>
						
						<td><%= df.format(total) %></td>
						<td><%= dto.getGrade() %></td>
					</tr>
					
				<% } %>
				
			</table>
		</div>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>