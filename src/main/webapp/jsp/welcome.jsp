<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home</title>
</head>
<jsp:include page="header.jsp"/>
<body>

<c:if test="${user != null}">
    <p>Hello, ${user.name}</p>

</c:if>

</body>
<jsp:include page="footer.jsp"/>
</html>