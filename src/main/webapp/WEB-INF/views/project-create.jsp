<%@ page import="ru.girfanov.tm.enumeration.Status" %>
<%@ page import="java.util.Date" %>
<%@ page import="ru.girfanov.tm.util.DateFormatUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>project-create</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css"/>
</head>
<jsp:include page="header.jsp"/>
<body>
    <div class="content">
        <h2>CREATE PROJECT</h2>
        <form action="<%=request.getContextPath()%>/project-create" method="post">
            <div class="name-field">
                <div class="project-name">
                    <p>Name</p>
                    <label>
                        <input type="text" name="name" placeholder="name"/>
                    </label>
                </div>
            </div>
            <div class="desc-field">
                <div class="project-desc">
                    <p>Description</p>
                    <label>
                        <input type="text" name="desc" placeholder="description"/>
                    </label>
                </div>
            </div>
            <div class="status-field">
                <div class="project-status">
                    <p>Status</p>
                    <label>
                        <select name="status" multiple size="1">
                            <c:forEach var="s" items="<%=Status.values()%>">
                                <option value="${s.name()}"> ${s.name()} </option>
                            </c:forEach>
                        </select>
                    </label>
                </div>
            </div>
            <div class="date-start-field">
                <div class="project-date-start">
                    <p>Date start</p>
                    <label>
                        <input type="date" name="date-start"
                               value = "<fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd" />"
                        />
                    </label>
                </div>
            </div>
            <div class="date-end-field">
                <div class="project-date-end">
                    <p>Date end</p>
                    <label>
                        <input type="date" name="date-end" value = "<fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd" />"/>
                    </label>
                </div>
            </div>
            <div class="send-button">
                <button type="submit">CREATE</button>
            </div>
        </form>
    </div>
</body>
</html>
