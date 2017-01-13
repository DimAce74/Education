<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Show users</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Action</th>
        </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getName()}</td>
            <td>${user.getAge()}</td>
            <td>
                <form action="/showAuto">
                    <input type="hidden" name="user_id" value="${user.getId()}">
                    <input type="submit" value="Показать автомобили" style="float:left">
                </form>
                <form action="/addAuto.jsp">
                    <input type="hidden" name="user_id" value="${user.getId()}">
                    <input type="submit" value="Добавить автомобиль" style="float:left">
                </form>
                <form action="/updateUser.jsp" method="post">
                    <input type="hidden" name="user_id" value="${user.getId()}">
                    <input type="hidden" name="user_name" value="${user.getName()}">
                    <input type="hidden" name="user_age" value="${user.getAge()}">
                    <input type="submit" value="Изменить" style="float:left">
                </form>
                <form action="updateUser.jsp">
                    <input type="button" value="Удалить" style="float:left">
                </form>

            </td>
        </tr>
    </c:forEach>
    </table>

</body>
</html>
