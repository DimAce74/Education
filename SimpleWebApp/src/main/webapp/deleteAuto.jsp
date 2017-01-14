<%--
  Created by IntelliJ IDEA.
  User: dimac
  Date: 14.01.2017
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Вы хотите удалить автомобиль с ID=${param.auto_id}?
<br>
<form action="/front/autos/${param.auto_id}" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" value="Удалить">
</form>
<form action="/autos">
    <input type="submit" value="Не удалять!" style="float:left">
</form>

</body>
</html>
