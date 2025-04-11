<%@page import="exam.MemberDTO"%>
<%@page import="exam.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
MemberDAO dao = new MemberDAO();
dao.getConn();
dao.dbClose();
--%>

<%
request.setCharacterEncoding("utf-8");
MemberDTO dto = new MemberDTO();
dto.setCustno(Integer.parseInt(request.getParameter("custno")));
dto.setCUSTNAME(request.getParameter("custname"));
dto.setPHONE(request.getParameter("phone"));
dto.setADDRESS(request.getParameter("address"));
dto.setJOINDATE(request.getParameter("joindate"));
dto.setGRADE(request.getParameter("grade"));
dto.setCITY(request.getParameter("city"));

MemberDAO dao = new MemberDAO(); 
dao.insertSub1(dto);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>