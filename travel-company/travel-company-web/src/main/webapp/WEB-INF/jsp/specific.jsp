<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | Поиск авиабилетов</title>


    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


    <style>

        td {
            width: 400px;
            margin: 5px;
            border: transparent 5px solid;
        }

        .welcome {
            font-family: 'Google Sans', Roboto, Arial, sans-serif;
            font-size: 40px;
            font-weight: 400;
            line-height: 1.5;
            word-break: break-word;
            word-wrap: break-word;
            color: #202124;
            text-align: center;
            margin: 20px;
        }

        table {
            margin-left: auto;
            margin-right: auto;

        }

        .label-1 {
            border: #dcdcdc solid 1px;
            border-radius: 5px;
            font-family: 'Google Sans', Roboto, Arial, sans-serif;
            font-size: 30px;
            font-weight: 400;
            line-height: 1.5;
            word-break: break-word;
            word-wrap: break-word;
            color: #111111;
            padding: 10px;
            background: #FAFAFA;
        }

        .small24 {
            font-size: 22px;
            color: #444444;
        }

        .list {
            width: 800px;
            height: 30px;
            font-size: 22px;
            font-family: 'Google Sans', Roboto, Arial, sans-serif;
            line-height: 1.5;
            color: #444444;
        }

        .my_button3 {
            outline: none;
            border: solid #ffffff 1px;
            border-radius: 5px;
            font-family: 'Google Sans', Roboto, Arial, sans-serif;
            color: #ffffff;
            font-size: 22px;
            background: #66A33B;
            padding: 6px 7px 6px 7px;
            text-decoration: none;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        .my_button3:hover {
            border: solid #66A33B 1px;
        }

        .my_button4 {
            outline: none;
            border: solid #ffffff 1px;
            border-radius: 5px;
            font-family: 'Google Sans', Roboto, Arial, sans-serif;
            color: #ffffff;
            font-size: 22px;
            background: #E55747;
            padding: 6px 7px 6px 7px;
            text-decoration: none;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        .my_button4:hover {
            border: solid #E55747 1px;

        }


    </style>

</head>

<body>

<%@include file="include/header.jsp" %>
<%@include file="include/menu.jsp" %>

<form method="POST" action="${pageContext.request.contextPath}/specificResult">

    <div class="welcome">
        Поиск авиабилетов
    </div>

    <table>
        <tr>
            <td>
                <div class="label-1">Вылет из:
                    <div class="small24">
                        <input type="checkbox" name="originDirectIsVNO" value="Y"/> Вильнюса<br/>
                        <input type="checkbox" name="originDirectIsKUN" value="Y"/> Каунаса<br/>
                        <input type="checkbox" name="originDirectIsWMI" value="Y"/> Варшавы Модлин<br/>
                        <input type="checkbox" name="originDirectIsWAW" value="Y"/> Варшавы Шопен<br/>
                    </div>
                </div>
            </td>

            <td>
                <div class="label-1">
                    Возврат в:
                    <div class="small24">
                        <input type="checkbox" name="originReturnIsVNO" value="Y"/> Вильнюс<br/>
                        <input type="checkbox" name="originReturnIsKUN" value="Y"/> Каунас<br/>
                        <input type="checkbox" name="originReturnIsWMI" value="Y"/> Варшаву Модлин<br/>
                        <input type="checkbox" name="originReturnIsWAW" value="Y"/> Варшаву Шопен<br/>
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div class="label-1">Дополнительные параметры поиска:
                    <div class="small24">
                        <input name="startingFilter" type="radio" value="noFilter" checked>
                        Без ограничений<br/>
                        <input name="startingFilter" type="radio" value="countryFilter">
                        Вылет и прилёт в одну страну<br/>
                        <input name="startingFilter" type="radio" value="cityFilter">
                        Вылет и прилёт в один город<br/>
                        <input name="startingFilter" type="radio" value="airportFilter">
                        Вылет и прилёт в один аэропорт<br/>
                    </div>
                </div>
            </td>
        </tr>

        <tr>

            <td colspan="2">

                <div class="label-1">Куда(не более 5-ти аэропортов):
                    <select name="airportDestinationDirect1" class="list">
                        <c:forEach items="${allStartedAirports}" var="airport">
                            <option value="${airport.code}">
                                    ${airport.country} | ${airport.city} | ${airport.code}
                            </option>
                        </c:forEach>
                    </select>

                    <div id="myDIV2" style="display: none">
                        <select id="select2" name="airportDestinationDirect2" class="list">
                            <option value="NONE" selected="selected">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} | ${airport.city} | ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="myDIV3" style="display: none">
                        <select id="select3" name="airportDestinationDirect3" class="list">
                            <option value="NONE" selected="selected">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} | ${airport.city} | ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="myDIV4" style="display: none">
                        <select id="select4" name="airportDestinationDirect4" class="list">
                            <option value="NONE" selected="selected">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} | ${airport.city} | ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="myDIV5" style="display: none">
                        <select id="select5" name="airportDestinationDirect5" class="list">
                            <option value="NONE" selected="selected">-
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} | ${airport.city} | ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <button class="my_button3" type="button" onclick="airportDirect()">
                        Добавить
                    </button>
                    <button class="my_button4" type="button" onclick="airportDirectDelete()"> Удалить </button>

                </div>

            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div class="label-1">Откуда(не более 5-ти аэропортов):
                    <select name="airportDestinationReturn1" class="list">
                        <c:forEach items="${allStartedAirports}" var="airport">
                            <option value="${airport.code}">
                                    ${airport.country} | ${airport.city} | ${airport.code}
                            </option>
                        </c:forEach>
                    </select>

                    <div id="myDIVR2" style="display: none">
                        <select id="selectR2" name="airportDestinationReturn2" class="list">
                            <option value="NONE" selected="selected">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} | ${airport.city} | ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="myDIVR3" style="display: none">
                        <select id="selectR3" name="airportDestinationReturn3" class="list">
                            <option value="NONE" selected="selected">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} | ${airport.city} | ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="myDIVR4" style="display: none">
                        <select id="selectR4" name="airportDestinationReturn4" class="list">
                            <option value="NONE" selected="selected">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} | ${airport.city} | ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="myDIVR5" style="display: none">
                        <select id="selectR5" name="airportDestinationReturn5" class="list">
                            <option value="NONE" selected="selected">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} | ${airport.city} | ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <button class="my_button3" type="button" onclick="airportReturn()">
                        Добавить
                    </button>
                    <button class="my_button4" type="button" onclick="airportReturnDelete()">
                       Удалить
                    </button>

                </div>
            </td>
        </tr>



        <tr>

            <td colspan="2">
                <div class="label-1">Дополнительные параметры:
                    <div class="small24">
                        <input name="endingFilter" type="radio" value="noFilter" checked>
                        Без ограничений <br/>
                        <input name="endingFilter" type="radio" value="countryFilter">
                        Прилёт и возврат из одной страны<br/>
                        <input name="endingFilter" type="radio" value="cityFilter">
                        Прилёт и возврат из из одного города<br/>
                        <input name="endingFilter" type="radio" value="airportFilter">
                        Прилёт и возврат из из одного аэропорта<br/>
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td>
                <div class="label-1">Самая ранняя дата начала путешествия
                    <div style="font-size: 16px">(Не позднее 300 дней от текущей даты):</div>
                    <input type="text" autocomplete="off" name="startingDate" id="datepicker"
                           style="font-family: 'Google Sans', Roboto, Arial, sans-serif; font-size: 22px; width: 380px">
                </div>
            </td>
            <td>
                <div class="label-1">Самая поздняя дата начала путешествия
                    <div style="font-size: 16px">(Не позднее 300 дней от текущей даты):</div>
                    <input type="text" autocomplete="off" name="endingDate" id="datepickerR"
                           style="font-family: 'Google Sans', Roboto, Arial, sans-serif; font-size: 22px; width: 380px">
                </div>
            </td>
        </tr>

        <tr>

            <td colspan="2">
                <div class="label-1">Количество дней в путешествии (от 2-х до 30-ти):
                <div class="small24">от <input type="text" name="minDay" height="30px"
                                               style=" width: 200px; height: 30px; font-size: 22px; font-family: 'Google Sans', Roboto, Arial, sans-serif">
                    до <input type="text" name="maxDay" height="30px"
                              style="width: 200px; height: 30px; font-size: 22px; font-family: 'Google Sans', Roboto, Arial, sans-serif">
                </div>
                </div>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <button type="submit" class="my_button3" style="margin-top: 0px; padding-left: 40px; padding-right: 40px">Поиск</button>
            </td>
        </tr>


    </table>

