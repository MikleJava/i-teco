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
            <%String userId = (String) request.getSession().getAttribute("user_id");%>
            <c:set var="userId" value="<%=userId%>" scope="page"/>
            <c:if test="${userId==null}">
                <h1>You must be logged in</h1>
            </c:if>
            <c:if test="${userId!=null}">
                <h1>Hello, ${userId}</h1>
            </c:if>
        </div>
    </div>
</body>
</html>
