<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | manual Trip Scanner</title>

    <style>
        p {
            margin-top: 0px;
            margin-bottom: 5px;
        }
    </style>

</head>
<body>
<%@include file="include/adminheader.jsp" %>
<br>
<div align="center" style="font-size: 30px"><b>MANUAL TRIP SCANNER</b></div>
<br>

<form method="POST" action="${pageContext.request.contextPath}/admin/scanner/manual">



    <table align="center" border="0px" bordercolor="grey">

        <tr>
            <td></td>
            <td colspan="5">__ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ </td>
            <td></td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="2">
                <b>DIRECT ROUTE:</b>
            </td>
            <td>|</td>
            <td colspan="2">
                <b>RETURN ROUTE:</b>
            </td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="2">
                Where we'll start?
            </td>
            <td>|</td>
            <td colspan="2">
                Where we'll end?
            </td>
            <td>|</td>
        </tr>

        <tr>
            <td>|<br>|<br>|<br>|<br></td>
            <td colspan="2">
                <input type="checkbox" name="originDirectIsVNO" value="Y"/> Vilnius Airport<br>
                <input type="checkbox" name="originDirectIsKUN" value="Y"/> Kaunas Airport<br>
                <input type="checkbox" name="originDirectIsWMI" value="Y"/> Warsaw Airport Modlen<br>
                <input type="checkbox" name="originDirectIsWAW" value="Y"/> Warsaw Airport Chopin<br>
            </td>
            <td>|<br>|<br>|<br>|<br></td>
            <td colspan="2">
                <input type="checkbox" name="originReturnIsVNO" value="Y"/> Vilnius Airport<br>
                <input type="checkbox" name="originReturnIsKUN" value="Y"/> Kaunas Airport<br>
                <input type="checkbox" name="originReturnIsWMI" value="Y"/> Warsaw Airport Modlen<br>
                <input type="checkbox" name="originReturnIsWAW" value="Y"/> Warsaw Airport Chopin<br>
            </td>
            <td>|<br>|<br>|<br>|<br></td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5"><br></td>
            <td>|</td>
        </tr>
        <tr>
            <td>|</td>
            <td colspan="5">Set additional search filter:</td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5">
                <input name="startingFilter" type="radio" value="noFilter"> No filter
            </td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5">
                <input name="startingFilter" type="radio" value="countryFilter"> Trip start and end in same country
            </td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5">
                <input name="startingFilter" type="radio" value="cityFilter"> Trip start and end in same city
            </td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5">
                <input name="startingFilter" type="radio" value="airportFilter"> Trip start and end in same airport
            </td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5"><br></td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="2">
                Where we'll flight to?
            </td>
            <td>|</td>
            <td colspan="2">
                Where we'll return from?
            </td>
            <td>|</td>
        </tr>

        <tr>

            <td>|<br>|<br>|<br>|<br>|</td>
            <td valign="top"><button type="button" onclick="airportDirect()">+</button></td>
            <td valign="top">
                <select name="airportDestinationDirect1">
                    <c:forEach items="${allStartedAirports}" var="airport">
                        <option value="${airport.code}">${airport.country}  |  ${airport.city}  |  ${airport.code}</option>
                    </c:forEach>
                </select><br><div id="direct" style="border: 0px">
            </div>
            </td>
            <td></td>
            <td valign="top">
                <button type="button" onclick="airportReturn()">+</button>
            </td>
            <td valign="top">
                <select name="airportDestinationReturn1">

                    <c:forEach items="${allStartedAirports}" var="airport">
                        <option value="${airport.code}">${airport.country}  |  ${airport.city}  |  ${airport.code}</option>
                    </c:forEach>

                </select><br><div id="return" style="border: 0px"></div>
            </td>
            <td>|<br>|<br>|<br>|<br>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5"><br></td>
            <td>|</td>
        </tr>
        <tr>
            <td>|</td>
            <td colspan="5">Additional options:</td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5">
                <input name="endingFilter" type="radio" value="noFilter"> No filter
            </td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5">
                <input name="endingFilter" type="radio" value="countryFilter"> We flight to and return from the same country
            </td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5">
                <input name="endingFilter" type="radio" value="cityFilter"> We flight to and return from the same city
            </td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5">
                <input name="endingFilter" type="radio" value="airportFilter"> We flight to and return from the same airport
            </td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5"><br></td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="3">Set start date YYYY-MM-DD </td>
            <td colspan="2"> <input type="text" name="startingDate"></td>
            <td>|</td>
        </tr>
        <tr>

        </tr>

        <tr>
            <td>|</td>
            <td colspan="3">How deep to find(days)? </td>
            <td colspan="2"> <input type="text" name="dayCount"></td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="3">Min day of trip? </td>
            <td colspan="2"><input type="text" name="minDay"></td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="3">Max day of trip? </td>
            <td colspan="2"><input type="text" name="maxDay"></td>
            <td>|</td>
        </tr>

        <tr>
            <td>|</td>
            <td colspan="5"><br></td>
            <td>|</td>
        </tr>
        
        <tr>
            <td>|</td>
            <td colspan="5">
                <div style="text-align: center">
                    <input type="submit" value="ANANAS!" onclick="messageWarning()"/>
                </div>
            </td>
            <td>|</td>
        </tr>
        <tr>
            <td>|</td>
            <td colspan="5">__ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ </td>
            <td>|</td>
        </tr>
    </table>


</form>




<script>
    schotD = 1;

    function airportDirect() {
        schotD++;
        if (schotD < 6) {
            document.getElementById("direct").innerHTML += '<select name="airportDestinationDirect' + schotD + '"> <c:forEach items="${allStartedAirports}" var="airport">\n' +
                '                            <option value="${airport.code}">${airport.country}  |  ${airport.city}  |  ${airport.code}</option>\n' +
                '                        </c:forEach> </select><br>'
        } else {
            alert("Sorry, but impossible find more than 5 points one time!")
        }
    }

    schotR = 1;

    function airportReturn() {
        schotR++;
        if (schotR < 6) {
            document.getElementById("return").innerHTML += '<select name="airportDestinationReturn' + schotR + '"> <c:forEach items="${allStartedAirports}" var="airport">\n' +
                '                            <option value="${airport.code}">${airport.country}  |  ${airport.city}  |  ${airport.code}</option>\n' +
                '                        </c:forEach> </select><br>'
        } else {
            alert("Sorry, but impossible find more than 5 points one time!")
        }
    }

    function messageWarning() {
        alert("Please, do not refresh page before scanner will be ended!")
    }

</script>
<%@include file="include/adminfooter.jsp" %>
</body>
</html>
