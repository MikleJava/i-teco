<%@ page import="ru.girfanov.tm.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%User user = (User) request.getAttribute("user");%>
<c:set var="user" value="<%=user%>" scope="page"/>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css"/>
</head>
<div id="header">
    <ul class="hr">
        <li><a href="<%=request.getContextPath()%>/">Main</a></li>
        <c:if test="${user==null}">
            <li><a href="<%=request.getContextPath()%>/authorization">Sign in</a></li>
            <li><a href="<%=request.getContextPath()%>/registration">Sign up</a></li>
        </c:if>
        <c:if test="${user!=null}">
            <li><a href="<%=request.getContextPath()%>/showproject">Projects</a></li>
            <li><a href="<%=request.getContextPath()%>/showtask">Tasks</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">Sign out</a></li>
        </c:if>
    </ul>
</div>