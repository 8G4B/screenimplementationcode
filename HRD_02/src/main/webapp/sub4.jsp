<%@page import="exam.ScoreDTO"%>
<%@page import="java.util.List"%>
<%@page import="exam.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

String searchSubcode = request.getParameter("searchSubcode");
List<ScoreDTO> list = null;

if (searchSubcode != null && !searchSubcode.trim().isEmpty()) {
    StudentDAO dao = new StudentDAO();
    list = dao.selectSub4(searchSubcode);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sub4</title>
<script type="text/javascript" src="check.js"></script>
<style>
	th, td {
		text-align: center;
		padding: 5px;
	}
</style>
</head>
<body style="padding: 0; margin: 0">
	<jsp:include page="header.jsp"/>
	<jsp:include page="nav.jsp"/>
	<section>
		<div align="center">
			<h3>과목별 성적조회</h3>
			<form action="sub4.jsp" method="post" name="sub4Form">
				<table border="1">
					<tr>
						<th>과목코드</th>
						<td><input type="text" name="searchSubcode" value="<%= searchSubcode != null ? searchSubcode : "" %>" /></td>
						<td><input type="submit" value="조회" /></td>
					</tr>
				</table>
			</form>

			<% if (list != null && !list.isEmpty()) { %>
			<table border="1" width="80%" style="margin: 60px auto;">
				<tr>
					<th>학번</th>
					<th>성명</th>
					<th>과목</th>
					<th>과목코드</th>
					<th>교수명</th>
					<th>중간</th>
					<th>기말</th>
					<th>출석</th>
					<th>레포트</th>
					<th>기타</th>
					<th>총점</th>
					<th>학점</th>
				</tr>
				<% for (ScoreDTO dto : list) { %>
				<tr>
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
					<td><%= String.format("%.1f", dto.getTotal()) %></td>
					<td><%= dto.getGrade() %></td>
				</tr>
				<% } %>
			</table>
			<% } %>
		</div>
	</section>
	<jsp:include page="footer.jsp"/>
</body>
</html>
