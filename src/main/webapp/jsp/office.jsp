<%@ page import="org.example.model.Office" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Offices list</title>
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
    <th>LOCATION</th>
    <th>PHONE</th>
    <th>FAX</th>
  </tr>

  <%
    Set<Office> offices = (Set<Office>) request.getAttribute("office");
    for (Office office : offices)
    { %>
  <tr>
    <td><%=office.getId()%></td>
    <td><%=office.getName()%></td>
    <td><%=office.getLocation()%></td>
    <td><%=office.getPhone()%></td>
    <td><%=office.getFax()%></td>
  </tr>

  <% } %>

</table>
</body>
</html>