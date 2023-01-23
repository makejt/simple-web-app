<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Update user</title>
</head>

<jsp:include page="header.jsp"/>

<body>

<form action='users?action=U&userId=${user.id}' method='post'>

    <label for='name'>name</label><br>
    <input type='text' id='name' name='name' value='${user.name}' required><br>
    <label for='email'>email</label><br>
    <input type='email' id='email' name='email'value="${user.email}" required><br>
    <label for='password'>password</label><br>
    <input type='text' id='password' name='password' value="${user.psw}"required><br>
    <label for='is_active'>Is active</label><br>
    <input type='checkbox' id='is_active' name='is_active' value="${user.isActive}"><br>
    <select name="officeLocation">
        <c:forEach items="${offices}" var="office">
            <option value="${office.location}" ${office.location == user.office.location ? 'selected' : ''}> ${office.location}
            </option>
        </c:forEach>
    </select>
    <br><br>

    <input type="submit" value="change">

</form>

<jsp:include page="footer.jsp"/>

</body>
</html>