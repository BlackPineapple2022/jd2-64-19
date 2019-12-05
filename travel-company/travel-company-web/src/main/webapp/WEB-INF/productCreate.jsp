
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>

<form method="post" action="/productCreate">

    <label>Name: <input type = "text" name="name"/></label>
    <label>Price: <input type = "number" name="price"/></label>
    <input type="submit">
</form>


</body>
</html>
