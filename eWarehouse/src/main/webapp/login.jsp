<%--
    Document   : test_login
    Created on : Jan 26, 2010, 6:50:04 PM
    Author     : jonycus
--%>

<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Login</title>
        <style type="text/css">
            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }
            body {
                display: table;
                height: 100%;
                width: 100%;
                text-align: center;
            }
            #wrapper {
                display: table-cell;
                vertical-align: middle;
            }
            #centered {
                width: 340px;
                height: 250px;
                margin: 0 auto;
                background: url(genrep-resources/images/login_bg.jpg) repeat-x top left;
                border: 1px solid white;
                padding: 5px;
            }
            .forma {
                margin-top: 30px;
                padding-left: 100px;
                font-family: Arial, sans-serif;
                font-size: 10px;
            }

            .forma img {
                float: left;
                position: relative;
                left: -60px;
                top: 20px;
            }

            .errormessage {
                color:red;
            }
        </style>
    </head>


    <body onload="document.f.j_username.focus();" style="background-color: #d3dbe6; margin: 0 auto;">
        <div id="wrapper">
            <div id="centered">
                <!--<img src="./genrep-resources/images/login_header.jpg"/>-->
                <div class="forma">
                    <form name="f" action="j_spring_security_check" method="POST">
                        <img src="./genrep-resources/images/login_key.gif"/>
                        <table>
                            <tr><td>Корисник</td></tr><tr><td><input type='text' name='j_username' /></td></tr>
                            <tr><td>Лозинка</td></tr><tr><td><input type='password' name='j_password' /></td></tr>
                            <tr><td><input name="submit" type="image" src="./genrep-resources/images/login_ok.jpg" /></td></tr>
                        </table>
                        <c:if test="${not empty param.authfailed}">
                            <span id="infomessage" class="errormessage" >
                                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                            </span>
                        </c:if>
                        <c:if test="${not empty param.acclocked}">
                            <span id="infomessage" class="errormessage" >
                                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                            </span>
                        </c:if>
                        <c:if test="${not empty param.accdisabled}">
                            <span id="infomessage" class="errormessage" >
                                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                            </span>
                        </c:if>
                        <c:if test="${not empty param.serviceException}">
                            <span id="infomessage" class="errormessage" >
                                Service exception: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                            </span>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
