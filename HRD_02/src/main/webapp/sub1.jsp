<%@page import="exam.StudentDTO"%>
<%@page import="java.util.List"%>
<%@ page import="exam.StudentDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
StudentDAO dao = new StudentDAO();
List<StudentDTO> list = dao.selectSub1();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sub1</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="nav.jsp" />
	<section>
		<div align="center">
			<h3>학생정보현황</h3>
			<table border="1" width="80%">
				<tr>
					<th>학번</th>
					<th>이름</th>
					<th>주민번호</th>
					<th>학과명</th>
					<th>성별</th>
					<th>전화번호</th>
					<th>이메일</th>
				</tr>
				
				<% for(StudentDTO dto : list) { %>
					<tr align="center">
						<td><%= dto.getStuid() %></td>
						<td><%= dto.getSname() %></td>
						<td><%= dto.getJumin() %></td>
						<td><%= dto.getDeptname() %></td>
						<td><%= dto.getGender() %></td>
						<td><%= dto.getPhone() %>
						<td><%= dto.getEmail() %></td>
					</tr>
				<% } %>
			</table>
		</div>
	</section>
	<jsp:include page="footer.jsp" />
</body>
</html>