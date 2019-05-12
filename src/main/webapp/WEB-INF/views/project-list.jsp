<%@ page import="java.util.List" %>
<%@ page import="ru.girfanov.tm.entity.Project" %>
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
        <h2>PROJECT LIST</h2>
        <table>
            <colgroup>
                <col class="number">
                <col class="id">
                <col class="name">
                <col class="description">
                <col class="status">
                <col class="date-start">
                <col class="date-end">
                <col class="user-id">
                <col class="edit">
                <col class="remove">
            </colgroup>
            <tr>
                <th>№</th>
                <th>ID</th>
                <th>NAME</th>
                <th>DESCRIPTION</th>
                <th>STATUS</th>
                <th>DATE START</th>
                <th>DATE END</th>
                <th>USER ID</th>
                <th>EDIT</th>
                <th>REMOVE</th>
            </tr>
            <%List<Project> projects = (List<Project>) request.getAttribute("projects");%>
            <c:set var="projects" value="<%=projects%>" scope="page"/>
            <c:forEach var="project" items="${projects}" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${project.id}</td>
                    <td>${project.name}</td>
                    <td>${project.description}</td>
                    <td>${project.status}</td>
                    <td>${project.dateStart}</td>
                    <td>${project.dateEnd}</td>
                    <td>${project.userId}</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/project-edit?pid=${project.id}"><i class="fas fa-edit"></i></a><!--&nbsp;-->
                    </td>
                    <td>
                        <a href="<%=request.getContextPath()%>/project-remove?pid=${project.id}"><i class="fas fa-trash-alt"></i></a><!--&nbsp;-->
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="send-button">
            <a href="<%=request.getContextPath()%>/project-create"> <button type="button">CREATE PROJECT</button></a>
        </div>
    </div>
</body>
</html>
