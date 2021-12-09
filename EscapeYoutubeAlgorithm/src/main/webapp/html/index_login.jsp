<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="youtube.YoutubeDAO" %> 
<%@ page import="youtube.User" %>
<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="og:title" content="Escape to Youtube Algorithm!"/>
    <link rel="stylesheet" href="../static/css/style.css">
    
    <title>Escape from Youtube Algorithm</title>
</head>
<body id="body-in">

<%
User user = new User(); 
PrintWriter script = response.getWriter();
if(user.getIsLogin() == false){
	script.println("<script>");
	script.println("location.href = './index_logout.jsp'");
	script.println("</script>");
}
%>

    <header id="header-in">
        <a class="header-logo-in" href="index_login.jsp">
            <img class="logo" src="../static/image/site_logo.png" alt="Escape logo">
            <h1 class="title-link-in">Escape from Youtube-Algorithm</h1>
        </a>
        <form form method= "post" action="searchAction.jsp" class="search-form-in js-form-in">
            <div class="search-box-in">
                <input type="text" name = "searchUrl" class="search-input-in">
            </div>
            <button type="submit" class="search-submit-in">
                <i class="fas fa-search fa-2x"></i>
            </button>
        </form>
    </header>
    <main id="main-in">
	    <% 
		YoutubeDAO youtubeDAO = new YoutubeDAO(); //인스턴스생성
		%>
        <i class="fas fa-chevron-left"></i>
        <div class="like-channel-in">
            <a href="video.jsp?tagNum=1&videoNum=1">
                <div class="one <%=youtubeDAO.checkExistTag(1)%>">
                    <div class="img-cover-in">
                        <img src="<%=youtubeDAO.getProfileImgUrl(1, user.getUserID())%>" alt="">
                    </div>
                    <div class="span-cover-in">
                        <span>    
						<%= youtubeDAO.getChanelName(1, user.getUserID()) %>
                        </span>
                    </div>
                </div>
            </a>
            <a href="video.jsp?tagNum=2&videoNum=1">
	            <div class="two <%=youtubeDAO.checkExistTag(2)%>">
	                <div class="img-cover-in">
	                    <img src=<%=youtubeDAO.getProfileImgUrl(2, user.getUserID())%> alt="">
	                </div>
	                <div class="span-cover-in">
							<span>    
							<%= youtubeDAO.getChanelName(2, user.getUserID()) %>
	                        </span>
	                </div>
	            </div>
	        </a>
	        
            <a href="video.jsp?tagNum=3&videoNum=1">
	            <div class="three <%=youtubeDAO.checkExistTag(3)%>">
	                <div class="img-cover-in">
	                    <img src=<%=youtubeDAO.getProfileImgUrl(3, user.getUserID())%> alt="">
	                </div>
	                <div class="span-cover-in">
							<span>    
							<%= youtubeDAO.getChanelName(3, user.getUserID()) %>
	                        </span>
	                </div>
	            </div>
	        </a>
            <a href="video.jsp?tagNum=4&videoNum=1">
	            <div class="four <%=youtubeDAO.checkExistTag(4)%>">
	                <div class="img-cover-in">
	                    <img src=<%=youtubeDAO.getProfileImgUrl(4, user.getUserID())%> alt="">
	                </div>
	                <div class="span-cover-in">
							<span>    
							<%= youtubeDAO.getChanelName(4, user.getUserID()) %>
	                        </span>
	                </div>
	            </div>
	        </a>
           <a href="video.jsp?tagNum=5&videoNum=1">
	            <div class="five <%=youtubeDAO.checkExistTag(5)%>">
	                <div class="img-cover-in">
	                    <img src=<%=youtubeDAO.getProfileImgUrl(5, user.getUserID())%> alt="">
	                </div>
	                <div class="span-cover-in">
							<span>    
							<%= youtubeDAO.getChanelName(5, user.getUserID()) %>
	                        </span>
	                </div>
	            </div>
	        </a>
            <a href="video.jsp?tagNum=6&videoNum=1">
	            <div class="six <%=youtubeDAO.checkExistTag(6)%>">
	                <div class="img-cover-in">
	                    <img src=<%=youtubeDAO.getProfileImgUrl(6, user.getUserID())%> alt="">
	                </div>
	                <div class="span-cover-in">
							<span>    
							<%= youtubeDAO.getChanelName(6, user.getUserID()) %>
	                        </span>
	                </div>
	            </div>
	        </a>
           <a href="video.jsp?tagNum=7&videoNum=1">
	            <div class="seven <%=youtubeDAO.checkExistTag(7)%>">
	                <div class="img-cover-in">
	                    <img src=<%=youtubeDAO.getProfileImgUrl(7, user.getUserID())%> alt="">
	                </div>
	                <div class="span-cover-in">
							<span>    
							<%= youtubeDAO.getChanelName(7, user.getUserID()) %>
	                        </span>
	                </div>
	            </div>
	        </a>
           <a href="video.jsp?tagNum=8&videoNum=1">
	            <div class="eight <%=youtubeDAO.checkExistTag(8)%>">
	                <div class="img-cover-in">
	                    <img src=<%=youtubeDAO.getProfileImgUrl(8, user.getUserID())%> alt="">
	                </div>
	                <div class="span-cover-in">
							<span>    
							<%= youtubeDAO.getChanelName(8, user.getUserID()) %>
	                        </span>
	                </div>
	            </div>
	        </a>
            <a href="video.jsp?tagNum=9&videoNum=1">
	            <div class="nine <%=youtubeDAO.checkExistTag(9)%>">
	                <div class="img-cover-in">
	                    <img src=<%=youtubeDAO.getProfileImgUrl(9, user.getUserID())%> alt="">
	                </div>
	                <div class="span-cover-in">
							<span>    
							<%= youtubeDAO.getChanelName(9, user.getUserID()) %>
	                        </span>
	                </div>
	            </div>
	        </a>
            <a href="video.jsp?tagNum=10&videoNum=1">
	            <div class="ten <%=youtubeDAO.checkExistTag(10)%>">
	                <div class="img-cover-in">
	                    <img src=<%=youtubeDAO.getProfileImgUrl(10, user.getUserID())%> alt="">
	                </div>
	                <div class="span-cover-in">
							<span>    
							<%= youtubeDAO.getChanelName(10, user.getUserID()) %>
	                        </span>
	                </div>
	            </div>
	        </a>
        </div>
        <i class="fas fa-chevron-right"></i>
    </main>
    <nav id="side-bar-in">
        <div class="top-sidebar-in">
    
        </div>
        <div class="main-sidebar-in">
            <a href="index_login.jsp">
                <div class="home-button">
                    <h2>Home</h2>
                </div>
            </a>
        </div>
        <div class="bottom-sidebar-in">
            <h1 class="greeting-sidebar-in">Hi, <%=user.getUserName()%></h1>
            <a class="hidden" href="login.jsp">
                <div class="sidebar-button-in">
                    <span>Log in</span>
                </div>
            </a>
            <a class="hidden" href="signup.jsp">
                <div class="sidebar-button-in">
                    <span>Sign up</span>
                </div>
            </a>
        </div>
    </nav>
    <script src="../static/js/index_login.js"></script>
    <script src="https://kit.fontawesome.com/6478f529f2.js" crossorigin="anonymous"></script>
</body>
</html>