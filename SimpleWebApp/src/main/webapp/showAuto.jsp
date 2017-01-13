<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Автомобили пользователя</title>
</head>
<body>
    <ol type="1">
        <c:forEach items="${auto_list}" var="auto">
            <li>Модель: ${auto.getModel()}, цвет:${auto.getColor()}</li>
        </c:forEach>
    </ol>

<a href="/showUsers">Назад</a>
<a href="/index.jsp">На главную</a>
</body>
</html>
