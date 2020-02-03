<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | Хочу что-то</title>
</head>

<body>

<style>
    .welcome {
        padding: 10px;
        font-family: "Times New Roman", Arial, serif;
        font-size: 40px;
        text-align: center;
        color: #000000;
        margin: 10px;
    }

    .left-label-1 {
        background: #FFFFFF;
        border-top: 2px solid #9EA8F1;
        color: #000000;
        font-family: "Times New Roman", Arial, serif;
        font-size: 30px;
        padding: 20px;
        padding-top: 5px;
        padding-left: 5px;
        padding-bottom: 5px;
        margin-top: 10px;
    }

    .right-label-1 {
        background: #FFFFFF;
        border-top: 2px solid #9EA8F1;
        color: #000000;
        font-family: "Times New Roman", Arial, serif;
        font-size: 30px;
        padding: 20px;
        padding-top: 5px;
        padding-left: 5px;
        padding-bottom: 5px;
        margin-top: 10px;
    }

    .label-2 {
        background: #FFFFFF;
        border-top: 2px solid #C0C0C0;
        color: #000000;
        font-family: "Times New Roman", Arial, serif;
        font-size: 30px;
        padding: 20px;
        padding-top: 5px;
        padding-left: 5px;
        padding-bottom: 5px;
        margin-top: 10px;
    }

    .label-3 {
        margin-top: 10px;
        background: #FFFFFF;
        border-top: 2px solid #9EA8F1;
        color: #000000;
        font-family: "Times New Roman", Arial, serif;
        font-size: 30px;
        padding: 20px;
        padding-top: 5px;
        padding-left: 5px;
        padding-bottom: 5px;
    }

    .list {
        width: 800px;
        height: 30px;
        font-size: 20px;
        font-family: "Times New Roman", Arial, serif;
    }


    .my_button {
        -webkit-border-radius: 0px;
        -moz-border-radius: 0px;
        border-radius: 0px;
        font-family: Arial;
        color: #C0C0C0;
        font-size: 18px;
        background: #FFFFFF;
        text-hover: #9ea8f1;
        padding: 1px 6px 1px 6px;
        border: solid #C0C0C0 2px;
        text-decoration: none;
    }

    .my_button:hover {
        color: #9ea8f1;
        border: solid #9ea8f1 2px;
        text-decoration: none;
    }

    .my_button:after {
        color: #C0C0C0;
        border: solid #C0C0C0 2px;
        text-decoration: none;

    }

    .my_button2 {

        border: solid #C0C0C0 1px;
        font-family: "Times New Roman", Arial, serif;
        color: #ffffff;
        font-size: 50px;
        background: #C0C0C0;
        text-hover: #ffffff;
        padding: 10px 60px 12px 60px;
        text-decoration: none;
    }

    .my_button2:hover {

        border: solid #9ea8f1 1px;
        font-family: "Times New Roman", Arial, serif;
        color: #ffffff;
        font-size: 50px;
        background: #9EA8F1;
        text-hover: #ffffff;
        padding: 10px 60px 12px 60px;
        text-decoration: none;
    }


</style>


<%@include file="include/header.jsp" %>
<%@include file="include/menu.jsp" %>


