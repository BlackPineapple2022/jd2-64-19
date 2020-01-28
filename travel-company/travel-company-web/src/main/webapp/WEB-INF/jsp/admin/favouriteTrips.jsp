<%@ page import="by.academy.it.travelcompany.user.favourite.Favourite" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | favourite Trips</title>
</head>

<body>
<%@include file="include/adminheader.jsp" %>


<br>
<div align="center" style="font-size: 30px"><b>FAVOURITE TRIP:</b></div>
<br>

<c:forEach items="${favouriteTripMap}" var="entry">
    <br>
    <br>
    <div align="center" style="font-size: 20px"><b><c:out value="${entry.key}"/>
        <form method="POST" action="${pageContext.request.contextPath}/admin/favourite/deleteFavourite">

        <input type="hidden" name = "userId" value = "${user.id}">
        <input type="hidden" name = "favouriteName" value = "${entry.key}">

        <input type="submit" value="DELETE FAVOURITE LIST"/>
    </form>
        <br></b></div>
    <c:forEach items="${entry.value}" var="trip">
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
                    <c:out value="${trip.flights[1].flightNumber}"/>
                </td>
                <td>|</td>

            </tr>

            <tr>
                <td>|</td>
                <td ><b>TOTAL PRICE:</b></td>
                <td>|</td>
                <td colspan="5"><div style="color: green; font-weight: bold"><c:out value="${trip.price}"/> EUR </div></td>
                <td>|</td>
            </tr>


            <tr>
                <td>|</td>
                <td colspan="2"> <b>Last update: "${trip.flights[0].checkedTime}"</b></td>
                <td colspan="4">
                    <form method="POST" action="${pageContext.request.contextPath}/admin/favourite/updateTrip">
                        <input type="hidden" name = "tripId" value = "${trip.id}">
                        <input type="submit" value="UPDATE"/>
                    </form>

                </td>
                <td>
                <div align="right">
                    <form method="POST" action="${pageContext.request.contextPath}/admin/favourite/deleteTrip">

                    <input type="hidden" name = "tripId" value = "${trip.id}">
                    <input type="hidden" name = "favouriteName" value = "${entry.key}">
                    <input type="submit" value="DELETE"/>
                </form>
                </div>
                </td>
                <td>|</td>

            </tr>

        </table>

    </c:forEach>
    <br>
    <div style="background: #000000; height:2px"></div>
</c:forEach>




<%@include file="include/adminfooter.jsp" %>

<iframe name="iframe1" style="position: absolute; left: -9999px;"></iframe>
</body>

</html>