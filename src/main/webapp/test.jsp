<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>


<!--Declaration Это поля класса -->
  <%!Date serverDate = new Date();%>

  <!--Scriptlet-->
<%
  //start Java logic
  System.out.println("Java Scriptlet" + serverDate);
  for (int i = 0; i < 5; i++) { %>
        <h4> <%=i%></h4>
  <% }%>

<!--Expression-->
<h2> <%=serverDate%></>h2>



</body>
</html>