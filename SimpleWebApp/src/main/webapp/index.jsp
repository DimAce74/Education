
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
Что вы хотите сделать?
<br>
<form action="/users" method="get">
    <input type="submit" value="Получить список пользователей">
</form>
<br>
<form action="/autos" method="get">
    <input type="submit" value="Получить список всех автомобилей">
</form>
<form action="/addUser.jsp">
    <input type="submit" value="Добавить пользователя">
</form>
</body>
</html>
