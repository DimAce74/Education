<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Автомобили </title>
</head>
<body>
<h1>Автомобили пользователя ${user.name}</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Модель</th>
            <th>Цвет</th>
            <th>Действия</th>
        </tr>
        <c:forEach items="${autos}" var="auto">
        <tr>
            <td>${auto.id}</td>
            <td>${auto.model}</td>
            <td>${auto.color}</td>
            <td>    <form action="/users/${user.id}/autos/${auto.id}" method="post">
                    <input type="submit" value="Изменить" style="float:left">
                    </form>
                <form:form action="/users/${user.id}/autos/${auto.id}" method="delete">
                    <input type="submit" value="Удалить" style="float:left">
                </form:form></td>
        </tr>
        </c:forEach>
    </table>
    <form:form modelAttribute="auto" action="/users/${user.id}/autos" method="post">
        <form:input path="model" />
        <form:input path="color" />
        <input type="submit" value="Добавить" />
    </form:form>

    <br>
<a href="/users/${user.id}/autos">Назад</a>
<a href="/index">На главную</a>
</body>
</html>
