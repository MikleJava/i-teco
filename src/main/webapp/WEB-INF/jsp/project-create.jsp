<%@ page import="ru.girfanov.tm.enumeration.Status" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>project-create</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<jsp:include page="header.jsp"/>
<body>
    <div class="content">
        <h2>CREATE PROJECT</h2>
        <%--@elvariable id="project" type="ru.girfanov.tm.dto.ProjectDto"--%>
        <form:form modelAttribute="project" action="/project/create" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <form:input type="text" class="form-control" path="name" id="name"/>
            </div>
            <div class="form-group">
                <label for="desc">Description:</label>
                <form:input type="text" class="form-control" path="description" id="desc"/>
            </div>
            <div class="form-group">
                <label for="status">Status:</label>
                <form:select class="form-control" id="status" name="sellStatus" path="status">
                    <c:forEach var="s" items="<%=Status.values()%>">
                        <form:option value="${s.name()}">${s.name()}</form:option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group row">
                <label for="date-start" class="col-2 col-form-label">Date Start:</label>
                <form:input class="form-control" type="date" path="dateStart" id="date-start"/>
            </div>
            <div class="form-group row">
                <label for="date-end" class="col-2 col-form-label">Date End:</label>
                <form:input class="form-control" type="date" path="dateEnd" id="date-end"/>
            </div>
            <div class="send-button">
                <button type="submit" class="btn btn-success">CREATE</button>
            </div>
        </form:form>
    </div>
</body>
</html>
