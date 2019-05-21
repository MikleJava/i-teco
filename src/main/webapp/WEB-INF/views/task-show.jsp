<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>task-show</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="content">
    <%--@elvariable id="task" type="ru.girfanov.tm.dto.TaskDto"--%>
    <c:if test="${task!=null}">
        <h2>TASK</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>DESCRIPTION</th>
                <th>STATUS</th>
                <th>DATE START</th>
                <th>DATE END</th>
                <th>USER ID</th>
                <th>PROJECT ID</th>
            </tr>
            <tr>
                <td>${task.id}</td>
                <td>${task.name}</td>
                <td>${task.description}</td>
                <td>${task.status}</td>
                <td>${task.dateStart}</td>
                <td>${task.dateEnd}</td>
                <td>${task.userId}</td>
                <td>${task.projectId}</td>
            </tr>
        </table>
        <div class="send-button">
            <a href="/task/edit?task_id=${task.id}"> <button type="button">EDIT</button></a>
        </div>
        <form action="/task/remove?task_id=${task.id}" method="post">
            <div class="send-button">
                <button type="submit">REMOVE</button>
            </div>
        </form>
    </c:if>
</div>
</body>
</html>
