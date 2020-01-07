<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | <fmt:message key="search.page.title"/></title>

    <style>
        p {
            margin-top: 0px; /* Отступ сверху */
            margin-bottom: 5px; /* Отступ снизу */
        }
    </style>

</head>
<body>
<%@include file="include/header.jsp" %>
<%@include file="include/menu.jsp" %>
<br>
<p align="center"><font color="red"><fmt:message key="search.page.warning"/></font></p>
<br>

<form method="POST" action="${pageContext.request.contextPath}/spec">

    <table align="center" border="0px" bordercolor="grey">
        <tr>
            <td colspan="2">
                <b>ПРЯМОЙ МАРШРУТ:</b>
            </td>


            <td colspan="2">
                <b>ОБРАТНЫЙ МАРШРУТ:</b>
            </td>
        </tr>

        <tr>


            <td colspan="2">
                Откуда будем стартовать?
            </td>


            <td colspan="2">
                Куда будем возвращаться?
            </td>


        </tr>

        <tr>

            <td colspan="2">
                <input type="checkbox" name="originDirectIsVNO" value="Y"/> Вильнюс аэропорт<br>
                <input type="checkbox" name="originDirectIsKUN" value="Y"/> Каунас аэропорт<br>
                <input type="checkbox" name="originDirectIsWMI" value="Y"/> Варшава аэропорт Модлен<br>
                <input type="checkbox" name="originDirectIsWAW" value="Y"/> Варшава аэропорт им. Ф. Шопена<br>
            </td>

            <td colspan="2">
                <input type="checkbox" name="originReturnIsVNO" value="Y"/> Вильнюс аэропорт<br>
                <input type="checkbox" name="originReturnIsKUN" value="Y"/> Каунас аэропорт<br>
                <input type="checkbox" name="originReturnIsWMI" value="Y"/> Варшава аэропорт Модлен<br>
                <input type="checkbox" name="originReturnIsWAW" value="Y"/> Варшава аэропорт им. Ф. Шопена<br>
            </td>

        </tr>
        <tr>
            <td colspan="4"><br></td>
        </tr>
        <tr>
            <td colspan="4">Укажите дополнительные условия:</td>
        </tr>

        <tr>
            <td colspan="4">
                <input name="startingFilter" type="radio" value="noFilter"> Без ограничений
            </td>
        </tr>

        <tr>
            <td colspan="4">
                <input name="startingFilter" type="radio" value="countryFilter"> Обратно в ту же страну
            </td>
        </tr>

        <tr>
            <td colspan="4">
                <input name="startingFilter" type="radio" value="cityFilter"> Обратно в тот же город
            </td>
        </tr>

        <tr>
            <td colspan="4">
                <input name="startingFilter" type="radio" value="airportFilter"> Обратно в тот же аэропорт
            </td>
        </tr>

        <tr>
            <td colspan="4"><br></td>
        </tr>

        <tr>

            <td colspan="2">
                Куда полетим?
            </td>

            <td colspan="2">
                Откуда будем возвращаться?
            </td>

        </tr>

        <tr>
            <td valign="top">

                <button type="button" onclick="airportDirect()">+</button>

            </td>
            <td>
                <select name="airportDestinationDirect1">
                    <c:forEach items="${allStartedAirports}" var="airport">
                        <option value="${airport.code}">${airport}</option>
                    </c:forEach>
                </select>
            </td>
            <td valign="top">

                <button type="button" onclick="airportReturn()">+</button>

            </td>
            <td>
                <select name="airportDestinationReturn1">

                    <c:forEach items="${allStartedAirports}" var="airport">
                        <option value="${airport.code}">${airport}</option>
                    </c:forEach>

                </select>
            </td>
        </tr>
        <tr>
            <td>

            </td>
            <td valign="top">
                <div id="direct" style="border: 0px">
                </div>
            </td>
            <td>

            </td>

            <td valign="top">
                <div id="return" style="border: 0px">
                </div>
            </td>


        </tr>

        <tr>
            <td colspan="4"><br></td>
        </tr>
        <tr>
            <td colspan="4">Укажите дополнительные условия:</td>
        </tr>

        <tr>
            <td colspan="4">
                <input name="endingFilter" type="radio" value="noFilter"> Без ограничений
            </td>
        </tr>

        <tr>
            <td colspan="4">
                <input name="endingFilter" type="radio" value="countryFilter"> Прилёт и вылет из той же страны
            </td>
        </tr>

        <tr>
            <td colspan="4">
                <input name="endingFilter" type="radio" value="cityFilter"> Прилёт и вылет из того же города
            </td>
        </tr>

        <tr>
            <td colspan="4">
                <input name="endingFilter" type="radio" value="airportFilter"> Прилёт и вылет из того же аэропорта
            </td>
        </tr>

        <tr>
            <td colspan="4"><br></td>
        </tr>

        <tr>
            <td colspan="2">Введите дату начала поиска в формате ГГГГ-ММ-ДД</td>
            <td colspan="2"><label> <input type="text" name="startingDate"></label></td>
        </tr>
        <tr>

        </tr>

        <tr>
            <td colspan="2">Укажите глубину поиска (дней)</td>
            <td colspan="2"><label> <input type="text" name="dayCount"></label></td>
        </tr>

        <tr>
            <td colspan="2">Минимальная длительность путешествия</td>
            <td colspan="2"><label> <input type="text" name="minDay"></label></td>

        </tr>
        <tr>
            <td colspan="2">Максимальная длительность путешествия</td>
            <td colspan="2"><label> <input type="text" name="maxDay"></label></td>
        </tr>

        <tr>
            <td colspan="4">
                <div style="text-align: center">
                    <input type="submit" value="Искать" onclick="messageWarning()"/>
                </div>
            </td>
        </tr>
    </table>


</form>

<%@include file="include/footer.jsp" %>


<script>
    schotD = 1;

    function airportDirect() {
        schotD++;
        if (schotD < 4) {
            document.getElementById("direct").innerHTML += '<p><select name="airportDestinationDirect' + schotD + '"> <c:forEach items="${allStartedAirports}" var="airport">\n' +
                '                            <option value="${airport.code}">${airport}</option>\n' +
                '                        </c:forEach> </select></p>'
        } else {
            alert("Больше трёх нельзя")
        }
    }

    schotR = 1;

    function airportReturn() {
        schotR++;
        if (schotR < 4) {
            document.getElementById("return").innerHTML += '<p><select name="airportDestinationReturn' + schotR + '"> <c:forEach items="${allStartedAirports}" var="airport">\n' +
                '                            <option value="${airport.code}">${airport}</option>\n' +
                '                        </c:forEach> </select></p>'
        } else {
            alert("Больше трёх нельзя")
        }
    }

    function messageWarning() {
        alert("Пожалуйста, не обновляйте страницу, дождитесь обработки запроса!")
    }

</script>
</body>
</html>
