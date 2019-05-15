<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css"/>
</head>
<jsp:include page="header.jsp"/>
<body>
    <div class="error-img">
        <div class="error-content">
            <%String error = (String) request.getAttribute("error");%>
            <%=error%>
        </div>
    </div>

</body>
</html>
