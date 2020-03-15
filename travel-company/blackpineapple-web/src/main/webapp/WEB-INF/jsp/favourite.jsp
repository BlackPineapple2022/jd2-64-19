<%@ page import="java.time.LocalDateTime" %>
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

    <script>


        function deleteTrip(tripId) {

            var t = tripId + "b";
            var y = document.getElementById(t);
            y.style.display="none";
            var f = document.getElementById(t+"d");
            f.style.display="inline";
            setTimeout(deleteTable,1000,tripId);

        }

        function deleteTable(tripId) {
            var x = document.getElementById(tripId);
            x.style.display = "none";
        }


    </script>


    <style>


        /*td {
            width: 400px;
            margin: 5px;
            border: transparent 5px solid;
        }*/


        .welcome {
            font-family: 'Google Sans', Roboto, Arial, sans-serif;
            font-size: 40px;
            font-weight: 400;
            line-height: 1.5;
            word-break: break-word;
            word-wrap: break-word;
            color: #202124;
            text-align: center;
            margin: 10px;
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

        .label-1:hover {
            background: #FFFFFF;
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
            background: #E55747;
            padding: 6px 7px 6px 7px;
            text-decoration: none;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .my_button3:hover {
            border: solid #E55747 1px;
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

<div class="welcome">
    Избранное
</div>

<c:if test="${trips == null}">
    <br/>
    <br/>
    <div class="welcome" style="color: #E55747;font-size: 26px; border: #E55747 1px solid; border-radius: 5px; margin-right: 20%; margin-left: 20%; padding: 20px">
        К сожалению, список избранного пуст, воспользуйтесь <b><a
            href="${pageContext.request.contextPath}/specific" style="color: #66A33B">поиском</a></b>, чтобы это исправить
    </div>
    <br/>
    <br/>
    <br/>
</c:if>

<c:forEach items="${trips}" var="trip">

    <table id="${trip.id}" style="display: table">

        <tr>
            <td width="850px">
                <div style="border: #C0C0C0 1px solid; border-radius: 5px; margin: 10px">
                    <table>


                        <tr>
                            <td width="100px">
                                <c:if test="${trip.directFlight.routeMap.airline.name=='RY'}">
                                    <img width="100px"
                                         src="${pageContext.request.contextPath}/resources/pic/ryanair.png"/>
                                </c:if>

                                <c:if test="${trip.directFlight.routeMap.airline.name=='WIZZ'}">
                                    <img width="100px"
                                         src="${pageContext.request.contextPath}/resources/pic/wizzair.png"/>
                                </c:if>
                            </td>

                            <td width="300px">

                                <div class="welcome"
                                     style="font-size: 34px; color: #505050; margin-bottom: 5px; margin-top: 0px">
                                    Туда<br/>
                                    <fmt:parseDate value="${trip.directFlight.departureDateTime}" pattern="yyyy-MM-dd'T'HH:mm"
                                                   var="parsedDateTimeDirectDeparture" type="both"/>
                                    <fmt:formatDate pattern="dd.MM.yyyy " value="${ parsedDateTimeDirectDeparture }"/><br/>
                                    <fmt:formatDate pattern="E" value="${ parsedDateTimeDirectDeparture }"/>
                                </div>
                            </td>


                            <td width="100px">

                                <c:if test="${trip.returnFlight.routeMap.airline.name=='RY'}">
                                    <img width="100px"
                                         src="${pageContext.request.contextPath}/resources/pic/ryanair.png"/>
                                </c:if>

                                <c:if test="${trip.returnFlight.routeMap.airline.name=='WIZZ'}">
                                    <img width="100px"
                                         src="${pageContext.request.contextPath}/resources/pic/wizzair.png"/>
                                </c:if>
                            </td>

                            <td width="300px">

                                <div class="welcome"
                                     style="font-size: 34px; color: #505050; margin-bottom: 5px; margin-top: 0px">
                                    Обратно<br/>
                                    <fmt:parseDate value="${trip.returnFlight.departureDateTime}" pattern="yyyy-MM-dd'T'HH:mm"
                                                   var="parsedDateTimeReturnDeparture" type="both"/>
                                    <fmt:formatDate pattern="dd.MM.yyyy " value="${ parsedDateTimeReturnDeparture}"/><br/>
                                    <fmt:formatDate pattern="E" value="${ parsedDateTimeReturnDeparture }"/>
                                </div>
                            </td>

                        </tr>

                    </table>

                    <table>
                        <tr>
                            <td width="403px">
                                <div class="label-1">
                                    <div style="font-size: 50px; text-align: center">
                                        <fmt:parseDate value="${trip.directFlight.arriveDateTime}"
                                                       pattern="yyyy-MM-dd'T'HH:mm"
                                                       var="parsedDateTimeDirectArrive" type="both"/>
                                        <fmt:formatDate pattern="HH:mm " value="${ parsedDateTimeDirectDeparture }"/>
                                        -
                                        <fmt:formatDate pattern="HH:mm " value="${ parsedDateTimeDirectArrive }"/>
                                    </div>
                                    <table>
                                        <tr>
                                            <td width="200px">
                                                <div style="font-size: 20px; color: #505050; border: #505050 1px solid; border-radius: 5px;text-align: center">
                                                    <div style="color: black"><fmt:formatDate pattern="dd.MM.yyyy HH:mm"
                                                                                              value="${ parsedDateTimeDirectDeparture }"/></div>
                                                    <c:out value="${trip.directFlight.routeMap.airportOrigin.country}"/><br/>
                                                    <c:out value="${trip.directFlight.routeMap.airportOrigin.city}"/><br/>
                                                    <b><c:out
                                                            value="${trip.directFlight.routeMap.airportOrigin.code}"/></b>
                                                </div>
                                            </td>

                                            <td width="200px">
                                                <div style="font-size: 20px; color: #505050; border: #505050 1px solid; border-radius: 5px; text-align: center">
                                                    <div style="color: black"><fmt:formatDate pattern="dd.MM.yyyy HH:mm"
                                                                                              value="${ parsedDateTimeDirectArrive }"/></div>
                                                    <c:out value="${trip.directFlight.routeMap.airportDestination.country}"/><br/>
                                                    <c:out value="${trip.directFlight.routeMap.airportDestination.city}"/><br/>
                                                    <b><c:out
                                                            value="${trip.directFlight.routeMap.airportDestination.code}"/></b>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <div class="welcome"
                                                     style="color: #505050; font-size: 20px; margin: 5px">
                                                    Номер рейса:
                                                    <c:out value="${trip.directFlight.flightNumber}"/>
                                                </div>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">
                                                <div class="welcome"
                                                     style="color: #505050; font-size: 20px; margin: 5px">
                                                    Цена:
                                                    <b><c:out value="${trip.directFlight.price}"/></b>
                                                    <c:out value="${trip.directFlight.currency.code}"/>
                                                    <fmt:parseDate value="${trip.directFlight.updateDateTime}"
                                                                   pattern="yyyy-MM-dd'T'HH:mm"
                                                                   var="parsedDateTimeDirectChecked" type="both"/>
                                                    <div style="font-size: 16px">(актуальна на <fmt:formatDate
                                                            pattern="dd.MM.yyyy HH:mm"
                                                            value="${ parsedDateTimeDirectChecked }"/>)
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>

                                    </table>
                                </div>
                            </td>
                            <td width="403px">
                                <div class="label-1">
                                    <div style="font-size: 50px; text-align: center">
                                        <fmt:parseDate value="${trip.returnFlight.arriveDateTime}"
                                                       pattern="yyyy-MM-dd'T'HH:mm"
                                                       var="parsedDateTimeReturnArrive" type="both"/>
                                        <fmt:formatDate pattern="HH:mm " value="${ parsedDateTimeReturnDeparture }"/>
                                        -
                                        <fmt:formatDate pattern="HH:mm " value="${ parsedDateTimeReturnArrive }"/>

                                    </div>
                                    <table>
                                        <tr>
                                            <td width="200px">
                                                <div style="font-size: 20px; color: #505050; border: #505050 1px solid; border-radius: 5px; text-align: center">
                                                    <div style="color: black"><fmt:formatDate pattern="dd.MM.yyyy HH:mm"
                                                                                              value="${ parsedDateTimeReturnDeparture }"/></div>
                                                    <c:out value="${trip.returnFlight.routeMap.airportOrigin.country}"/><br/>
                                                    <c:out value="${trip.returnFlight.routeMap.airportOrigin.city}"/><br/>
                                                    <b><c:out
                                                            value="${trip.returnFlight.routeMap.airportOrigin.code}"/></b>
                                                </div>
                                            </td>

                                            <td width="200px">
                                                <div style="font-size: 20px; color: #505050; border: #505050 1px solid; border-radius: 5px; text-align: center">
                                                    <div style="color: black"><fmt:formatDate pattern="dd.MM.yyyy HH:mm"
                                                                                              value="${ parsedDateTimeReturnArrive }"/></div>
                                                    <c:out value="${trip.returnFlight.routeMap.airportDestination.country}"/><br/>
                                                    <c:out value="${trip.returnFlight.routeMap.airportDestination.city}"/><br/>
                                                    <b><c:out
                                                            value="${trip.returnFlight.routeMap.airportDestination.code}"/></b>
                                                </div>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">
                                                <div class="welcome"
                                                     style="color: #505050; font-size: 20px; margin: 5px">
                                                    Номер рейса:
                                                    <c:out value="${trip.returnFlight.flightNumber}"/>
                                                </div>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">
                                                <div class="welcome"
                                                     style="color: #505050; font-size: 20px; margin: 5px">
                                                    Цена:
                                                    <b><c:out value="${trip.returnFlight.price}"/></b>
                                                    <c:out value="${trip.returnFlight.currency.code}"/>
                                                    <fmt:parseDate value="${trip.returnFlight.updateDateTime}"
                                                                   pattern="yyyy-MM-dd'T'HH:mm"
                                                                   var="parsedDateTimeReturnChecked" type="both"/>
                                                    <div style="font-size: 16px">(актуальна на <fmt:formatDate
                                                            pattern="dd.MM.yyyy HH:mm"
                                                            value="${ parsedDateTimeReturnChecked }"/>)
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>

                                    </table>


                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2">
                                <div class="welcome"
                                     style="margin: 10px; margin-bottom: 0px; font-size: 40px; color: black">
                                    Итоговая цена:
                                        <fmt:formatNumber type="number"
                                                          maxFractionDigits="2" value="${trip.price}"/> EUR

                            </td>
                        </tr>

                        <c:if test="${user != null}">

                            <tr>
                                <td colspan="2"
                                    style="text-align: right; margin-left: 10px; margin-top: 0px; margin-bottom: 0px">
                                    <form method="POST"
                                          action="${pageContext.request.contextPath}/favourite/delete"
                                          target="iframe1">
                                        <input type="hidden" name="id" value="${trip.id}">
                                        <button class="my_button3" id="${trip.id}b" onclick=deleteTrip(${trip.id})>
                                            Удалить
                                        </button>

                                        <button class="my_button3" id="${trip.id}bd" type="button" style="text-align: right;alignment:right; display: none; background: #FFFFFF; color: #E55747; border: #E55747 1px solid">
                                            Удалено
                                        </button>

                                    </form>
                                </td>
                            </tr>
                        </c:if>

                    </table>
                </div>
            </td>
        </tr>
    </table>


</c:forEach>


<iframe name="iframe1" style="position: absolute; left: -9999px;"></iframe>


</body>


<%@include file="include/footer.jsp" %>


</html>