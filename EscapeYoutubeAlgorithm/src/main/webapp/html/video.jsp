<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    
    <title>Escape to Youtube Algorithm</title>
</head>
<body id="body-vi">
    <main id="main-vi">
    <%  
    int tagNum = Integer.parseInt(request.getParameter("tagNum"));
    int videoNum = Integer.parseInt(request.getParameter("videoNum"));
    YoutubeDAO dao = new YoutubeDAO(); //인스턴스생성
    User user = new User();
    
    PrintWriter script = response.getWriter();
    if(videoNum<1)
    {
    	script.println("<script>");
    	script.println("alert('가장 최근 영상입니다')");
		script.println("</script>");
		videoNum = 1;
    }
    if(videoNum>5)
    {
    	script.println("<script>");
    	script.println("alert('마지막 영상입니다')");
		script.println("</script>");
		videoNum = 5;
    }
    String newVideoUrl = dao.getNewVideoUrl(tagNum, user.getUserID(), videoNum);
    if(newVideoUrl.equals("-5"))
    {
    	script.println("<script>");
    	script.println("alert('마지막 영상입니다')");
		script.println("</script>");
		videoNum -= 1 ;
		newVideoUrl = dao.getNewVideoUrl(tagNum, user.getUserID(), videoNum);
    }
    
    %>
    
        <div class="video-information-vi">
            <div class="video-container-vi">
                <div class="cover-vi"></div>
                <iframe width="896" height="504" src=<%=newVideoUrl%> title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            </div>
            <div class="bottom-bar-vi">
                <div class="tag-container-vi">
                    <span class="js-span-vi">영상 태그 보기</span>
                    <ul class="js-list-vi">
                        <li></li>
                    </ul>
                </div>
                <div class="link-container-vi">
                    <a href="video.jsp?tagNum=<%=tagNum%>&videoNum=<%=videoNum-1%>">
                        <div class="button-vi">
                            <i class="fas fa-chevron-left"></i>
                            <span>이전 영상</span>
                        </div>
                    </a>
                    <a href="video.jsp?tagNum=<%=tagNum%>&videoNum=<%=videoNum+1%>">
                        <div class="button-vi">
                            <span>다음 영상</span>
                            <i class="fas fa-chevron-right"></i>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <!--<iframe width="560" height="315" src="https://www.youtube.com/embed/OEKv5nBmbTE" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>-->
    </main>
    <footer id="footer-vi">

    </footer>
    <root id="root-vi">

    </root>
    <nav id="side-bar-vi">
        <div class="top-sidebar-vi">
    
        </div>
        <div class="main-sidebar-vi">
            <a href="index_login.jsp">
                <div class="home-button">
                    <h2>Home</h2>
                </div>
            </a>
        </div>
        <%
        String greeting = null;
        String loginAndsignUp = null;
        if(user.getIsLogin()){ 
        	greeting = "";
        	loginAndsignUp = "hidden";
        }
        else{
        	greeting = "hidden";
        	loginAndsignUp = "";
        }
        %>
        <div class="bottom-sidebar-vi">
            <h1 class="greeting-sidebar-vi <%=greeting%>">Hi, <%=user.getUserName()%></h1>
            <a href="login.jsp" class = "<%=loginAndsignUp%>">
                <div class="sidebar-button-vi">
                    <span>Log in</span>
                </div>
            </a>
            <a href="signup.jsp" class = "<%=loginAndsignUp%>">
                <div class="sidebar-button-vi">
                    <span>Sign up</span>
                </div>
            </a>
        </div>
    </nav>
    <script src="../static/js/video.js"></script>
    <script src="https://kit.fontawesome.com/6478f529f2.js" crossorigin="anonymous"></script>
</body>
</html>