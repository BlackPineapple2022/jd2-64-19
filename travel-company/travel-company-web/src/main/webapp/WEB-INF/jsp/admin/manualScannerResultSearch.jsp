<%@ page buffer="8192kb" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | manual Scanner Result Search</title>
</head>

<body>
<%@include file="include/adminheader.jsp" %>


<br>
<div align="center" style="font-size: 30px"><b>MANUAL TRIP SCANNER SEARCH RESULT:</b></div>
<br>
<c:forEach items="${trips}" var="trip">
    <table align="center">

        <tr>
            <td></td>
            <td colspan="7">__ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __
                __ __ __ __
            </td>
            <td></td>

        </tr>
        <tr>
            <td>|</td>
            <td colspan="3">Direct way:</td>
            <td>|</td>
            <td colspan="3">Return way:</td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td>Origin airport:</td>
            <td>|</td>
            <td>
                <c:out value="${trip.flights[0].routeMap.originAirport.country}"/>
            </td>
            <td>|</td>
            <td>Origin airport:</td>
            <td>|</td>
            <td>
                <c:out value="${trip.flights[1].routeMap.originAirport.country}"/>
            </td>
            <td>|</td>
        </tr>
        <tr>
            <td>|</td>
            <td>

            </td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[0].routeMap.originAirport.city}"/>
            </td>
            <td>|</td>

            <td>

            </td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[1].routeMap.originAirport.city}"/>
            </td>
            <td>|</td>

        </tr>
        <tr>
            <td>|</td>

            <td>

            </td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[0].routeMap.originAirport.code}"/>
            </td>
            <td>|</td>

            <td>

            </td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[1].routeMap.originAirport.code}"/>
            </td>
            <td>|</td>

        </tr>

        <tr>
            <td>|</td>

            <td>Destination airport:</td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[0].routeMap.destinationAirport.country}"/>
            </td>
            <td>|</td>

            <td>Destination airport:</td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[1].routeMap.destinationAirport.country}"/>
            </td>
            <td>|</td>

        </tr>
        <tr>
            <td>|</td>

            <td></td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[0].routeMap.destinationAirport.city}"/>
            </td>
            <td>|</td>

            <td></td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[1].routeMap.destinationAirport.city}"/>
            </td>
            <td>|</td>

        </tr>
        <tr>
            <td>|</td>

            <td></td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[0].routeMap.destinationAirport.code}"/>
            </td>
            <td>|</td>

            <td></td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[1].routeMap.destinationAirport.code}"/>
            </td>
            <td>|</td>

        </tr>
        <tr>
            <td>|</td>

            <td><c:out value="${trip.flights[0].currency.currencyCode}"/></td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[0].ticketPrice}"/>
            </td>
            <td>|</td>

            <td><c:out value="${trip.flights[1].currency.currencyCode}"/></td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[1].ticketPrice}"/>
            </td>
            <td>|</td>

        </tr>
        <tr>
            <td>|</td>

            <td>АirLines</td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[0].routeMap.airline.airlineName}"/>
            </td>
            <td>|</td>

            <td>AirLines</td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[1].routeMap.airline.airlineName}"/>
            </td>
            <td>|</td>

        </tr>
        <tr>
            <td>|</td>

            <td>Departure time</td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[0].departureTime}"/>
            </td>
            <td>|</td>

            <td>Departure Time</td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[1].departureTime}"/>
            </td>
            <td>|</td>

        </tr>

        <tr>
            <td>|</td>

            <td>Arrive Time</td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[0].arriveTime}"/>
            </td>
            <td>|</td>

            <td>Arrive Time</td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[1].arriveTime}"/>
            </td>
            <td>|</td>

        </tr>

        <tr>
            <td>|</td>

            <td>Flight number</td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[0].flightNumber}"/>
            </td>
            <td>|</td>

            <td>Flight number</td>
            <td>|</td>

            <td>
                <c:out value="${trip.flights[0].flightNumber}"/>
            </td>
            <td>|</td>

        </tr>

        <tr>
            <td>|</td>
            <td colspan="3"><b>TOTAL PRICE: </b></td>
            <td>|</td>
            <td colspan="3"><b><c:out value="${trip.price}"/></b></td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="7"> add to:
                <form method="POST" action="${pageContext.request.contextPath}/admin/favourite/add" target="iframe1">

                    <select name="favourite">
                        <c:forEach items="${favourites}" var="favourite">
                            <option value="${favourite}">${favourite}</option>

                        </c:forEach>
                    </select>
                    <input type="hidden" name = "tripId" value = "${trip.id}">
                    or create new list
                    <input type="text" name="newFavourite">
                    <input type="submit" value="+"/>
                </form>
            </td>
            <td>|</td>

        </tr>

    </table>
</c:forEach>

<%@include file="include/adminfooter.jsp" %>

<iframe name="iframe1" style="position: absolute; left: -9999px;"></iframe>
</body>

</html>