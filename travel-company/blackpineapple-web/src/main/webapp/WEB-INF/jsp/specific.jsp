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

<c:if test="${errorCodeSet != null}">
    <br/>
    <br/>
    <br/>
    <div style="color: #E55747; text-align: center; font-family: Arial, serif; font-size: 30px; border: #E55747 1px solid; border-radius: 5px; margin-left: 20%; margin-right: 20%">
        <br/>
        УПС. Что-то пошло не так...:


        <c:forEach var="error" items="${errorCodeSet}">

            <c:if test="${error == 1}"><p
                    style="color: #E55747; text-align: center; font-family: Arial, serif; font-size: 20px">Вы
                не выбрали, откуда начинается путешествие</p>
            <style>
                .red-label-1{
                    border: solid #E55747 1px;
                    background: #FFE4E4;
                }
            </style>
            </c:if>
            <c:if test="${error == 2}"><p
                    style="color: #E55747; text-align: center; font-family: Arial, serif; font-size: 20px">Вы
                не выбрали, где завершается путешествие</p>

                <style>
                .red-label-2{
                border: solid #E55747 1px;
                background: #FFE4E4;
                }
                </style>


            </c:if>
            <c:if test="${error == 3}"><p
                    style="color: #E55747; text-align: center; font-family: Arial, serif; font-size: 20px">
                Неверно указана самая ранняя дата начала путешествия</p>
                <style>
                    .red-date-1{
                        border: solid #E55747 1px;
                        background: #FFE4E4;
                    }
                </style>


            </c:if>
            <c:if test="${error == 4}"><p
                    style="color: #E55747; text-align: center; font-family: Arial, serif; font-size: 20px">
                Неверно указана самая поздняя дата начала путешествия</p>

                <style>
                    .red-date-2{
                        border: solid #E55747 1px;
                        background: #FFE4E4;
                    }
                </style>

            </c:if>
            <c:if test="${error == 5}"><p
                    style="color: #E55747; text-align: center; font-family: Arial, serif; font-size: 20px">
                Самая поздняя дата начала путешествия не может быть раньше самой ранней</p>

                <style>
                    .red-date-2{
                        border: solid #E55747 1px;
                        background: #FFE4E4;
                    }
                </style>

            </c:if>
            <c:if test="${error == 6}"><p
                    style="color: #E55747; text-align: center; font-family: Arial, serif; font-size: 20px">
                Неверно указана минимальная продолжительность путешествия (не может быть мень 2-х дней и более
                30-ти)</p>

                <style>
                    .red-count{
                        border: solid #E55747 1px;
                        background: #FFE4E4;
                    }
                </style>

            </c:if>
            <c:if test="${error == 7}"><p
                    style="color: #E55747; text-align: center; font-family: Arial, serif; font-size: 20px">
                Неверно указана максимальная продолжительность путешествия (не может быть мень 2-х дней и более
                30-ти)</p>
                <style>
                    .red-count{
                        border: solid #E55747 1px;
                        background: #FFE4E4;
                    }
                </style>

            </c:if>

            <c:if test="${error == 9}"><p
                    style="color: #E55747; text-align: center; font-family: Arial, serif; font-size: 20px">
                Максимальная продолжительность путешествия не может быть меньше минимальной
                </p>

                <style>
                    .red-count{
                        border: solid #E55747 1px;
                        background: #FFE4E4;
                    }
                </style>

            </c:if>


        </c:forEach>
        <br/>
    </div>
</c:if>