</form>


</body>

<script>

    schotD = 1;

    function airportDirect() {
        schotD++;
        if (schotD < 6) {
            var x = document.getElementById('myDIV' + schotD);
            if (x.style.display === "none") {
                x.style.display = "block";
                document.getElementById('select' + schotD).options[0].selected = true;
            } else {

                x.style.display = "none";
                document.getElementById('select' + schotD).options[0].selected = true;
            }
        } else {
            schotD = 5;
            alert("Хватит!")
        }
    }

    function airportDirectDelete() {
        if (schotD > 1) {
            var x = document.getElementById('myDIV' + schotD);
            if (x.style.display === "block") {
                x.style.display = "none";
                document.getElementById('select' + schotD).options[0].selected = true;
            } else {

                x.style.display = "none";
                document.getElementById('select' + schotD).options[0].selected = true;
            }
        } else {
            schotD = 2;
        }
        schotD--;
    }

    schotR = 1;

    function airportReturn() {
        schotR++;
        if (schotR < 6) {
            var x = document.getElementById('myDIVR' + schotR);
            if (x.style.display === "none") {
                x.style.display = "block";
                document.getElementById('selectR' + schotR).options[0].selected = true;
            } else {

                x.style.display = "none";
                document.getElementById('selectR' + schotR).options[0].selected = true;
            }
        } else {
            schotR = 5;
            alert("Хватит!")
        }
    }

    function airportReturnDelete() {
        if (schotR > 1) {
            var x = document.getElementById('myDIVR' + schotR);
            if (x.style.display === "block") {
                x.style.display = "none";
                document.getElementById('selectR' + schotR).options[0].selected = true;
            } else {

                x.style.display = "none";
                document.getElementById('selectR' + schotR).options[0].selected = true;
            }
        } else {
            schotR = 2;
        }
        schotR--;
    }

</script>

<script>
    $(function () {
        $("#datepicker").datepicker();
        $("#datepickerR").datepicker();
    });
</script>


<%@include file="include/footer.jsp" %>


</html>