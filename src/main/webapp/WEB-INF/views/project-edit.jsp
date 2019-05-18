<%@ page import="ru.girfanov.tm.enumeration.Status" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>project-edit</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="content">
<%--@elvariable id="project" type="ru.girfanov.tm.entity.Project"--%>
<c:if test="${project!=null}">
    <h2>EDIT PROJECT</h2>
    <%--@elvariable id="project" type="ru.girfanov.tm.entity.Project"--%>
    <form:form modelAttribute="project" action="/project/edit" method="post">
        <input type="hidden" name="project_id" value="${project.id}" />
        <div class="name-field">
            <div class="project-name">
                <p>Name</p>
                <label>
                    <form:input type="text" path="name" placeholder="name"/>
                </label>
            </div>
        </div>
        <div class="desc-field">
            <div class="project-desc">
                <p>Description</p>
                <label>
                    <form:input type="text" path="description" placeholder="description"/>
                </label>
            </div>
        </div>
        <div class="status-field">
            <div class="project-status">
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
            <div class="project-date-start">
                <p>Date start</p>
                <label>
                <form:input type="date" path="dateStart"/>
                </label>
            </div>
        </div>
        <div class="date-end-field">
            <div class="project-date-end">
                <p>Date end</p>
                <label>
                    <form:input type="date" path="dateEnd"/>
                </label>
            </div>
        </div>
        <div class="send-button">
            <button type="submit">EDIT</button>
        </div>
    </form:form>
</c:if>
</div>
</body>
</html>
