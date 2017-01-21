<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить автомобиль</title>
</head>
<body>
    <form:form action="/users/${auto.getUser().getId()}/autos/${auto.id}" method="put">
        <input required type="text" name="model" placeholder="${auto.model}">
        <input required type="text" name="color" placeholder="${auto.color}">
        <input type="submit" value="Ok">
    </form:form>

    <a href="/users/${user.id}/autos">Назад</a>
    <a href="/index">На главную</a>
</body>
</html>
