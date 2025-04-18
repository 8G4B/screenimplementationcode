<%@page import="java.text.DecimalFormat"%>
<%@page import="exam.TotalDTO"%>
<%@page import="java.util.List"%>
<%@page import="exam.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	MemberDAO dao = new MemberDAO();
	List<TotalDTO> list = dao.selectSub3();
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sub3</title>
	</head>
	<body style="padding: 0; margin: 0">
		<jsp:include page="header.jsp" />
		<jsp:include page="nav.jsp" />
		<section>
			<div align="center">
				<h3>회원매출조회</h3>
				<table border="1" width="70%">
					<tr>
						<th>회원번호</th>
						<th>회원성명</th>
						<th>고객등급</th>
						<th>매출</th>
					</tr>
					<% for(TotalDTO dto: list) { %>
						<tr align="center">
							<td><%= dto.getCustno() %></td>
							<td><%= dto.getCustname() %></td>
							<td><%= dto.getGrade() %></td>
							
							<% 
								int total = dto.getTotal();
								DecimalFormat df = new DecimalFormat("￦#, ##0");	
							%>
							
							<td><%= df.format(total) %></td>
						</tr>
					<% } %>
				</table>
			</div>
		</section>
		<jsp:include page="footer.jsp" />
	</body>
</html>