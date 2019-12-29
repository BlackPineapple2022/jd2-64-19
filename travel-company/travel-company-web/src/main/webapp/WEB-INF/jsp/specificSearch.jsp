<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | <fmt:message key="login.page.title"/></title>
</head>
<body>
<%@include file="include/header.jsp" %>
<form method="POST" action="${pageContext.request.contextPath}/spec">
    <p align="center"><fmt:message key="searh.page.title"/></p>


    <div style="text-align: center; font-size: 20px">
        Откуда полетим?
    </div>

    <table align="center">

        <tr>
            <td><input type="checkbox" name="originDirectIsVNO" value="Y"/></td>
            <td>Вильнюс аэропорт</td>
        </tr>

        <tr>
            <td><input type="checkbox" name="originDirectIsKUN" value="Y"/></td>
            <td>Каунас аэропорт</td>
        </tr>

        <tr>
            <td><input type="checkbox" name="originDirectIsWMI" value="Y"/></td>
            <td>Варшава аэропорт Модлен</td>
        </tr>

        <tr>
            <td><input type="checkbox" name="originDirectIsWAW" value="Y"/></td>
            <td>Варшава аэропорт им. Ф. Шопена</td>
        </tr>
    </table>

    <br>

    <div style="text-align: center; font-size: 20px">
        Куда полетим?
    </div>

    <div style="text-align: center">
        <select name="airportDestinationDirect1">

            <c:forEach items="${allStartedAirports}" var="airport">
                <option value="${airport.code}">${airport}</option>
            </c:forEach>

        </select>
    </div>

    <br>

    <div style="text-align: center; font-size: 20px">
        и ещё куда полетим?
    </div>

    <div style="text-align: center">
        <select name="airportDestinationDirect2">

            <c:forEach items="${allStartedAirports}" var="airport">
                <option value="${airport.code}">${airport}</option>
            </c:forEach>

        </select>
    </div>

    <br>

    <div style="text-align: center; font-size: 20px">
        и ещё?
    </div>

    <div style="text-align: center">
        <select name="airportDestinationDirect3">

            <c:forEach items="${allStartedAirports}" var="airport">
                <option value="${airport.code}">${airport}</option>
            </c:forEach>

        </select>
    </div>

    <br>

    <div style="text-align: center; font-size: 20px">
        Откуда полетим обратно?
    </div>

    <div style="text-align: center">
        <select name="airportDestinationReturn1">

            <c:forEach items="${allStartedAirports}" var="airport">
                <option value="${airport.code}">${airport}</option>
            </c:forEach>

        </select>
    </div>

    <br>

    <div style="text-align: center; font-size: 20px">
       и ещё откуда обратно полетим?
    </div>

    <div style="text-align: center">
        <select name="airportDestinationReturn2">

            <c:forEach items="${allStartedAirports}" var="airport">
                <option value="${airport.code}">${airport}</option>
            </c:forEach>

        </select>
    </div>

    <br>

    <div style="text-align: center; font-size: 20px">
        и ещё?
    </div>

    <div style="text-align: center">
        <select name="airportDestinationReturn3">

            <c:forEach items="${allStartedAirports}" var="airport">
                <option value="${airport.code}">${airport}</option>
            </c:forEach>

        </select>
    </div>

    <div style="text-align: center; font-size: 20px">
        Куда будем возвращаться?
    </div>

    <table align="center">

        <tr>
            <td><input type="checkbox" name="originReturnIsVNO" value="Y"/></td>
            <td>Вильнюс аэропорт</td>
        </tr>

        <tr>
            <td><input type="checkbox" name="originReturnIsKUN" value="Y"/></td>
            <td>Каунас аэропорт</td>
        </tr>

        <tr>
            <td><input type="checkbox" name="originReturnIsWMI" value="Y"/></td>
            <td>Варшава аэропорт Модлен</td>
        </tr>

        <tr>
            <td><input type="checkbox" name="originReturnIsWAW" value="Y"/></td>
            <td>Варшава аэропорт им. Ф. Шопена</td>
        </tr>

        <tr>
            <td colspan="2">Введите дату начала поиска в формате гггг-мм-дд</td>
        </tr>
        <tr>
            <td colspan="2"><label> <input type="text" name = "startingDate" ></label></td>
        </tr>

        <tr>
            <td colspan="2">Сколько дней искать</td>
        </tr>
        <tr>
            <td colspan="2"><label> <input type="text" name = "dayCount" ></label></td>
        </tr>

        <tr>
            <td colspan="2">Минимальная длительность путешествия</td>
        </tr>
        <tr>
            <td colspan="2"><label> <input type="text" name = "minDay" ></label></td>
        </tr>

        <tr>
            <td colspan="2">Максимальная длительность путешествия</td>
        </tr>
        <tr>
            <td colspan="2"><label> <input type="text" name = "maxDay" ></label></td>
        </tr>



    </table>
<div style="text-align: center">
    <input type="submit" value="Искать"/>
</div>





</form>

<%@include file="include/footer.jsp" %>
</body>
</html>
