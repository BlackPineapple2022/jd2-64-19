<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add Flight</title>
</head>
<body>

<h2>Укажите аэропорт начала путешествия</h2>
<form method="post" action="${pageContext.request.contextPath}/addFlight">
    <select name="airportOrigin" >

        <c:forEach items="${allAirport}" var="airport">
        <option value = "${airport.code}">${airport}</option>
        </c:forEach>

    </select>
<input type = "submit">
</form>
</body>
</html>
