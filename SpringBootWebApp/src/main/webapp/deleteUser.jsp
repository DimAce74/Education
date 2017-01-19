
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Вы хотите удалить пользователя с ID=${param.user_id}?
<br>
<form action="/users/${param.user_id}" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" value="Удалить">
</form>
<form action="/users">
    <input type="submit" value="Не удалять!" style="float:left">
</form>

</body>
</html>
