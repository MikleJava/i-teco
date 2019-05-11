<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>registration</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css"/>
</head>
<jsp:include page="header.jsp"/>
<body>
    <div class="content">
        <form action="<%=request.getContextPath()%>/registration" method="post">
            <div class="login-field">
                <div class="login-name">
                    <p>Login</p>
                    <label>
                        <input type="text" name="login" placeholder="LOGIN"/>
                    </label>
                </div>
            </div>
            <div class="password-field">
                <div class="password-name">
                    <p>Password</p>
                    <label>
                        <input type="password" name="password" placeholder="PASSWORD"/>
                    </label>
                </div>
            </div>
            <div class="send-button">
                <button type="submit">SEND</button>
            </div>
        </form>
    </div>
</body>
</html>
