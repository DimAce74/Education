<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>Вы в системе под именем ${cookie.get("user").value}</h3>

    <form:form action="/users/${user.id}" method="put">
        <input type="text" name="user_name" value="${user.name}">
        <input type="text" name="user_age" placeholder="${user.age}">
        <input type="submit" value="Ok">
    </form:form>

    <a href="/users">Назад</a>
    <a href="/index">На главную</a>
</body>
</html>
