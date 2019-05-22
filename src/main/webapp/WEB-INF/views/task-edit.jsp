<%@ page import="ru.girfanov.tm.enumeration.Status" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>task-edit</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="content">
    <%--@elvariable id="task" type="ru.girfanov.tm.dto.TaskDto"--%>
    <c:if test="${task!=null}">
        <h2>EDIT TASK</h2>
        <%--@elvariable id="task" type="ru.girfanov.tm.dto.TaskDto"--%>
        <form:form modelAttribute="task" action="/task/edit" method="post">
            <form:input path="id" type="hidden"/>
            <div class="name-field">
                <div class="task-name">
                    <p>Name</p>
                    <label>
                        <form:input type="text" path="name" placeholder="name"/>
                    </label>
                </div>
            </div>
            <div class="desc-field">
                <div class="task-desc">
                    <p>Description</p>
                    <label>
                        <form:input type="text" path="description" placeholder="description"/>
                    </label>
                </div>
            </div>
            <div class="status-field">
                <div class="task-status">
                    <p>Status</p>
                    <label>
                        <form:select path="status" size="1">
                            <c:forEach var="s" items="<%=Status.values()%>">
                                <form:option value="${s.name()}">${s.name()}</form:option>
                            </c:forEach>
                        </form:select>
                    </label>
                </div>
            </div>
            <div class="date-start-field">
                <div class="task-date-start">
                    <p>Date start</p>
                    <label>
                        <form:input type="date" path="dateStart"/>
                    </label>
                </div>
            </div>
            <div class="date-end-field">
                <div class="task-date-end">
                    <p>Date end</p>
                    <label>
                        <form:input type="date" path="dateEnd"/>
                    </label>
                </div>
            </div>
            <form:input path="userId" type="hidden"/>
            <div class="task-projects-field">
                <div class="task-projects">
                    <p>Projects</p>
                    <label>
                        <form:select path="projectId" size="1">
                            <%--@elvariable id="projects" type="java.util.List"--%>
                            <c:forEach var="p" items="${projects}">
                                <form:option value="${p.id}">${p.id}</form:option>
                            </c:forEach>
                        </form:select>
                    </label>
                </div>
            </div>
            <div class="send-button">
                <button type="submit" class="btn btn-success">EDIT</button>
            </div>
        </form:form>
    </c:if>
</div>
</body>
</html>
