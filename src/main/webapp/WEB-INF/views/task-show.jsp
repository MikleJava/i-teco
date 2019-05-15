<%@ page import="ru.girfanov.tm.entity.Task" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>task-show</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css"/>
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="content">
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
        <%Task task = (Task) request.getAttribute("task");%>
        <tr>
            <td><%=task.getId()%></td>
            <td><%=task.getName()%></td>
            <td><%=task.getDescription()%></td>
            <td><%=task.getStatus()%></td>
            <td><%=task.getDateStart()%></td>
            <td><%=task.getDateEnd()%></td>
            <td><%=task.getUserId()%></td>
            <td><%=task.getProjectId()%></td>
        </tr>
    </table>
    <div class="send-button">
        <a href="<%=request.getContextPath()%>/task-edit?task_id=<%=task.getId()%>"> <button type="button">EDIT</button></a>
    </div>
    <form action="<%=request.getContextPath()%>/task-remove?task_id=<%=task.getId()%>" method="post">
        <div class="send-button">
            <button type="submit">REMOVE</button>
        </div>
    </form>
</div>
</body>
</html>