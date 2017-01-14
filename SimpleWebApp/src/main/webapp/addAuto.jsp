<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/front/users/${param.user_id}/autos" method="post">
        <input type="text" name="model" placeholder="Модель">
        <input type="text" name="color" placeholder="Цвет">
        <input type="hidden" name="_method" value="put">
        <input type="submit" value="Сохранить">
    </form>

    <a href="/users">Назад</a>
    <a href="/index.jsp">На главную</a>
</body>
</html>
