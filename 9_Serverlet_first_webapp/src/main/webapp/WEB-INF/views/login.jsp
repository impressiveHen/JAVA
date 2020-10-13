<%--
  Created by IntelliJ IDEA.
  User: shianghu
  Date: 8/27/20
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.Date"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>

<%
    System.out.println("Javacode in JSP, (scriptlet)");
    Date now = new java.util.Date();
%>

<body>
    <P>
        <h1 style="color: red">${name}'s first JSP servlet</h1>
        <div>Current date is <%=now%></div>
    </P>
    <form action="login.do" method="post">
        <div>Enter your name</div>
        <input type="text" name="credential">
        <input type="submit" value="login">
    </form>
</body>
</html>
