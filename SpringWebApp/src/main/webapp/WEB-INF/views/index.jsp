
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<h3>Вы в системе под именем ${cookie.get("user").value} </h3>

Что вы хотите сделать?
<br>
<form action="/users" method="get">
    <input type="submit" value="Получить список пользователей">
</form>
<br>
<form action="/autos" method="get">
    <input type="submit" value="Получить список всех автомобилей">
</form>
<br>
<form action="/logout" method="post">
    <input type="submit" value="Logout" >
</form>
</body>
</html>
