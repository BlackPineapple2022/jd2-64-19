<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Product List</title>
</head>

<body>

<%= request.getAttribute("productList")%>

<table>
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Price</td>
    </tr>

    <c: forEach
    <tr>
        <td>1</td>
        <td>Name_1</td>
        <td>10.0</td>
    </tr>

    <tr>
        <td>2</td>
        <td>Name_2</td>
        <td>20.0</td>
    </tr>


</table>


</body>

</html>
