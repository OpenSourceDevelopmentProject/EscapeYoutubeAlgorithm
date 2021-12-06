<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="scrapper.YoutubeDAO" %> <!-- userdao의 클래스 가져옴 -->

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

    <header id="header-in">
        <a class="header-logo-in" href="index.jsp">
            <img class="logo" src="../static/image/site_logo.png" alt="Escape logo">
            <h1 class="title-link-in">Escape from Youtube-Algorithm</h1>
        </a>
        <form class="search-form-in js-form-in" action="get">
            <div class="search-box-in">
                <input type="text" class="search-input-in">
            </div>
            <button type="submit" class="search-submit-in">
                <i class="fas fa-search fa-2x"></i>
            </button>
        </form>
    </header>
    <main id="main-in">
	    <% 
		YoutubeDAO dao = new YoutubeDAO(); //인스턴스생성
		%>
        <i class="fas fa-chevron-left"></i>
        <div class="like-channel-in">
            <a href="video.jsp?tagNum=1">
                <div class="one <%=dao.checkExistTag(1)%>">
                    <div class="img-cover-in">
                        <img src="<%=dao.getProfileImgUrl(1)%>" alt="">
                    </div>
                    <div class="span-cover-in">
                        <span>    
						<%= dao.getChanelName(1) %>
                        </span>
                    </div>
                </div>
            </a>
            <a href="video.jsp?tagNum=1">
	            <div class="two <%=dao.checkExistTag(2)%>">
	                <div class="img-cover-in">
	                    <img src=<%=dao.getProfileImgUrl(2)%> alt="">
	                </div>
	                <div class="span-cover-in">
							<span>    
							<%= dao.getChanelName(2) %>
	                        </span>
	                </div>
	            </div>
	        </a>
            <div class="three <%=dao.checkExistTag(3)%>">
                <div class="img-cover-in">
                    <img src="#" alt="">
                </div>
                <div class="span-cover-in">

                </div>
            </div>
            <div class="four <%=dao.checkExistTag(4)%>">
                <div class="img-cover-in">
                    <img src="#" alt="">
                </div>
                <div class="span-cover-in">

                </div>
            </div>
            <div class="five <%=dao.checkExistTag(5)%>">
                <div class="img-cover-in">
                    <img src="#" alt="">
                </div>
                <div class="span-cover-in">

                </div>
            </div>
            <div class="six <%=dao.checkExistTag(6)%>">
                <div class="img-cover-in">
                    <img src="#" alt="">
                </div>
                <div class="span-cover-in">

                </div>
            </div>
            <div class="seven <%=dao.checkExistTag(7)%>">
                <div class="img-cover-in">
                    <img src="#" alt="">
                </div>
                <div class="span-cover-in">

                </div>
            </div>
            <div class="eight <%=dao.checkExistTag(8)%>">
                <div class="img-cover-in">
                    <img src="#" alt="">
                </div>
                <div class="span-cover-in">

                </div>
            </div>
            <div class="nine <%=dao.checkExistTag(9)%>">
                <div class="img-cover-in">
                    <img src="#" alt="">
                </div>
                <div class="span-cover-in">

                </div>
            </div>
            <div class="ten <%=dao.checkExistTag(10)%>">
                <div class="img-cover-in">
                    <img src="#" alt="">
                </div>
                <div class="span-cover-in">

                </div>
            </div>
        </div>
        <i class="fas fa-chevron-right"></i>
    </main>
    <nav id="side-bar-in">
        <div class="top-sidebar-in">
    
        </div>
        <div class="main-sidebar-in">
            <a href="index.jsp">
                <div class="home-button">
                    <h2>Home</h2>
                </div>
            </a>
        </div>
        <div class="bottom-sidebar-in">
            <h1 class="greeting-sidebar-in">Hi, 이주야</h1>
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
    <script src="../static/js/index.js"></script>
    <script src="https://kit.fontawesome.com/6478f529f2.js" crossorigin="anonymous"></script>
</body>
</html>