<%@page import="exam.TeacherDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
TeacherDAO dao = new TeacherDAO();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sub2</title>
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="nav.jsp"></jsp:include>
	
	<div align="center">
		<h2>수강신청</h2>
		<form action="sub2Action.jsp" method="post" name="Sub2Form">
		<table border="1" width="45%">
			<tr>
				<th>
					수강월
				</th>
				<td>
					<input name="수강월" type="text" style="width: 30%" />                예) 202203
				</td>
			</tr>
			<tr>
				<th>
					회원명
				</th>
				<td>
					<select name="회원명" style="width: 30%" onchange="updateMemberCode()">
					    <option value="" disabled selected hidden>회원검색</option>
					    <option value="홍길동" data-code="10001">홍길동</option>
					    <option value="정길성" data-code="10002">정길성</option>
					    <option value="임길성" data-code="10003">임길성</option>
					    <option value="성춘향" data-code="20001">성춘향</option>
					    <option value="이몽룡" data-code="20002">이몽룡</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					회원번호
				</th>
				<td>
					<input name="회원번호" type="text" style="width: 30%" readonly />
				</td>
			</tr>
			<tr>
				<th>
					강의장소
				</th>
				<td>
					<input type="radio" name="강의장소" value="서울본원" id="서울본원" />
					<label for="서울본원">서울본원</label>
					<input type="radio" name="강의장소" value="성남분원" id="성남분원" />
					<label for="성남분원">성남분원</label>
					<input type="radio" name="강의장소" value="대전분원" id="대전분원" />
					<label for="대전분원">대전분원</label>
					<input type="radio" name="강의장소" value="부산분원" id="부산분원" />
					<label for="부산분원">부산분원</label>
					<input type="radio" name="강의장소" value="대구분원" id="대구분원" />
					<label for="대구분원">대구분원</label>
				</td>
			</tr>
			<tr>
				<th>
					강의명
				</th>
				<td>
					<select name="강의명" style="width: 30%" onchange="updatePrice()">
					    <option value="" disabled selected hidden>강의신청</option>
					    <option value="초급반" data-price="100000" teacher-code="100">초급반</option>
					    <option value="중급반" data-price="200000" teacher-code="100">중급반</option>
					    <option value="고급반" data-price="300000" teacher-code="100">고급반</option>
					    <option value="심화반" data-price="400000" teacher-code="100">심화반</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					수강료
				</th>
				<td>
					<input name="수강료" type="text" style="width: 30%" />   원
				</td>
			</tr>
			<input name="강사코드" type="hidden" style="height: 0px" readonly />
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수강신청" onclick="fnFormChecking()" />
					<input type="button" value="다시쓰기" onclick="fnClear()" />
				<td>
			</tr>
		</table>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>