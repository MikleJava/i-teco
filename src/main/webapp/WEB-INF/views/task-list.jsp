<%@ page import="java.util.List" %>
<%@ page import="ru.girfanov.tm.entity.Task" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>project-list</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css"/>
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="content">
    <h2>TASK LIST</h2>
    <table>
        <caption>TASKS</caption>
        <tr>
            <th>№</th>
            <th>ID</th>
            <th>NAME</th>
            <th>SHOW</th>
        </tr>
        <%List<Task> tasks = (List<Task>) request.getAttribute("tasks");%>
        <c:set var="tasks" value="<%=tasks%>" scope="page"/>
        <c:forEach var="task" varStatus="i" items="${tasks}">
            <tr>
                <td>${i.count}</td>
                <td>${task.id}</td>
                <td>${task.name}</td>
                <td>
                    <a href="<%=request.getContextPath()%>/task-edit?pid=${task.id}"></a>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/task-remove?pid=${task.id}"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="send-button">
        <a href="<%=request.getContextPath()%>/task-create"> <button type="button">CREATE TASK</button></a>
    </div>
</div>
</body>
</html>
