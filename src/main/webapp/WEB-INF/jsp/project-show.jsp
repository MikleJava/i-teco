<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>project-show</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="content">
    <%--@elvariable id="project" type="ru.girfanov.tm.dto.ProjectDto"--%>
    <c:if test="${project!=null}">
        <h2>PROJECT</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>DESCRIPTION</th>
                <th>STATUS</th>
                <th>DATE START</th>
                <th>DATE END</th>
                <th>USER ID</th>
            </tr>
            <tr>
                <td>${project.id}</td>
                <td>${project.name}</td>
                <td>${project.description}</td>
                <td>${project.status}</td>
                <td>${project.dateStart}</td>
                <td>${project.dateEnd}</td>
                <td>${project.userId}</td>
            </tr>
        </table>
        <div class="send-button">
            <a href="/project/edit?project_id=${project.id}"> <button type="button" class="btn btn-warning">EDIT</button></a>
        </div>
        <form action="/project/remove?project_id=${project.id}" method="post">
            <div class="send-button">
                <button type="submit" class="btn btn-danger">REMOVE</button>
            </div>
        </form>
    </c:if>
</div>
</body>
</html>
