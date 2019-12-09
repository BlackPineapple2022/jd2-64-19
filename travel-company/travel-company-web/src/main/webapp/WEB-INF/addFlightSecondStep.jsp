<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add Flight</title>
</head>
<body>

<h2>Аэропорт начала маршрута:</h2>
<h2>${airportOrigin}</h2>
<tr>Давайте выберем аэропорт назначения:</tr>
<form method="post" action="${pageContext.request.contextPath}/addFlight">

    <select name="airportDestination" >
        <c:forEach items="${allDestinationAirports}" var="airport">
            <option value = "${airport.code}">${airport}</option>
        </c:forEach>
    </select>
    <br>
    <select name = "airlines" >
        <option value = "RY">Ryanair</option>
        <option value = "WIZZ">Wizzair</option>
    </select>
    <br>

    <tr>Введите дату и время отправления в формате:гггг-мм-дд-чч-мм</tr>
    <label> <input type="text" name="arriveTime"/> </label>

    <tr>Введите дату и время прибытия в формате:гггг-мм-вв-чч-мм</tr>
    <label> <input type="text" name="departureTime"/> </label>

    <tr>Введите цену билета </tr>
    <label> <input type="text" name="ticketPrice"/> </label>

    <tr>Введите № рейса </tr>
    <label> <input type="text" name="flightNumber"/> </label>

    <input type = "submit">

</form>

</body>
</html>
