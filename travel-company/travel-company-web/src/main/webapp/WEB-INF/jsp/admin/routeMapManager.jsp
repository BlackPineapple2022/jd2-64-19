<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | routeMap Manager </title>
</head>

<body>

<style type="text/css">
    .id {
        width: 40px;
    }

    .airportCode {
        width: 100px;
    }

    .airline {
        width: 100px;
    }

    .direction {
        width: 100px;
    }


</style>
<%@include file="include/adminheader.jsp" %>

<div align="center" style="font-size: 30px"><b>ROUTEMAP MANAGER</b>
    </div>

<table align="center" style="text-align: center">

    <tr>
        <td></td>
        <td colspan="19">__ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __
            __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __
        </td>
        <td></td>
    </tr>
    <tr>
        <td>|</td>
        <td colspan="19">
            <b>
                LIST OF ROUTEMAP:
            </b>
        </td>
        <td>|</td>
    </tr>

    <tr>
        <td>|</td>
        <td>RouteMap id</td>
        <td>|</td>
        <td>Origin airport code</td>
        <td>|</td>
        <td>Origin airport country</td>
        <td>|</td>
        <td>Origin airport city</td>
        <td>|</td>
        <td>Destination airport code</td>
        <td>|</td>
        <td>Destination airport country</td>
        <td>|</td>
        <td>Destination airport city</td>
        <td>|</td>
        <td>Airline</td>
        <td>|</td>
        <td>Direction</td>
        <td>|</td>
        <td>Delete?</td>
        <td>|</td>

    </tr>

    <c:forEach items="${routeMapList}" var="routeMap">
        <tr>
            <td> |</td>
            <td><c:out value="${routeMap.id}"/></td>
            <td> |</td>
            <td><c:out value="${routeMap.originAirport.code}"/></td>
            <td> |</td>
            <td><c:out value="${routeMap.originAirport.country}"/></td>
            <td> |</td>
            <td><c:out value="${routeMap.originAirport.city}"/></td>
            <td> |</td>
            <td><c:out value="${routeMap.destinationAirport.code}"/></td>
            <td> |</td>
            <td><c:out value="${routeMap.destinationAirport.country}"/></td>
            <td> |</td>
            <td><c:out value="${routeMap.destinationAirport.city}"/></td>
            <td> |</td>
            <td><c:out value="${routeMap.airline.airlineName}"/></td>
            <td> |</td>
            <td><c:out value="${routeMap.direction.directionName}"/></td>
            <td> |</td>
            <td><a href="${pageContext.request.contextPath}/admin/manager/routemap/delete?id=${routeMap.id}">[x]</a>
            </td>
            <td> |</td>
        </tr>
    </c:forEach>

    <tr>
        <td>|</td>
        <td colspan="19">

        </td>
        <td>|</td>
    </tr>

    <tr>
        <td> |</td>
        <td colspan="19">
            <b>ADD ROUTEMAP TO BASE</b>
        </td>
        <td> |</td>
    </tr>

    <tr>
        <td> |</td>
        <td colspan="19">

            <form method="POST" action="${pageContext.request.contextPath}/admin/manager/routemap/add">
                Origin airport code:
                <input type="text" name="originAirportCode" class="airportCode" value=""/>
                Destination airport code:
                <input type="text" name="destinationAirportCode" class="airportCode" value=""/>
                Airline:
                <input type="text" name="airlineName" class="airline" value=""/>
                Direction:
                <input type="text" name="directionName" class="direction" value=""/>
                <input type="submit" value="add"/>
            </form>


        </td>
        <td> |</td>
    </tr>
    <tr>
        <td>|</td>
        <td colspan="19">

        </td>
        <td>|</td>
    </tr>
    <tr>
        <td> |</td>
        <td colspan="19">
            <b>UPDATE ROUTEMAP IN BASE</b>
        </td>
        <td> |</td>
    </tr>

    <tr>
        <td> |</td>
        <td colspan="19">

            <form method="POST" action="${pageContext.request.contextPath}/admin/manager/routemap/update">
                Id:
                <input type="text" name="id" class="id" value=""/>
                Origin airport code:
                <input type="text" name="originAirportCode" class="airportCode" value=""/>
                Destination airport code:
                <input type="text" name="destinationAirportCode" class="airportCode" value=""/>
                Airline:
                <input type="text" name="airlineName" class="airline" value=""/>
                Direction:
                <input type="text" name="directionName" class="direction" value=""/>
                <input type="submit" value="update"/>
            </form>


        </td>
        <td> |</td>
    </tr>

    <tr>
        <td>|</td>
        <td colspan="19">__ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __
            __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ @routeMap manager
        </td>
        <td>|</td>
    </tr>

    <tr>
        <td colspan="21" style="text-align: left">For installing all routeMap to base:</td>
    </tr>
    <tr>
        <td colspan="21" style="text-align: left">Input "INSTALL" into field and press button</td>
    </tr>
    <tr>
        <td colspan="21" style="text-align: left;color: red">All routeMap in base will be removed and installed
            default
        </td>
    </tr>

    <tr>
        <td colspan="21" style="text-align: left">
            <form method="POST" action="${pageContext.request.contextPath}/admin/manager/routemap/install">
                <input type="text" name="install" value=""/>
                <input type="submit" value="install"/>
                @danger
            </form>
        </td>
    </tr>


</table>

<%@include file="include/adminfooter.jsp" %>
</body>

</html>