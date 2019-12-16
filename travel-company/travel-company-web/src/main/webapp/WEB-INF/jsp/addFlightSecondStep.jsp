<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale scope="session" value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>BlackPineapple.by | <fmt:message key="addFlight.page.title"/></title>
</head>
<body>
<%@include file="include/header.jsp" %>

<h2 align="center"><fmt:message key="addFlight.page.content.header2"/>: ${airportOrigin}</h2>
<p align="center"><fmt:message key="addFlight.page.destination"/>: </p>

<form method="post" action="${pageContext.request.contextPath}/addFlight">

    <input type="hidden" name="airportOrigin" value="${airportOrigin.code}">

    <table align="center">
        <tr>
            <td>
                <select name="airportDestination">
                    <c:forEach items="${allDestinationAirports}" var="airport">
                        <option value="${airport.code}">${airport}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="airlines">
                    <option value="RY">Ryanair</option>
                    <option value="WIZZ">Wizzair</option>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                Введите дату и время отправления в формате:гггг-мм-дд-чч-мм
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <label> <input type="text" name="arriveTime"/> </label>
            </td>
        </tr>


        <tr>
            <td colspan="2">
            Введите дату и время прибытия в формате:гггг-мм-вв-чч-мм
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <label> <input type="text" name="departureTime"/> </label>
            </td>
        </tr>

        <tr>
            <td>
                Введите цену билета
            </td>
            <td>
                <label> <input type="text" name="ticketPrice"/> </label>
            </td>
        </tr>

        <tr>
            <td>
                Введите № рейса
            </td>
            <td>
                <label> <input type="text" name="flightNumber"/> </label>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input style="align-content: center" type="submit" value="<fmt:message key="addFlight.page.submit"/>"/>
                </a>
            </td>
        </tr>

        </table>
</form>


<%@include file="include/footer.jsp" %>
</body>

</html>
