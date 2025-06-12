<%@page import="exam.ClassDAO"%>
<%@page import="exam.ClassDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	ClassDTO dto = new ClassDTO();
	dto.set강사코드(request.getParameter("강사코드"));
	dto.set강의장소(request.getParameter("강의장소"));
	dto.set수강료(request.getParameter("수강료"));
	dto.set수강월(request.getParameter("수강월"));
	dto.set회원번호(request.getParameter("회원번호"));
	
	ClassDAO dao = new ClassDAO();
	dao.sub2Action(dto);
	out.println("<script>alert('수강신청이 정상적으로 완료되었습니다!');location.href='index.jsp';</script>");
%>