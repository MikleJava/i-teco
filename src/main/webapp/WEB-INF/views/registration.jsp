<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>registration</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<jsp:include page="header.jsp"/>
<body>
    <div class="container">
        <h2>REGISTRATION</h2>
        <%--@elvariable id="user" type="ru.girfanov.tm.dto.UserDto"--%>
        <form:form modelAttribute="user" action="/registration" method="post" class="was-validated">
            <div class="form-group">
                <label for="login">Login:</label>
                <form:input type="email" class="form-control" path="login" placeholder="Email" id="login"/>
                <div class="valid-feedback">Valid</div>
                <div class="invalid-feedback">Please fill out this field</div>
            </div>
            <div class="form-group">
                <label for="pwd">Password:</label>
                <form:input type="password" class="form-control" path="password" placeholder="Password" id="pwd"/>
                <div class="valid-feedback">Valid</div>
                <div class="invalid-feedback">Please fill out this field</div>
            </div>
            <div class="form-group">
                <label for="repwd">Repeat password:</label>
                <form:input type="password" class="form-control" path="repassword" placeholder="Repeat password" id="repwd"/>
                <div class="valid-feedback">Valid</div>
                <div class="invalid-feedback">Please fill out this field</div>
            </div>
            <div class="send-button">
                <button type="submit" class="btn btn-info">SIGN UP</button>
            </div>
        </form:form>
    </div>
</body>
</html>
