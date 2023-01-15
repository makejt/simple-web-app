<%@ page import="org.example.model.Role" %>
<%@ page import="java.util.Set" %>
<html>
<head>
  <title>Roles list</title>
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
    <th>Description</th>
  </tr>

  <%
    Set<Role> roles = (Set<Role>) request.getAttribute("role");
    for (Role role : roles)
    { %>
  <tr>
    <td><%=role.getId()%></td>
    <td><%=role.getName()%></td>
    <td><%=role.getDescription()%></td>
  </tr>

  <% } %>

</table>
</body>
</html>