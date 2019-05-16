<%@ page import="ru.girfanov.tm.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css"/>
</head>
<div id="header">
    <ul class="hr">
        <li><a href="<%=request.getContextPath()%>/">Main</a></li>
        <%String userId = (String) request.getSession().getAttribute("user_id");%>
        <c:set var="userId" value="<%=userId%>" scope="page"/>
        <c:if test="${userId==null}">
            <li><a href="<%=request.getContextPath()%>/authorization">Sign in</a></li>
            <li><a href="<%=request.getContextPath()%>/registration">Sign up</a></li>
        </c:if>
        <c:if test="${userId!=null}">
            <li><a href="<%=request.getContextPath()%>/project-list">Projects</a></li>
            <li><a href="<%=request.getContextPath()%>/task-list">Tasks</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">Sign out</a></li>
        </c:if>
    </ul>
</div>