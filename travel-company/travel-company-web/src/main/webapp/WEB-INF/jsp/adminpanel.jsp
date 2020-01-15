<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | <fmt:message key="home.page.title" /></title>
</head>
<a href="${pageContext.request.contextPath}/admin/install/addAllAirportToBase"> Добавить все аэропорты в базу данных </a>
<a href="${pageContext.request.contextPath}/admin/install/addAllRouteMapToBase"> Добавить все маршруты в базу данных </a>



<form method="POST" action="${pageContext.request.contextPath}/admin/scanner/startFlightScannerRY">
    <table align="center" border="0" bord >
        <tr>
            <td>origin</td>
            <td><input type="text" name="origin" value=""/></td>
        </tr>
        <tr>
            <td>destination</td>
            <td><input type="text" name="destination" value=""/></td>
        </tr>
        <tr>
            <td>days</td>
            <td><input type="text" name="dayCount" value=""/></td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="<fmt:message key="login.page.submit"/>"/>
            </td>
        </tr>

    </table>
</form>
<a href="${pageContext.request.contextPath}/admin/scanner/startFlightScannerRY?origin=WMI&destination=CGN&dayCount=100"> Запустить сканнер </a>


<body>
<%@include file="include/header.jsp" %>
<%@include file="include/menu.jsp" %>
<%@include file="include/footer.jsp" %>
</body>

</html>