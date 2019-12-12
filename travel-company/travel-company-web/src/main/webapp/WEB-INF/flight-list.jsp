<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Flight list</title>
</head>
<body>
<table>

<tr>

    <td>Id</td>
    <td>Origin</td>
    <td>Destination</td>
    <td>Arrive time</td>
    <td>Departure time</td>
    <td>Airline</td>
    <td>Ticket price EUR</td>
    <td>Flight No</td>

</tr>

<c:forEach items="${flightList}" var="flight">

    <tr>
    <td><c:out value="${flight.id}"/></td>
    <td><c:out value="${flight.originAirport}"/></td>
    <td><c:out value="${flight.destinationAirport}"/></td>
    <td><c:out value="${flight.arriveTime}"/></td>
    <td><c:out value="${flight.departureTime}"/></td>
    <td><c:out value="${flight.airline}"/></td>
    <td><c:out value="${flight.ticketPrice}"/></td>
    <td><c:out value="${flight.flightNumber}"/></td>
    </tr>

</c:forEach>

    </table>
    </body>
    </html>
