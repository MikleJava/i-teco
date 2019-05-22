<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>task-list</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="content">
    <h2>TASK LIST</h2>
    <table>
        <tr>
            <th>№</th>
            <th>ID</th>
            <th>NAME</th>
            <th>STATUS</th>
            <th>SHOW</th>
        </tr>
        <%--@elvariable id="tasks" type="java.util.List"--%>
        <c:forEach var="task" varStatus="i" items="${tasks}">
            <tr>
                <td>${i.count}</td>
                <td>${task.id}</td>
                <td>${task.name}</td>
                <td>${task.status}</td>
                <td>
                    <a href="/task/show?task_id=${task.id}">SHOW</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="send-button">
        <a href="/task/create"> <button type="button" class="btn btn-success">CREATE TASK</button></a>
    </div>
</div>
</body>
</html>
