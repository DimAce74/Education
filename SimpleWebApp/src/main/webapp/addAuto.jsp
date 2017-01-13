<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/addAuto" method="post">
        <input type="text" name="model" placeholder="Модель">
        <input type="text" name="color" placeholder="Цвет">
        <input type="hidden" name="user_id" value="${param.user_id}">
        <input type="submit" value="Ok">
    </form>

    <a href="/showUsers">Назад</a>
    <a href="/index.jsp">На главную</a>
</body>
</html>