<form method="POST" action="${pageContext.request.contextPath}/specificResult">

    <div class="welcome">
        Чего же Вы хотите?
    </div>

    <table align="center">
        <tr>
            <td>
            </td>
            <td width="400px">
                <div class="left-label-1">Я хочу улететь из:<br/>
                    <div style="font-size: 20px">
                        <input type="checkbox" name="originDirectIsVNO" value="Y"/> Вильнюса<br/>
                        <input type="checkbox" name="originDirectIsKUN" value="Y"/> Каунаса<br/>
                        <input type="checkbox" name="originDirectIsWMI" value="Y"/> Варшавы Модлин<br/>
                        <input type="checkbox" name="originDirectIsWAW" value="Y"/> Варшавы Шопен<br/>
                    </div>
                </div>
            </td>

            <td width="400px">
                <div class="right-label-1">
                    Я хочу вернуться в:<br/>
                    <div style="font-size: 20px">
                        <input type="checkbox" name="originReturnIsVNO" value="Y"/> Вильнюс<br/>
                        <input type="checkbox" name="originReturnIsKUN" value="Y"/> Каунас<br/>
                        <input type="checkbox" name="originReturnIsWMI" value="Y"/> Варшаву Модлин<br/>
                        <input type="checkbox" name="originReturnIsWAW" value="Y"/> Варшаву Шопен<br/>
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td>
            </td>
            <td colspan="2" width="400px">
                <div class="label-2">А ещё я хочу:
                    <div style="font-size: 20px">
                        <input name="startingFilter" type="radio" value="noFilter">
                        Всё хочу, всё без исключения!<br/>
                        <input name="startingFilter" type="radio" value="countryFilter">
                        Хочу вылететь и прилететь в одну страну<br/>
                        <input name="startingFilter" type="radio" value="cityFilter">
                        Хочу вылететь и прилететь в один город<br/>
                        <input name="startingFilter" type="radio" value="airportFilter">
                        Хочу вылететь и прилететь в один аэропорт<br/>
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td>
            </td>
            <td colspan="2" width="400px">
                <div class="label-3">Куда отправимся?

                </div>
            </td>
        </tr>

        <tr>
            <td>
            </td>
            <td colspan="2" width="400px">
                <select name="airportDestinationDirect1" class="list">
                    <c:forEach items="${allStartedAirports}" var="airport">
                        <option value="${airport.code}">

                                ${airport.country} | ${airport.city} | ${airport.code}

                        </option>
                    </c:forEach>
                </select></td>
        </tr>

        <tr>
            <td>
                <button class="my_button" type="button" onclick="myFunction2()">+</button>
            </td>
            <td colspan="2">

                <div id="myDIV2" style="display: none">
                    <select id="select2" name="airportDestinationDirect2"
                            class="list">
                        <option value="NONE" selected="selected">-
                        </option>
                        <c:forEach items="${allStartedAirports}" var="airport">
                            <option value="${airport.code}">
                                    ${airport.country} | ${airport.city} | ${airport.code}
                            </option>
                        </c:forEach>

                    </select>
                </div>
            </td>

        </tr>

        <tr>
            <td>
                <button class="my_button" type="button" onclick="myFunction3()">+</button>
            </td>
            <td colspan="2">

                <div id="myDIV3" style="display: none">
                    <select id="select3" name="airportDestinationDirect3"
                            class="list">
                        <option value="NONE" selected="selected">-
                        </option>
                        <c:forEach items="${allStartedAirports}" var="airport">
                            <option value="${airport.code}">
                                    ${airport.country} | ${airport.city} | ${airport.code}
                            </option>
                        </c:forEach>

                    </select>
                </div>
            </td>

        </tr>

        <tr>
            <td>
                <button class="my_button" type="button" onclick="myFunction4()">+</button>
            </td>
            <td colspan="2">

                <div id="myDIV4" style="display: none">
                    <select id="select4" name="airportDestinationDirect4"
                            class="list">
                        <option value="NONE" selected="selected">-
                        </option>
                        <c:forEach items="${allStartedAirports}" var="airport">
                            <option value="${airport.code}">
                                    ${airport.country} | ${airport.city} | ${airport.code}
                            </option>
                        </c:forEach>

                    </select>
                </div>
            </td>

        </tr>

        <tr>
            <td>
                <button class="my_button" type="button" onclick="myFunction5()">+</button>
            </td>
            <td colspan="2">

                <div id="myDIV5" style="display: none">
                    <select id="select5" name="airportDestinationDirect5"
                            class="list">
                        <option value="NONE" selected="selected">-
                        </option>
                        <c:forEach items="${allStartedAirports}" var="airport">
                            <option value="${airport.code}">
                                    ${airport.country} | ${airport.city} | ${airport.code}
                            </option>
                        </c:forEach>
                    </select></div>
            </td>

        </tr>

        <tr>
            <td><br/></td>
            <td></td>
            <td></td>
        </tr>

        <tr>
            <td>
            </td>
            <td colspan="2" width="400px">
                <div class="label-2">А обратно откуда?</div>

            </td>
        </tr>

        <tr>
            <td>
            </td>
            <td colspan="2" width="400px">

                <select name="airportDestinationReturn1" class="list">
                    <c:forEach items="${allStartedAirports}" var="airport">
                        <option value="${airport.code}">

                                ${airport.country} | ${airport.city} | ${airport.code}

                        </option>
                    </c:forEach>
                </select>
                </div>

                <br>


            </td>
        </tr>

        <tr>
            <td>
                <button class="my_button" type="button" onclick="myFunction7()">+</button>
            </td>
            <td colspan="2">

                <div id="myDIV7" style="display: none">
                    <select id="select7" name="airportDestinationReturn2"
                            class="list">
                        <option value="NONE" selected="selected">-
                        </option>
                        <c:forEach items="${allStartedAirports}" var="airport">
                            <option value="${airport.code}">
                                    ${airport.country} | ${airport.city} | ${airport.code}
                            </option>
                        </c:forEach>

                    </select>
                </div>
            </td>

        </tr>

        <tr>
            <td>
                <button class="my_button" type="button" onclick="myFunction8()">+</button>
            </td>
            <td colspan="2">

                <div id="myDIV8" style="display: none">
                    <select id="select8" name="airportDestinationReturn3"
                            class="list">
                        <option value="NONE" selected="selected">-
                        </option>
                        <c:forEach items="${allStartedAirports}" var="airport">
                            <option value="${airport.code}">
                                    ${airport.country} | ${airport.city} | ${airport.code}
                            </option>
                        </c:forEach>

                    </select>
                </div>
            </td>

        </tr>

        <tr>
            <td>
                <button class="my_button" type="button" onclick="myFunction9()">+</button>
            </td>
            <td colspan="2">

                <div id="myDIV9" style="display: none">
                    <select id="select9" name="airportDestinationReturn4"
                            class="list">
                        <option value="NONE" selected="selected">-
                        </option>
                        <c:forEach items="${allStartedAirports}" var="airport">
                            <option value="${airport.code}">
                                    ${airport.country} | ${airport.city} | ${airport.code}
                            </option>
                        </c:forEach>

                    </select>
                </div>
            </td>

        </tr>

        <tr>
            <td>
                <button class="my_button" type="button" onclick="myFunction10()">+</button>
            </td>
            <td colspan="2">

                <div id="myDIV10" style="display: none">
                    <select id="select10" name="airportDestinationReturn5"
                            class="list">
                        <option value="NONE" selected="selected">-
                        </option>
                        <c:forEach items="${allStartedAirports}" var="airport">
                            <option value="${airport.code}">
                                    ${airport.country} | ${airport.city} | ${airport.code}
                            </option>
                        </c:forEach>

                    </select>
                </div>
            </td>

        </tr>

        <tr>
            <td><br/></td>
            <td></td>
            <td></td>
        </tr>

        <tr>
            <td>
            </td>
            <td colspan="2" width="400px">
                <div class="left-label-1">И ещё хочу, чтобы:
                    <div style="font-size: 20px">
                        <input name="endingFilter" type="radio" value="noFilter">
                        Всё хочу, всё без исключения, что не ясно то?<br/>
                        <input name="endingFilter" type="radio" value="countryFilter">
                        Хочу прилететь и вернуться из одной страны<br/>
                        <input name="endingFilter" type="radio" value="cityFilter">
                        Хочу прилететь и вернуться из одного города<br/>
                        <input name="endingFilter" type="radio" value="airportFilter">
                        Хочу прилететь и вернуться из одного аэропорта<br/>
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td></td>
            <td colspan="2">
                <div class="label-2">Самая ранняя дата начала путешествия?</div>
            </td>
        </tr>

        <tr>
            <td></td>

            <td colspan="2">
                <input type="date" name="startingDate">
                <br/><br/>
            </td>
        </tr>

        <tr>
            <td></td>

            <td colspan="2">
                <div class="left-label-1">Самая поздняя дата начала путешествия?</div>
            </td>
        </tr>

        <tr>
            <td></td>
            <td colspan="2">
                <input type="date" name="endingDate">
                <br/><br/>

            </td>
        </tr>

        <tr>
            <td></td>

            <td colspan="2">
                <div class="label-2">Сколько дней?</div>
                <div style="font-size: 20px" align="left">от <input type="text" name="minDay" height="20px"
                                                                    style="height: 20px; font-size: 18px"> до <input
                        type="text" name="maxDay" height="20px" style="height: 20px; font-size: 18px"></div>
            </td>
        </tr>

        <tr>
            <td colspan="3"><br/>
            </td>
        </tr>


    </table>
    <div class="welcome">
        <button type="submit" class="my_button2">Поиск</button>
    </div>

