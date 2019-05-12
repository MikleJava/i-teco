<%@ page import="ru.girfanov.tm.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>main</title>
</head>
<jsp:include page="header.jsp"/>
<body>
    <div class="content">
        <div class="greeting">
            <%User user = (User) request.getSession().getAttribute("user");%>
            <c:set var="user" value="<%=user%>" scope="page"/>
            <c:if test="${user==null}">
                <h1>You must be logged in</h1>
            </c:if>
            <c:if test="${user!=null}">
                <h1>Hello, ${user.login}</h1>
            </c:if>
        </div>
    </div>
</body>
</html>
