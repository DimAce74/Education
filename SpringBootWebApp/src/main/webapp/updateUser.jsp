
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/users/${param.user_id}" method="post">
        <input required type="text" name="user_name" placeholder="${param.user_name}">
        <input required type="text" name="user_age" placeholder="${param.user_age}">
        <input type="submit" value="Ok">
    </form>

<a href="users">Назад</a>
<a href="index.jsp">На главную</a>
</body>
</html>
