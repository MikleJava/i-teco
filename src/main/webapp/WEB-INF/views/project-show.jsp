<%@ page import="ru.girfanov.tm.entity.Project" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>project-show</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css"/>
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="content">
    <h2>PROJECT</h2>
    <table>
        <caption>PROJECTS</caption>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>DESCRIPTION</th>
            <th>STATUS</th>
            <th>DATE START</th>
            <th>DATE END</th>
            <th>USER ID</th>
        </tr>
        <%Project project = (Project) request.getAttribute("project");%>
        <tr>
            <td><%=project.getId()%></td>
            <td><%=project.getName()%></td>
            <td><%=project.getDescription()%></td>
            <td><%=project.getStatus()%></td>
            <td><%=project.getDateStart()%></td>
            <td><%=project.getDateEnd()%></td>
            <td><%=project.getUserId()%></td>
        </tr>
    </table>
        <div class="send-button">
            <a href="<%=request.getContextPath()%>/project-edit?project_id=<%=project.getId()%>"> <button type="button">EDIT</button></a>
        </div>
    <form action="<%=request.getContextPath()%>/project-remove?project_id=<%=project.getId()%>" method="post">
        <div class="send-button">
            <button type="submit">REMOVE</button>
        </div>
    </form>
</div>
</body>
</html>
