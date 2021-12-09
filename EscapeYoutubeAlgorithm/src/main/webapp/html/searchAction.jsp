<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="youtube.YoutubeDAO" %> 
<%@ page import="youtube.User" %>
<%@ page import="youtube.YoutubeCrawler" %>  
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
String searchUrl = request.getParameter("searchUrl");
YoutubeDAO youtubeDAO = new YoutubeDAO();
User user = new User();
YoutubeCrawler youtubeCrawler = new YoutubeCrawler(searchUrl); 
youtubeDAO.setYoutube_db(youtubeCrawler);
youtubeDAO.setSubscribe_db(user.getUserID(), youtubeCrawler);

//원래 페이지로 돌아가기
PrintWriter script = response.getWriter();
script.println("<script>");
script.println("location.href = './index_login.jsp'");
script.println("</script>");
%>
</body>
</html>
