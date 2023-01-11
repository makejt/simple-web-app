<%@ page import="org.example.model.User" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crazy Users List</title>
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
        <th>OFFICE</th> <!-- ADD href view offices-->
        <th>ACTIVE</th>
        <th>LAST UPDATED</th>
        <th>CREATED</th>
  <!-- ADD ROLES HEADER href  role-->
    </tr>

    <%
        Set<User> users = (Set<User>) request.getAttribute("users");
        for (User user : users) { %>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getPsw()%></td>
        <td>TO DO</td>
        <td>TO DO</td>
        <td><%=user.is_active()? "Yes":"No"%></td>
        <td><%=user.getUpdateTs()%></td>
        <td><%=user.getCreatedTs()%></td>

    </tr>

    <% } %>

</table>
</body>
</html>