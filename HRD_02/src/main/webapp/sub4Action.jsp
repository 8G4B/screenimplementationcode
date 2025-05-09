<%@page import="exam.ScoreDTO"%>
<%@page import="java.util.List"%>
<%@page import="exam.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String searchSubcode = request.getParameter("searchSubcode");

StudentDAO dao = new StudentDAO();
List<ScoreDTO> list = dao.selectSub4(searchSubcode);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sub4Action</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<jsp:include page="nav.jsp"/>
	<section>
		<div align="center">
			<h3>과목별 성적조회 결과</h3>
			<table border="1" width="80%" style="margin: 60px">
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
				<% if(list != null && list.size() > 0) {
					for(ScoreDTO dto : list) { %>
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
				<% } 
				} else { %>
					<tr>
						<td colspan="12">해당 과목코드에 대한 성적 정보가 없습니다.</td>
					</tr>
				<% } %>
			</table>
		</div>
	</section>
	<jsp:include page="footer.jsp"/>
</body>
</html>