</form>

<script>

    function myFunction2() {
        var x = document.getElementById("myDIV2");
        if (x.style.display === "none") {
            x.style.display = "block";
            document.getElementById("select2").options[0].selected = true;
        } else {
            x.style.display = "none";
            document.getElementById("select2").options[0].selected = true;
        }
    }

    function myFunction3() {
        var x = document.getElementById("myDIV3");
        if (x.style.display === "none") {
            x.style.display = "block";
            document.getElementById("select3").options[0].selected = true;
        } else {
            x.style.display = "none";
            document.getElementById("select3").options[0].selected = true;
        }
    }

    function myFunction4() {
        var x = document.getElementById("myDIV4");
        if (x.style.display === "none") {
            x.style.display = "block";
            document.getElementById("select4").options[0].selected = true;
        } else {
            x.style.display = "none";
            document.getElementById("select4").options[0].selected = true;
        }
    }

    function myFunction5() {
        var x = document.getElementById("myDIV5");
        if (x.style.display === "none") {
            x.style.display = "block";
            document.getElementById("select5").options[0].selected = true;
        } else {
            x.style.display = "none";
            document.getElementById("select5").options[0].selected = true;
        }
    }

    function myFunction7() {
        var x = document.getElementById("myDIV7");
        if (x.style.display === "none") {
            x.style.display = "block";
            document.getElementById("select7").options[0].selected = true;
        } else {
            x.style.display = "none";
            document.getElementById("select7").options[0].selected = true;
        }
    }

    function myFunction8() {
        var x = document.getElementById("myDIV8");
        if (x.style.display === "none") {
            x.style.display = "block";
            document.getElementById("select8").options[0].selected = true;
        } else {
            x.style.display = "none";
            document.getElementById("select8").options[0].selected = true;
        }
    }

    function myFunction9() {
        var x = document.getElementById("myDIV9");
        if (x.style.display === "none") {
            x.style.display = "block";
            document.getElementById("select9").options[0].selected = true;
        } else {
            x.style.display = "none";
            document.getElementById("select9").options[0].selected = true;
        }
    }

    function myFunction10() {
        var x = document.getElementById("myDIV10");
        if (x.style.display === "none") {
            x.style.display = "block";
            document.getElementById("select10").options[0].selected = true;
        } else {
            x.style.display = "none";
            document.getElementById("select10").options[0].selected = true;
        }
    }


</script>


<%@include file="include/footer.jsp" %>
</body>

</html>