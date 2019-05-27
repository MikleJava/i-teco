<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>main</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<jsp:include page="header.jsp"/>
<body>
    <div class="greeting">
        <sec:authorize access="!isAuthenticated()">
            <p><a class="btn btn-lg btn-info" href="<c:url value="/registration"/>" role="button">SIGN UP</a></p>
            <p><a class="btn btn-success btn-lg" href="<c:url value="/login" />" role="button">SIGN IN</a></p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">SIGN OUT</a></p>
        </sec:authorize>
    </div>
</body>
</html>
