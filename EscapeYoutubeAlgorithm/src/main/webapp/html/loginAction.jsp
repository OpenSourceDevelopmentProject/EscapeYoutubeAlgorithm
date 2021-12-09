<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="youtube.UserDAO" %>
<%@ page import="youtube.YoutubeDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="user" class="youtube.User" scope="page"/>
<jsp:setProperty name="user" property="userID"/>
<jsp:setProperty name="user" property="userPassword"/>

<!DOCTYPE html> 

<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		UserDAO userDAO = new UserDAO();
		YoutubeDAO youtubeDAO = new YoutubeDAO();
		int result = userDAO.login(user.getUserID(), user.getUserPassword()); //login함수에 아이디와 패스워드를 넣어서 실행
		//-2에서 1까지
		if(result == 1){
			user.setIsLogin(true);
			user.setUserName(userDAO.getUserNameFromDB(user.getUserID()));
			youtubeDAO.updateNewVideoUrls(user.getUserID());
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'index_login.jsp'");
			script.println("</script>");
		}
		else if(result == 0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if(result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if(result == -2){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
	%>
</body>
</html>