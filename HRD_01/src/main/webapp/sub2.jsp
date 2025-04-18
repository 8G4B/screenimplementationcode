<%@page import="exam.MemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="exam.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	MemberDAO dao = new MemberDAO();
	List<MemberDTO> list = dao.selectSub2();
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sub2</title>
	</head>
	<body style="padding: 0; margin: 0">
		<jsp:include page="header.jsp" />
		<jsp:include page="nav.jsp" />
		<section>
			<div align="center">
				<h3>회원목록조회/수정</h3>
				<table border="1">
					<tr>
						<th>회원번호</th>
						<th>회원성명</th>
						<th>전화번호</th>
						<th>주소</th>
						<th>가입일자</th>
						<th>고객등급</th>
						<th>거주지역</th>
					</tr>
					<% for(MemberDTO dto: list) { %>
						<tr align="center">
							<td><%= dto.getCustno() %></td>
							<td><%= dto.getCustname() %></td>
							<td><%= dto.getPhone() %></td>
							<td><%= dto.getAddress() %></td>
							<td><%= dto.getJoindate() %></td>
							<td><%= dto.getGrade() %></td>
							<td><%= dto.getCity() %></td>
						</tr>
					<% } %>
				</table>
			</div>
		</section>
		<jsp:include page="footer.jsp" />
	</body>
</html>
