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
	dto.setCustname(request.getParameter("custname"));
	dto.setPhone(request.getParameter("phone"));
	dto.setAddress(request.getParameter("address"));
	dto.setJoindate(request.getParameter("joindate"));
	dto.setGrade(request.getParameter("grade"));
	dto.setCity(request.getParameter("city"));
	
	MemberDAO dao = new MemberDAO();
	dao.insertSub1(dto);
	
	out.println("<script>location.href='sub1.jsp';</script>");
%>