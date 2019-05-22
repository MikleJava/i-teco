<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>registration</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<jsp:include page="header.jsp"/>
<body>
    <div class="content">
        <h2>REGISTRATION</h2>
        <%--@elvariable id="user" type="ru.girfanov.tm.dto.UserDto"--%>
        <form:form modelAttribute="user" action="/registration" method="post">
            <div class="form-group">
                <label for="login">Login:</label>
                <form:input type="email" path="login" placeholder="Email" id="login"/>
<%--                <input id="login" type="email" name="login" placeholder="Email address" class="form-control" required autofocus/>--%>
            </div>
            <div class="form-group">
                <label for="pwd">Password:</label>
                <form:input type="password" path="password" placeholder="Password" id="pwd"/>
<%--                <input id="pwd" type="password" name="password" class="form-control" placeholder="Password">--%>
            </div>
            <div class="form-group">
                <label for="repwd">Repeat password:</label>
                <form:input type="password" path="repassword" placeholder="Repeat password" id="repwd"/>
<%--                <input id="repwd" type="password" name="repassword" class="form-control" placeholder="Password">--%>
            </div>
            <div class="send-button">
                <button type="submit" class="btn btn-info">SIGN UP</button>
            </div>
        </form:form>
    </div>
</body>
</html>
