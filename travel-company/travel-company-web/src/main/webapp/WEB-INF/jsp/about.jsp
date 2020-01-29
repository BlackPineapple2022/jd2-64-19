<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | О проекте</title>
</head>

<body>
<style>
td {
padding: 10px;
padding-left: 20px;
}
</style>


<%@include file="include/header.jsp" %>
<%@include file="include/menu.jsp" %>

<div style="font-size: 40px" align="center"><br>Добро пожаловать на <b>BLACKPINEAPPLE.BY</b></div>

<table align="center">
    <tr>
        <td width="300px"><img src="${pageContext.request.contextPath}/resources/pic/What.png" width="300px"></td>
        <td colspan="2" width="300px" align="justify"><div style="font-size: 40px">Что такое лоукосты?</div><br>
            <div style="font-size: 20px">
                Лоукосты или лоукостеры - это авиакомпании, которые продают билеты на свои рейсы за суммы, которые в разы, а то и в десятки меньше, чем у основных национальных авиаперевозчиков.
                Лоукосты существуют в Европе, Азии, Америке.
                Лоукосты как правило продают билеты без багажа, питания и возможности выбора места.
                Эти и другие услуги оказываются за дополнительную плату.
                Основные лоукост компании в Европе - это <b>RYANAIR</b> и <b>WIZZAIR</b>.
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2" width="300px" align="justify"><div style="font-size: 40px">Что такое Чёрный Ананас?</div><br>
            <div style="font-size: 20px">
                Чёрный ананас - это мощный сервис по планированию авиопутешествий по Европе для Беларусов.
                К сожалению, вылеты из Минска слишком дорогие, да и добраться можно не так много куда.
                Если Вы хоть раз заказывали такси в Вильнюсе до аэропорта, то слышали местную шутку:
                <b>"Вам в аэропорт МИНСК-3?"</b> Действительно, половина пассажиров в Вильсе - это Беларусы.
                Аналогичная ситуация в аэропорте Каунаса и аэропортах Варшавы.<br>
                Здесь Вы можете искать и сравнивать цены одновременно на вылеты из аэропорта Вильнюса, Каунаса и
                обоих аэропортов Варшавы: имени Фридерика Шопена и Модлин. Сохранять Ваши поиски, обновлять цены
                прямо из личного кабинета и многое другое.
            </div>
        </td>
        <td width="300px"><img src="${pageContext.request.contextPath}/resources/pic/pineapple_about.png" width="300px"></td>
    </tr>

    <tr>
        <td width="300px" colspan="3">
            <img src="${pageContext.request.contextPath}/resources/pic/people.jpg" width="900px">
        </td>
    </tr>>




</table>

<%@include file="include/footer.jsp" %>
</body>

</html>