<form autocomplete="off" method="POST" action="${pageContext.request.contextPath}/specific">

    <div class="welcome">
        Поиск авиабилетов
    </div>

    <table>
        <tr>
            <td>
                <div class="label-1 red-label-1">Старт:
                    <div class="small24">
                        <input type="checkbox" name="originDirectIsVNO"  value="Y" ${checkedVNOStart}/> Вильнюс<br/>
                        <input type="checkbox" name="originDirectIsKUN" ${checkedKUNStart} value="Y"/> Каунас<br/>
                        <input type="checkbox" name="originDirectIsWMI" ${checkedWMIStart} value="Y"/> Варшава Модлин<br/>
                        <input type="checkbox" name="originDirectIsWAW" ${checkedWAWStart} value="Y"/> Варшава Шопен<br/>
                    </div>
                </div>
            </td>

            <td>
                <div class="label-1 red-label-2">
                    Финиш:
                    <div class="small24">
                        <input type="checkbox" name="originReturnIsVNO" ${checkedVNOFinish} value="Y"/> Вильнюс<br/>
                        <input type="checkbox" name="originReturnIsKUN" ${checkedKUNFinish} value="Y"/> Каунас<br/>
                        <input type="checkbox" name="originReturnIsWMI" ${checkedWMIFinish} value="Y"/> Варшаву Модлин<br/>
                        <input type="checkbox" name="originReturnIsWAW" ${checkedWAWFinish} value="Y"/> Варшаву Шопен<br/>
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div class="label-1">Дополнительные параметры поиска:
                    <div class="small24">
                        <input name="startingFilter" type="radio" value="noFilter" ${checkedNoFilter} >
                        Без ограничений<br/>
                        <input name="startingFilter" type="radio" value="countryFilter" ${checkedCountryFilter} >
                        Вылет (старт) и прилёт (финиш) в одну страну<br/>
                        <input name="startingFilter" type="radio" value="cityFilter" ${checkedCityFilter}>
                        Вылет (старт) и прилёт (финиш) в один город<br/>
                        <input name="startingFilter" type="radio" value="airportFilter" ${checkedAirportFilter}>
                        Вылет (старт) и прилёт (финиш) в один аэропорт<br/>
                    </div>

                    <button class="my_button3" type="button" onclick="about1()" style="margin-top: 0px; margin-bottom: 0px">
                        Подробнее
                    </button>


                </div>

            </td>


        </tr>

        <tr >
            <td colspan="2">



                <div id="about1" style="color: #444444; text-align: left; font-family: Arial, serif; font-size: 20px; border: #E55747 1px solid; border-radius: 5px; padding: 10px; line-height: 1.5; display: none">
                    <div style="font-size: 30px">ПОДРОБНЕЕ:</div>

                    <br/>
                    1. Выберете <b>Без ограничений</b>, если: <br/>
                    Вы хотите, чтобы были отображены все результаты поиска, без исключений;
                    <br/>
                    <br/>
                    2. Выберете <b>Вылет и прилёт в одну страну</b>, если: <br/>
                    Вы хотите видеть варианты, в которых стартовая страна (Литва или Польша) совпадает
                    с финишной страной (опять же Литвой или Польшей). <br/>
                    <b>Зачем это может быть нужно?</b> Например, Вы не хотите, что бы отправляясь в путешествие и покидая
                    Беларусь Вам в паспорт поставили штамп одной страны, а по возвращению - другой.
                    <br/>
                    <br/>
                    3. Выберете <b>Вылет и прилёт в один город</b>, если: <br/>
                    Вы хотите видеть варианты, в которых стартовый город (Вильнюс, Каунас, Варшава) совпадает
                    с финишным (опять же Вильнюсом, Каунасом, Варшавой).
                    <br/>
                    <b>Зачем это может быть нужно?</b>Например, Вы выезжаете из Беларуси на авто и
                    хотите его припарковать на городской стоянке, или же оставить что-то в общественных камерах хранения.
                    <br/>
                    <br/>
                    4. Выберете <b>Вылет и прилёт в один аэропорт</b>, если: <br/>
                    Вы выезжаете из Бераруси на авто и хотите припарковаться на стоянке в аэропорту, или же оставить
                    вещи в камерах хранения аэропорта.
                </div>
            </td>

        </tr>

        <tr>

            <td colspan="2">

                <div class="label-1">Куда отправимся(не более 5-ти аэропортов):
                    <select name="airportDestinationDirect1" class="list">
                        <option value="${defaultAirport1.code}" selected="selected">
                            ${defaultAirport1.country} &nbsp ${defaultAirport1.city} &nbsp ${defaultAirport1.code}
                        </option>
                        <c:forEach items="${allStartedAirports}" var="airport">
                            <option value="${airport.code}">
                                    ${airport.country} &nbsp ${airport.city} &nbsp ${airport.code}
                            </option>
                        </c:forEach>
                    </select>

                    <div id="myDIV2" style="display: ${displayDiv2}">
                        <select id="select2" name="airportDestinationDirect2" class="list">
                            <option value="${defaultAirport2.code}" selected="selected">
                                ${defaultAirport2.country} &nbsp ${defaultAirport2.city} &nbsp ${defaultAirport2.code}
                            </option>
                            <option value="NONE">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} &nbsp ${airport.city} &nbsp ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="myDIV3" style="display: ${displayDiv3}">
                        <select id="select3" name="airportDestinationDirect3" class="list">
                            <option value="${defaultAirport3.code}" selected="selected">
                                ${defaultAirport3.country} &nbsp ${defaultAirport3.city} &nbsp ${defaultAirport3.code}
                            </option>
                            <option value="NONE">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} &nbsp ${airport.city} &nbsp ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="myDIV4" style="display: ${displayDiv4}">
                        <select id="select4" name="airportDestinationDirect4" class="list">
                            <option value="${defaultAirport4.code}" selected="selected">
                                ${defaultAirport4.country} &nbsp ${defaultAirport4.city} &nbsp ${defaultAirport4.code}
                            </option>
                            <option value="NONE">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} &nbsp ${airport.city} &nbsp ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="myDIV5" style="display: ${displayDiv5}">
                        <select id="select5" name="airportDestinationDirect5" class="list">
                            <option value="${defaultAirport5.code}" selected="selected">
                                ${defaultAirport5.country} &nbsp ${defaultAirport5.city} &nbsp ${defaultAirport5.code}
                            </option>
                            <option value="NONE">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} &nbsp ${airport.city} &nbsp ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <button class="my_button3" type="button" onclick="airportDirect()" style="margin-bottom: 0px; margin-top: 0px">
                        Добавить
                    </button>
                    <button class="my_button4" type="button" onclick="airportDirectDelete()" style="margin-bottom: 0px; margin-top: 0px"> Удалить</button>

                </div>

            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div class="label-1" id="returnAirportBlock">Откуда будем возвращаться назад(не более 5-ти аэропортов):
                    <select name="airportDestinationReturn1" class="list">

                        <option value="${defaultAirportReturn1.code}" selected="selected">
                            ${defaultAirportReturn1.country} &nbsp ${defaultAirportReturn1.city} &nbsp ${defaultAirportReturn1.code}
                        </option>

                        <c:forEach items="${allStartedAirports}" var="airport">
                            <option value="${airport.code}">
                                    ${airport.country} &nbsp ${airport.city} &nbsp ${airport.code}
                            </option>
                        </c:forEach>
                    </select>

                    <div id="myDIVR2" style="display: ${displayDivR2}">
                        <select id="selectR2" name="airportDestinationReturn2" class="list">
                            <option value="${defaultAirportReturn2.code}" selected="selected">
                                ${defaultAirportReturn2.country} &nbsp ${defaultAirportReturn2.city} &nbsp ${defaultAirportReturn2.code}
                            </option>
                            <option value="NONE">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} &nbsp ${airport.city} &nbsp ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="myDIVR3" style="display: ${displayDivR3}">
                        <select id="selectR3" name="airportDestinationReturn3" class="list">
                            <option value="${defaultAirportReturn3.code}" selected="selected">
                                ${defaultAirportReturn3.country} &nbsp ${defaultAirportReturn3.city} &nbsp ${defaultAirportReturn3.code}
                            </option>
                            <option value="NONE">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} &nbsp ${airport.city} &nbsp ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="myDIVR4" style="display: ${displayDivR4}">
                        <select id="selectR4" name="airportDestinationReturn4" class="list">
                            <option value="${defaultAirportReturn4.code}" selected="selected">
                                ${defaultAirportReturn4.country} &nbsp ${defaultAirportReturn4.city} &nbsp ${defaultAirportReturn4.code}
                            </option>
                            <option value="NONE">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} &nbsp ${airport.city} &nbsp ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="myDIVR5" style="display: ${displayDivR5}">
                        <select id="selectR5" name="airportDestinationReturn5" class="list">
                            <option value="${defaultAirportReturn5.code}" selected="selected">
                                ${defaultAirportReturn5.country} &nbsp ${defaultAirportReturn5.city} &nbsp ${defaultAirportReturn5.code}
                            </option>
                            <option value="NONE">
                                -
                            </option>
                            <c:forEach items="${allStartedAirports}" var="airport">
                                <option value="${airport.code}">
                                        ${airport.country} &nbsp ${airport.city} &nbsp ${airport.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <button class="my_button3" type="button" onclick="airportReturn()" style="margin-bottom: 0px; margin-top: 0px">
                        Добавить
                    </button>
                    <button class="my_button4" type="button" onclick="airportReturnDelete()" style="margin-bottom: 0px; margin-top: 0px"">
                        Удалить
                    </button>

                </div>
            </td>
        </tr>


        <tr>

            <td colspan="2">
                <div class="label-1">Дополнительные параметры:
                    <div class="small24">
                        <input name="endingFilter" type="radio" value="noFilter" ${checkedNoFilterR}>
                        Без ограничений <br/>
                        <input name="endingFilter" type="radio" value="countryFilter" ${checkedCountryFilterR}>
                        Прилёт и возврат из одной страны<br/>
                        <input name="endingFilter" type="radio" value="cityFilter" ${checkedCityFilterR}>
                        Прилёт и возврат из одного города<br/>
                        <input name="endingFilter" type="radio" value="airportFilter" ${checkedAirportFilterR}>
                        Прилёт и возврат из одного аэропорта<br/>
                    </div>
                    <button class="my_button3" type="button" onclick="about2()" style="margin-top: 0px; margin-bottom: 0px">
                    Подробнее
                    </button>
                </div>

            </td>
        </tr>

        <tr >
            <td colspan="2">

        <div id="about2" style="color: #444444; text-align: left; font-family: Arial, serif; font-size: 20px; border: #E55747 1px solid; border-radius: 5px; padding: 10px; line-height: 1.5; display: none">
            <div style="font-size: 30px">ПОДРОБНЕЕ:</div>

            <br/>
            1. Вы можете искать одно конкретное направление. Например, выберите - куда: Австрия,Вена и откуда - Австрия,Вена соответственно.<br/>
            2. Вы можете искать один составной маршрут, например с прилётом в Барселону, или Мадрид а вылетом из Лиссабона.
            (Не забудьте подумать, как добраться из Испании в Португалию)<br/>
            3. Вы можете искать сразу пять направлений туда, и пять обратно, например туда:
            Италия - Турин, Милан-Бергамо, Милан-Мальпенса, Верона, Венеция, откуда - Рим-Фьюмичино, Рим-Чампино, Неаполь.
            <br/>
            <br/>

            1. Выберете <b>Без ограничений</b>, если: <br/>
            Вы хотите, чтобы были отображены все результаты поиска, без исключений;
            <br/>
            <br/>
            2. Выберете <b>Прилёт и возврат из одной страны</b>, если: <br/>
            Вы хотите видеть варианты, в которых страна в которую Вы прилетаете совпадает с той, из которой Вы вылетаете.<br/>
            <b>Зачем это может быть нужно?</b> Например, Вы сразу ищете несколько вариантов на 5-7 дней,
            например Францию, Италию и Испанию и не против поперемещаться внутри страны. Или же Вы выбрали оба аэропорта Кипра,
            и не против проехаться от Ларнаки до Пафоса.
            <br/>
            <br/>
            3. Выберете <b>Вылет и прилёт в один город</b>, если: <br/>
            В некоторых городах несколько аэропортов, например в Риме, Милане, Париже. Вы планируете недолгий трип и не планируете
            перемещаться внутри страны назначения.
            <br/>
            <br/>
            4. Выберете <b>Вылет и прилёт в один аэропорт</b>, если: <br/>
            Этот вариант лучше всего подходит при аренде авто в аэропорту.
        </div>
            </td>
        </tr>

        <tr>
            <td>
                <div class="label-1 red-date-1">Самая ранняя дата начала путешествия
                    <div style="font-size: 30px">ММ/ДД/ГГГГ</div>
                    <input type="text" autocomplete="off" name="startingDate" id="datepicker" value="${defaultStartDate}"
                           style="font-family: 'Google Sans', Roboto, Arial, sans-serif; font-size: 22px; width: 380px">
                </div>
            </td>
            <td>
                <div class="label-1 red-date-2">Самая поздняя дата начала путешествия
                    <div style="font-size: 30px">ММ/ДД/ГГГГ</div>
                    <input type="text" autocomplete="off" name="endingDate" id="datepickerR" value="${defaultFinishDate}"
                           style="font-family: 'Google Sans', Roboto, Arial, sans-serif; font-size: 22px; width: 380px">
                </div>
            </td>
        </tr>

        <tr>

            <td colspan="2">
                <div class="label-1 red-count">Количество дней в путешествии (от 2-х до 30-ти):
                    <div class="small24">от <input type="text" name="minDay" height="30px" value ="${minDay}"
                                                   style=" width: 200px; height: 30px; font-size: 22px; font-family: 'Google Sans', Roboto, Arial, sans-serif">
                        до <input type="text" name="maxDay" height="30px" value="${maxDay}"
                                  style="width: 200px; height: 30px; font-size: 22px; font-family: 'Google Sans', Roboto, Arial, sans-serif">
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <button type="submit" class="my_button3"
                        style="margin-top: 0px; padding-left: 40px; padding-right: 40px">Поиск
                </button>
            </td>
        </tr>


    </table>

</form>


</body>

<script>

    function about1() {
        var x = document.getElementById('about1');
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }

    function about2() {
        var x = document.getElementById('about2');
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }



    schotD = ${schotD};

    function airportDirect() {
        schotD++;
        if (schotD < 6) {
            var x = document.getElementById('myDIV' + schotD);
            if (x.style.display === "none") {

                document.getElementById('select' + schotD).options[1].selected = true;
                x.style.display = "block";
            } else {
                alert("NEVER!")
                /*x.style.display = "none";*/
                /*document.getElementById('select' + schotD).options[0].selected = true;*/
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
                document.getElementById('select' + schotD).options[1].selected = true;
            } else {
                alert("NEVER!")
                /*x.style.display = "none";
                document.getElementById('select' + schotD).options[0].selected = true;*/
            }
        } else {
            schotD = 2;
        }
        schotD--;
    }

    schotR = ${schotR};

    function airportReturn() {
        schotR++;
        if (schotR < 6) {
            var x = document.getElementById('myDIVR' + schotR);
            if (x.style.display === "none") {
                x.style.display = "block";
                document.getElementById('selectR' + schotR).options[1].selected = true;
            } else {
                alert("NEVER!")
                /*x.style.display = "none";
                document.getElementById('selectR' + schotR).options[0].selected = true;*/
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
                document.getElementById('selectR' + schotR).options[1].selected = true;
            } else {
                alert("NEVER!")
                /*x.style.display = "none";
                document.getElementById('selectR' + schotR).options[0].selected = true;*/
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