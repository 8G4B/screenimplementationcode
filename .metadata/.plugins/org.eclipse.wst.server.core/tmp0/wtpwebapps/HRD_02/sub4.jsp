<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sub4</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="nav.jsp" />

	<section>
		<div align="center">
			<h3>과목별 성적 조회</h3>

			<form action="sub4Action.jsp" method="get" name="sub4Form">
				<table border="1">
					<tr>
						<th>과목코드</th>
						<td><input type="text" name="searchSubcode" /></td>
						<td><input type="button" value="조회"
							onclick="fnSub4FromCheck()" /></td>
					</tr>
				</table>
			</form>


			<table border="1">
				<tr>
					<th>학번</th>
					<th>성명</th>
					<th>학과</th>
					<th>과목</th>
					<th>중간</th>
					<th>기말</th>
					<th>출석</th>
					<th>레포트</th>
					<th>기타</th>
					<th>총점</th>
					<th>학점</th>
				</tr>
			</table>


		</div>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>