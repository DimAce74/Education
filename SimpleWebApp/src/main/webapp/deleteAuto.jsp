
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удаление авто</title>
</head>
<body>
Вы хотите удалить автомобиль с ID=${param.auto_id}?
<br>
<form action="/autos/${param.auto_id}" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" value="Удалить">
</form>
<form action="/autos">
    <input type="submit" value="Не удалять!" style="float:left">
</form>

</body>
</html>
