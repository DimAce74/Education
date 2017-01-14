<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Автомобили пользователя</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Модель</th>
            <th>Цвет</th>
            <th>Действия</th>
        </tr>
        <c:forEach items="${autos}" var="auto">
        <tr>
            <td>${auto.getId()}</td>
            <td>${auto.getModel()}</td>
            <td>${auto.getColor()}</td>
            <td>    <form action="/updateAuto.jsp" method="post">
                    <input type="hidden" name="auto_id" value="${auto.getId()}">
                    <input type="hidden" name="model" value="${auto.getModel()}">
                    <input type="hidden" name="color" value="${auto.getColor()}">
                    <input type="submit" value="Изменить" style="float:left">
                    </form>
                <form action="/deleteAuto.jsp">
                    <input type="hidden" name="auto_id" value="${auto.getId()}">
                    <input type="submit" value="Удалить" style="float:left">
                </form></td>
        </tr>
        </c:forEach>
    </table>
<br>
<a href="/users">Назад</a>
<a href="index.jsp">На главную</a>
</body>
</html>
