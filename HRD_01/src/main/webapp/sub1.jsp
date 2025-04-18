<%@page import="exam.MemberDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	MemberDAO dao = new MemberDAO();
	int custno = dao.getCustno();

	// 오늘날짜
	Date date = new Date();
	SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
	String today = formater.format(date);
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sub1</title>
		<script type="text/javascript" src="check.js"></script>
	</head>
	<body style="padding: 0; margin: 0">
		<jsp:include page="header.jsp" />
		<jsp:include page="nav.jsp" />
		<section>
			<div align="center">
				<h3>쇼핑몰 회원등록</h3>
				<form action="sub1Action.jsp" method="post" name="sub1Form">
					<table border="1">
						<tr>
							<th>회원번호(자동발생)</th>
							<td>
								<input type="number" name="custno" value="<%= custno %>" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>회원성명</th>
							<td>
								<input type="text" name="custname" />
							</td>
						</tr>
						<tr>
							<th>회원전화</th>
							<td>
								<input type="text" name="phone" />
							</td>
						</tr>
						<tr>
							<th>회원주소</th>
							<td>
								<input type="text" name="address" />
							</td>
						</tr>
						<tr>
							<th>가입일자</th>
							<td>
								<input type="text" name="joindate" value="<%= today %>" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>고객등급[A:VIP, B:일반, C:직원]</th>
							<td>
								<input type="text" name="grade" />
							</td>
						</tr>
						<tr>
							<th>도시코드</th>
							<td>
								<input type="text" name="city" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="button" value="등록" onclick="fnSub1FormCheck()" />
								<input type="button" value="조회" onclick="location.href='sub2.jsp'" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</section>
		<jsp:include page="footer.jsp" />
	</body>
</html>