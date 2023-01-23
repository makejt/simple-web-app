<%@ page import="org.example.model.User" %>
<%@ page import="java.util.Set" %>
<%@ page import="org.example.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Users List</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
            border: solid;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>


<h2> ${msg == null ? '' : msg}</h2>

<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>EMAIL</th>
        <th>PASSWORD</th>
        <th>OFFICE</th>
        <th>ROLE</th>
        <th>ACTIVE</th>
        <th>LAST UPDATED</th>
        <th>CREATED</th>
        <th>UPDATE</th>
        <th>DELETE</th>
    </tr>


    <c:forEach items="${users}" var= "user" varStatus="counter">
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>${user.psw}</td>
        <td> <a href="viewOffice?id=${user.office.id}"> ${user.office.location} View Details</a></td>
        <td> <a href="viewRolesList?userid=${user.id}"> View Details</a></td>
        <td>${user.isActive}</td>
        <td>${user.updateTs}</td>
        <td>${user.createdTs}</td>
        <td><a href = 'users?action=U&userId=${user.id}'>UPDATE</a></td>
        <td><a href = 'users?action=D&userId=${user.id}'>DELETE</a></td>

    </tr>
</c:forEach>



<%--    <%--%>
<%--        Set<User> users = (Set<User>) request.getAttribute("users");--%>
<%--        for (User user : users) { %>--%>
<%--    <tr>--%>
<%--        <td><%=user.getId()%></td>--%>
<%--        <td><%=user.getName()%></td>--%>
<%--        <td><%=user.getEmail()%></td>--%>
<%--        <td><%=user.getPsw()%></td>--%>
<%--        <td><%=user.getOffice().getName()%> <a href="office"> more info here </a> </td>--%>

<%--        <td><%--%>
<%--        for (Role role: user.getRoles()) { %>--%>
<%--            <%=role.getName()%>--%>
<%--            <% } %> <a href="role"> more info here </a> </td>--%>
<%--       <td><%=user.is_active()? "Yes":"No"%></td>--%>
<%--        <td><%=user.getUpdateTs()%></td>--%>
<%--        <td><%=user.getCreatedTs()%></td>--%>
<%--        <td><a href = 'user?action=U'>UPDATE</a></td>--%>
<%--        <td><a href = 'user?action=D'>DELETE</a></td>--%>

<%--    </tr>--%>

<%--    <% } %>--%>

</table>

<a href = 'users?action=C'>CREATE NEW USER</a>

<jsp:include page="footer.jsp"/>


</body>
</html>