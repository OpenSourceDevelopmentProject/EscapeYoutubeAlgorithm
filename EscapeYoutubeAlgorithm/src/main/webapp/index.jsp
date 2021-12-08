<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="youtube.User" %>
<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
<%
User user = new User(); 
PrintWriter script = response.getWriter();
if(user.getIsLogin() == true){
	script.println("<script>");
	script.println("location.href = './html/index_login.jsp'");
	script.println("</script>");
}
else{
	script.println("<script>");
	script.println("location.href = './html/index_logout.jsp'");
	script.println("</script>");
}
%>
</body>
</html>