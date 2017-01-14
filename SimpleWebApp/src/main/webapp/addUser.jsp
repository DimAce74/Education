
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Новый пользователь:
<br>
<form action="/front/users" method="post">
    <input required type="text" name="user_name" placeholder="Имя">
    <input required type="text" name="user_age" placeholder="Возраст">
    <input type="hidden" name="_method" value="put">
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
