<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="content">
    <h2>AUTHORIZATION</h2>
    <form action="<c:url value="/login"/>" method="post">
        <div class="form-group">
            <label for="login">Login:</label>
            <input id="login" type="email" name="login" placeholder="Email address" class="form-control" required autofocus/>
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input id="pwd" type="password" name="password" class="form-control" placeholder="Password">
        </div>
        <div class="send-button">
            <button type="submit" class="btn btn-success">SIGN IN</button>
        </div>
    </form>
</div>
</body>
</html>
