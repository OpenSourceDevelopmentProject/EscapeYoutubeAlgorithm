<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="scrapper.Main" %>
<%@ page import="scrapper.YoutubeDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	Main youtube = new Main();
	YoutubeDAO youtubeDAO = new YoutubeDAO();
	System.out.println(youtubeDAO.setData(youtube));
	//System.out.println(youtubeDAO.getData("레알예능 스브스"));
%>
</body>
</html>