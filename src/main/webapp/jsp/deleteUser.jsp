<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Delete user</title>
</head>
<jsp:include page="header.jsp"/>

<body>

<h2> Are you sure you want to delete user ${deleteUser.name} ?</h2>

        <form>
            <input formaction="users?action=D&userId=${deleteUser.id}" formmethod="post" type="submit" value="YES" />
            <input formaction="users" formmethod="get" type="submit" value="NO" />
        </form>

</body>

<jsp:include page="footer.jsp"/>

</html>