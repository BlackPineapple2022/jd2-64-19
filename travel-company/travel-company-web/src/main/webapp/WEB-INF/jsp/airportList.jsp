<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | <fmt:message key="home.page.title" /></title>
</head>

<body>
<%@include file="include/header.jsp" %>
<%@include file="include/menu.jsp" %>

Airport list:

<table>
    <tr>
        <td>
            Id
        </td>
        <td>
            Airport_code
        </td>
        <td>
            Country
        </td>
        <td>
            City
        </td>
        <td>
            Delete?
        </td>
    </tr>
    <c:forEach items="${airports}" var="airport">
    <tr>
        <td>
            <c:out value="${airport.id}"/>
        </td>

        <td>
            <c:out value="${airport.code}"/>
        </td>
        <td>
            <c:out value="${airport.country}"/>
        </td>
        <td>
            <c:out value="${airport.city}"/>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/admin/airportDelete?id=${airport.id}">x</a>
        </td>
    </tr>
    </c:forEach>


</table>







<%@include file="include/footer.jsp" %>
</body>

</html>