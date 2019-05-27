<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
    <link rel="stylesheet" href="/css/style.css" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<div id="header">
    <ul class="hr">
        <sec:authorize access="!isAuthenticated()">
            <li><a href="<c:url value="/"/>" >Main</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li><a href="<c:url value="/project/list" />" >Projects</a></li>
            <li><a href="<c:url value="/task/list" />" >Tasks</a></li>
            <li id="username"><a href="<c:url value="/" />" ><sec:authentication property="principal.username"/></a></li>
        </sec:authorize>
    </ul>
</div>