<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
    <p style="color: red">${errorMessage}</p>
    <form action="/spring-mvc/login" method="POST">
        Name : <input name="name" type="text" />
        Password : <input name="password" type="password" />
        <input type="submit" />
    </form>
</body>
</html>