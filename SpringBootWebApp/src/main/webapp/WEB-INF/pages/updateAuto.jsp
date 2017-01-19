
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить автомобиль</title>
</head>
<body>
    <form action="/autos/${param.auto_id}" method="post">
        <input required type="text" name="model" placeholder="${param.model}">
        <input required type="text" name="color" placeholder="${param.color}">
        <input type="submit" value="Ok">
    </form>

<a href="users">Назад</a>
<a href="index.jsp">На главную</a>
</body>
</html>
