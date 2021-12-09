<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="og:title" content="Escape to Youtube Algorithm!"/>
    
    <link rel="stylesheet" href="../static/css/style.css"">
    
    <title>sign up - Escape to Youtube Algorithm</title>
</head>
<body id="body-si">
    <header id="header-si">
        <a href="index_login.jsp" class="title-si">Escape to Youtube Algorithm</a>
        <h1 class="info-si">Sign up</h1>
    </header>

    <main id="main-si">
        <form method = "post" action="signupAction.jsp" class="login-form-si">
            <input type="text" class="name-si input-si" placeholder="Please input your name" name = "userName" maxlength="20" required>
            <input type="text" class="id-si input-si" placeholder="Id" name = "userID" maxlength="20" required>
            <input type="password" class="pw-si input-si" placeholder="Password" name = "userPassword" maxlength="20" required>
            <div class="gender-si input-si">
                <span>Gender</span>
                <span>Male</span>
                <input type="radio" name="userGender" autocomplete="off" value="Male" required checked>
                <span>Female</span>
                <input type="radio" name="userGender" autocomplete="off" value="Female" required>
            </div>
            <input type="submit" class="sub-si input-si" value="Create Account">
        </form>
    </main>

    <div class="boundary-si">
        <div class="div-line-si"></div>
        <span>Or</span>
        <div class="div-line-si"></div>
    </div>

    <footer id="footer-si">
        <a href="/">
            <div class="google-si">
                <img class="google-logo-lo" src="../static/image/googleLogo.png" alt="">
                <span>Sign up for Google</span>
            </div>
        </a>
        <a href="/">
            <div class="kakao-si">
                <img class="kakao-logo-lo" src="../static/image/kakaoLogo.png" alt="">
                <span>Sign up for KakaoTalk</span>
            </div>
        </a>
    </footer>

    <nav id="side-bar-si">
        <div class="top-sidebar-si">
    
        </div>
        <div class="main-sidebar-si">
            <a href="index_login.jsp">
                <div class="home-button">
                    <h2>Home</h2>
                </div>
            </a>
        </div>
        <div class="bottom-sidebar-si">
        
            <a href="login.jsp">
                <div class="sidebar-button-si">
                    <span>Log in</span>
                </div>
            </a>
            <a href="signup.jsp">
                <div class="sidebar-button-si">
                    <span>Sign up</span>
                </div>
            </a>
        </div>
    </nav>
</body>
</html>