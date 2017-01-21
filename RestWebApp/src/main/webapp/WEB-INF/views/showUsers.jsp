<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Show users</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Возраст</th>
            <th>Действия</th>
        </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getName()}</td>
            <td>${user.getAge()}</td>
            <td>
                <form action="/users/${user.getId()}/autos">
                    <input type="submit" value="Показать автомобили" style="float:left">
                </form>
                <form action="/users/${user.getId()}" method="post">
                    <input type="submit" value="Изменить" style="float:left">
                </form>
                <form:form action="/users/${user.getId()}" method="delete">
                    <input type="submit" value="Удалить" style="float:left">
                </form:form>

            </td>
        </tr>
    </c:forEach>
    </table>
    <form:form modelAttribute="user" action="users" method="post">
        <form:input path="name" />
        <form:input path="age" />
        <input type="submit" value="Добавить" />
    </form:form>

    <a href="/index">На главную</a>

</body>
</html>
