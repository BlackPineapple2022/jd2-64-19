<%--
<%@ page buffer="8192kb" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | manual scanner result search</title>
</head>

<body>



<table align="center">
    <tr>
        <td colspan="2"><b>РЕЗУЛЬТАТЫ ПОИСКА:</b></td>
    </tr>
    <c:forEach items="${trips}" var="trip">
        <tr>
            <td colspan="2">Прямой маршрут:</td>
            <td colspan="2">Обратный маршрут:</td>
        </tr>

        <tr>
            <td>Аэропорт вылета:</td>
            <td>
                <c:out value="${trip.flights[0].routeMap.originAirport.country}"/>
            </td>

            <td>Аэропорт вылета:</td>
            <td>
                <c:out value="${trip.flights[1].routeMap.originAirport.country}"/>
            </td>
        </tr>
        <tr>
            <td>

            </td>
            <td>
                <c:out value="${trip.flights[0].routeMap.originAirport.city}"/>
            </td>
            <td>

            </td>
            <td>
                <c:out value="${trip.flights[1].routeMap.originAirport.city}"/>
            </td>
        </tr>
        <tr>
            <td>

            </td>
            <td>
                <c:out value="${trip.flights[0].routeMap.originAirport.code}"/>
            </td>
            <td>

            </td>
            <td>
                <c:out value="${trip.flights[1].routeMap.originAirport.code}"/>
            </td>
        </tr>

        <tr>
            <td>Аэропорт прилёта:</td>
            <td>
                <c:out value="${trip.flights[0].routeMap.destinationAirport.country}"/>
            </td>
            <td>Аэропорт прилёта:</td>
            <td>
                <c:out value="${trip.flights[1].routeMap.destinationAirport.country}"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <c:out value="${trip.flights[0].routeMap.destinationAirport.city}"/>
            </td>
            <td></td>
            <td>
                <c:out value="${trip.flights[1].routeMap.destinationAirport.city}"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <c:out value="${trip.flights[0].routeMap.destinationAirport.code}"/>
            </td>
            <td></td>
            <td>
                <c:out value="${trip.flights[1].routeMap.destinationAirport.code}"/>
            </td>
        </tr>
        <tr>
            <td><c:out value="${trip.flights[0].currency.currencyCode}"/></td>
            <td>
                <c:out value="${trip.flights[0].ticketPrice}"/>
            </td>
            <td><c:out value="${trip.flights[1].currency.currencyCode}"/></td>
            <td>
                <c:out value="${trip.flights[1].ticketPrice}"/>
            </td>
        </tr>
        <tr>
            <td>Авиакомпания</td>
            <td>
                <c:out value="${trip.flights[0].routeMap.airline.airlineName}"/>
            </td>
            <td>Авиакомпания</td>
            <td>
                <c:out value="${trip.flights[1].routeMap.airline.airlineName}"/>
            </td>
        </tr>
        <tr>
            <td>Время вылета</td>
            <td>
                <c:out value="${trip.flights[0].departureTime}"/>
            </td>
            <td>Время вылета</td>
            <td>
                <c:out value="${trip.flights[1].departureTime}"/>
            </td>
        </tr>

        <tr>
            <td>Время прилёта</td>
            <td>
                <c:out value="${trip.flights[0].arriveTime}"/>
            </td>
            <td>Время прилёта</td>
            <td>
                <c:out value="${trip.flights[1].arriveTime}"/>
            </td>
        </tr>

        <tr>
            <td>Номер рейса</td>
            <td>
                <c:out value="${trip.flights[0].flightNumber}"/>
            </td>
            <td>Номер рейса</td>
            <td>
                <c:out value="${trip.flights[0].flightNumber}"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">ИТОГОВАЯ ЦЕНА EUR:</td>
            <td colspan="2"><b><c:out value="${trip.price}"/></b></td>
        </tr>
        <tr>
            <td colspan="4">
                <br>
                <br>
            </td>
        </tr>





    </c:forEach>
</table>


<%@include file="include/footer.jsp" %>
</body>

</html>--%>
