<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Regions of Belarus</title>
</head>

<title>Regions of Belarus</title>
</head>

<body>

  <h3>Regions of Belarus</h3>

  <br>

  <table border="3">

      <tr>
            <th>Name</th>
            <th>Population (people) </th>
            <th>Square (square meter)</th>
    </tr>

    <c:forEach items="${regions}" var="regions" varStatus="rowStatus">
      <tr>
        <td>${regions.name}</td>
        <td>${regions.population}</td>
        <td>${regions.square}</td>
      </tr>
    </c:forEach>
  </table>

</body>
</html>