<%@ page import="org.example.model.User" %>
<%@ page import="java.util.Set" %>
<%@ page import="org.example.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    </tr>

    <%
        Set<User> users = (Set<User>) request.getAttribute("users");
        for (User user : users) { %>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getPsw()%></td>
        <td><%=user.getOffice().getName()%> <a href="office"> more info here </a> </td>

        <td><%
        for (Role role: user.getRoles()) { %>
            <%=role.getName()%>
            <% } %> <a href="role"> more info here </a> </td>
       <td><%=user.is_active()? "Yes":"No"%></td>
        <td><%=user.getUpdateTs()%></td>
        <td><%=user.getCreatedTs()%></td>

    </tr>

    <% } %>

</table>
</body>
</html>