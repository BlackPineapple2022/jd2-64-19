<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | airport Manager </title>
</head>

<body>
<style type="text/css">
    .id {
        width: 20px;
    }

    .airportCode {
         width: 30px;
    }
    .airportCountry {
        width: 150px;
    }
    .airportCity {
        width: 150px;
    }

</style>

<%@include file="include/adminheader.jsp" %>

<div align="center" style="font-size: 30px"><b>AIRPORT MANAGER</b></div>

<table align="center" style="text-align: center">

    <tr>
        <td></td>
        <td>__ __ __ __ __</td>
        <td></td>
        <td>__ __ __ __ __</td>
        <td></td>
        <td>__ __ __ __ __</td>
        <td></td>
        <td>__ __ __ __ __</td>
        <td></td>
        <td>__ __ __ __ __</td>
        <td></td>
    </tr>
    <tr>
        <td> |</td>
        <td colspan="9">
            <b>LIST OF AIRPORT:</b>
        </td>
        <td> |</td>
    </tr>

    <tr>
        <td> |</td>
        <td> Airport id</td>
        <td> |</td>
        <td> Airport code</td>
        <td> |</td>
        <td> Airport country</td>
        <td> |</td>
        <td> Airport city</td>
        <td> |</td>
        <td> Delete?</td>
        <td> |</td>
    </tr>


    <c:forEach items="${airportList}" var="airport">
        <tr>
            <td> |</td>
            <td><c:out value="${airport.id}"/></td>
            <td> |</td>
            <td><c:out value="${airport.code}"/></td>
            <td> |</td>
            <td><c:out value="${airport.country}"/></td>
            <td> |</td>
            <td><c:out value="${airport.city}"/></td>
            <td> |</td>
            <td><a href="${pageContext.request.contextPath}/admin/manager/airport/delete?id=${airport.id}">[x]</a>
            </td>
            <td> |</td>
        </tr>
    </c:forEach>
    <tr>
        <td> |</td>
        <td colspan="9">

        </td>
        <td> |</td>
    </tr>
    <tr>
        <td> |</td>
        <td colspan="9">
            <b>ADD AIRPORT TO BASE</b>
        </td>
        <td> |</td>
    </tr>
    <tr>
        <td> |</td>
        <td colspan="9">

        <form method="POST" action="${pageContext.request.contextPath}/admin/manager/airport/add">
            Code:
            <input type="text" name="airportCode" class="airportCode" value=""/>
            Country:
            <input type="text" name="airportCountry" class="airportCountry" value=""/>
            City:
            <input type="text" name="airportCity" class="airportCity" value=""/>
            <input type="submit" value="add"/>
        </form>


        </td>
        <td> |</td>
    </tr>
    <tr>
        <td> |</td>
        <td colspan="9">

        </td>
        <td> |</td>
    </tr>
    <tr>
        <td> |</td>
        <td colspan="9">
            <b>UPDATE AIRPORT IN BASE</b>
        </td>
        <td> |</td>
    </tr>

    <tr>
        <td> |</td>
        <td colspan="9">

            <form method="POST" action="${pageContext.request.contextPath}/admin/manager/airport/update">
                id:
                <input type="text" name="id" class="id" value=""/>
                Code:
                <input type="text" name="airportCode" class="airportCode" value=""/>
                Country:
                <input type="text" name="airportCountry" class="airportCountry" value=""/>
                City:
                <input type="text" name="airportCity" class="airportCity" value=""/>
                <input type="submit" value="update"/>
            </form>


        </td>
        <td> |</td>
    </tr>

    <tr>
        <td> |</td>
        <td>__ __ __ __ __</td>
        <td></td>
        <td>__ __ __ __ __</td>
        <td></td>
        <td>__ __ __ __ __</td>
        <td></td>
        <td>__ __ __ __ __</td>
        <td></td>
        <td>@airport manager</td>
        <td> |</td>
    </tr>

    <tr>
        <td colspan="11" style="text-align: left">For installing all airport to base:</td>
    </tr>
    <tr>
        <td colspan="11" style="text-align: left">Input "INSTALL" into field and press button</td>
    </tr>
    <tr>
        <td colspan="11" style="text-align: left;color: red">All airport in base will be removed and installed default</td>
    </tr>

    <tr>
        <td colspan="11" style="text-align: left">
            <form method="POST" action="${pageContext.request.contextPath}/admin/manager/airport/install">
                <input type="text" name="install" value=""/>
                <input type="submit" value="install"/>
                @danger
            </form>
        </td>
    </tr>



    <%--<tr>
        <td> |</td>
        <td colspan="5">
            <b>ADD AIRLINE TO BASE</b>
        </td>
        <td> |</td>
    </tr>
    <tr>
        <td> |</td>
        <td colspan="5">
            <form method="POST" action="${pageContext.request.contextPath}/admin/manager/airline/add">
                <input type="text" name="airlineName" value=""/>
                <input type="submit" value="add"/>
            </form>
        </td>
        <td> |</td>
    </tr>
    <tr>
        <td> |</td>
        <td colspan="5">
            <b>UPDATE AIRLINE IN BASE</b>
        </td>
        <td> |</td>
    </tr>
    <tr>
        <td> |</td>
        <td colspan="5">
            <form method="POST" action="${pageContext.request.contextPath}/admin/manager/airline/update">
                id:<input type="text" class="id" name="id" value=""/>
                new airline name
                <input type="text" class="airlineName" name="airlineName" value=""/>
                <input type="submit" value="update"/>
            </form>
        </td>
        <td> |</td>
    </tr>
    <tr>
        <td> |</td>
        <td>__ __ __ __ __</td>
        <td></td>
        <td>__ __ __ __ __</td>
        <td></td>
        <td>@airline manager</td>
        <td> |</td>
    </tr>
    <tr>
        <td colspan="7" style="text-align: left">For installing all airlines to base:</td>
    </tr>
    <tr>
        <td colspan="7" style="text-align: left">Input "INSTALL" into field and press button</td>
    </tr>
    <tr>
        <td colspan="7" style="text-align: left;color: red">All airline in base will be removed and installed default</td>
    </tr>

    <tr>
        <td colspan="7" style="text-align: left">
            <form method="POST" action="${pageContext.request.contextPath}/admin/manager/airline/install">
                <input type="text" name="install" value=""/>
                <input type="submit" value="install"/>
                @danger
            </form>
        </td>
    </tr>--%>

</table>


</body>

</html>