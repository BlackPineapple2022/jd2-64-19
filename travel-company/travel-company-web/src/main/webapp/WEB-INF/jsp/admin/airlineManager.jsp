<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | airline Manager </title>
</head>

<body>
<style type="text/css">
    .id {
        width: 20px;
    }
</style>
<style type="text/css">
    .airlineName {
        width: 80px;
    }
</style>
<%@include file="include/adminheader.jsp" %>

<div align="center" style="font-size: 30px"><b>AIRLINE MANAGER</b></div>

<table align="center" style="text-align: center">

    <tr>
        <td></td>
        <td colspan="5">__ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __</td>

        <td></td>
    </tr>
    <tr>
        <td> |</td>
        <td colspan="5">
            <b>LIST OF AIRLINE:</b>
        </td>
        <td> |</td>
    </tr>

    <tr>
        <td> |</td>
        <td> Airline id</td>
        <td> |</td>
        <td> Airline name</td>
        <td> |</td>
        <td> Delete?</td>
        <td> |</td>
    </tr>


    <c:forEach items="${airlineList}" var="airline">
        <tr>
            <td> |</td>
            <td><c:out value="${airline.id}"/></td>
            <td> |</td>
            <td><c:out value="${airline.airlineName}"/></td>
            <td> |</td>
            <td><a href="${pageContext.request.contextPath}/admin/manager/airline/delete?id=${airline.id}">[x]</a>
            </td>
            <td> |</td>
        </tr>
    </c:forEach>

<tr>
    <td> |</td>
        <td colspan="5"></td>
    <td> |</td>
</tr>
    <tr>
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
        <td colspan="5"></td>
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
        <td colspan="5">__ __ __ __ __ __ __ __ __ __ __ __ __ @airline manager </td>

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
    </tr>

</table>

<%@include file="include/adminfooter.jsp" %>
</body>

</html>