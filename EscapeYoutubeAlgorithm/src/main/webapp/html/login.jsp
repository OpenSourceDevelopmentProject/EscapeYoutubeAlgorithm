<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Watch your youtube chanel without channel without Youtube-Algorithm">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="og:title" content="Escape to Youtube Algorithm!"/>
    
    <link rel="stylesheet" href="../static/css/style.css">

    <title>Login - Escape to Youtube Algorithm</title>
</head>
<body id="body-lo">
    <header id="header-lo">
        <a href="index_login.jsp" class="title-lo">Escape to Youtube Algorithm</a>
        <h1 class="info-lo">Log in</h1>
    </header>

    <main id="main-lo">
        <form method= "post" action="loginAction.jsp" class="login-form-lo">
            <input type="text" class="id-lo input-lo" placeholder="Id" name = "userID" maxlength="20">
            <input type="password" class="pw-lo input-lo" placeholder="Password" name = "userPassword" maxlength="20">
            <input type="submit" class="sub-lo input-lo" value="Log in">
        </form>
    </main>

    <div class="boundary-lo">
        <div class="div-line-lo"></div>
        <span>Or</span>
        <div class="div-line-lo"></div>
    </div>

    <footer id="footer-lo">
        <a href="/">
            <div class="google-lo">
                <img class="google-logo-lo" src="../static/image/googleLogo.png" alt="">
                <span>Log in with Google</span>
            </div>
        </a>
        <a href="/">
            <div class="kakao-lo">
                <img class="kakao-logo-lo" src="../static/image/kakaoLogo.png" alt="">
                <span>Log in with KakaoTalk</span>
            </div>
        </a>
    </footer>
    
    <nav id="side-bar-lo">
        <div class="top-sidebar-lo">
    
        </div>
        <div class="main-sidebar-lo">
            <a href="index_login.jsp">
                <div class="home-button">
                    <h2>Home</h2>
                </div>
            </a>
        </div>
        <div class="bottom-sidebar-lo">
        
            <a href="login.jsp">
                <div class="sidebar-button-lo">
                    <span>Log in</span>
                </div>
            </a>
            <a href="signup.jsp">
                <div class="sidebar-button-lo">
                    <span>Sign up</span>
                </div>
            </a>
        </div>
    </nav>
</body>
</html>