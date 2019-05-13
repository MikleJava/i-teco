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
            <caption>PROJECTS</caption>
            <tr>
                <th>№</th>
                <th>ID</th>
                <th>NAME</th>
                <th>SHOW</th>
            </tr>
            <%List<Project> projects = (List<Project>) request.getAttribute("projects");%>
            <c:set var="projects" value="<%=projects%>" scope="page"/>
            <c:forEach var="project" varStatus="i" items="${projects}">
                <tr>
                    <td>${i.count}</td>
                    <td>${project.id}</td>
                    <td>${project.name}</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/project-show?project_id=${project.id}">SHOW</a>
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
