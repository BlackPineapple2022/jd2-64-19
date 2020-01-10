<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<html >
<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by </title>
</head>
<body>
<%@include file="include/header.jsp" %>
<%@include file="include/menu.jsp" %>

<form method="POST" action="${pageContext.request.contextPath}/admin/updateAirport">
    <p align="center">Update airport</p>
    <table align="center" border="0" bord >
        <tr>
            <td>Airport ID</td>
            <td><input type="text" name="id" value=""/></td>
        </tr>
        <tr>
            <td>Airport code</td>
            <td><input type="text" name="airportCode" value=""/></td>
        </tr>
        <tr>
            <td>Country</td>
            <td><input type="text" name="country" value=""/></td>
        </tr>
        <tr>
            <td>City</td>
            <td><input type="text" name="city" value=""/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="add"/>
            </td>
        </tr>

    </table>
</form>


<%@include file="include/footer.jsp" %>
</body>
</html>
