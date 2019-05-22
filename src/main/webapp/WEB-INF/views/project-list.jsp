<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>project-list</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<jsp:include page="header.jsp"/>
<body>
    <div class="content">
        <h2>PROJECT LIST</h2>
        <table>
            <tr>
                <th>№</th>
                <th>ID</th>
                <th>NAME</th>
                <th>STATUS</th>
                <th>SHOW</th>
            </tr>
            <%--@elvariable id="projects" type="java.util.List"--%>
            <c:forEach var="project" varStatus="i" items="${projects}">
                <tr>
                    <td>${i.count}</td>
                    <td>${project.id}</td>
                    <td>${project.name}</td>
                    <td>${project.status}</td>
                    <td>
                        <a href="/project/show?project_id=${project.id}">SHOW</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="send-button">
            <a href="/project/create"> <button type="button" class="btn btn-success">CREATE PROJECT</button></a>
        </div>
    </div>
</body>
</html>
