<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale scope="session" value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>BlackPineapple.by | <fmt:message key="addFlight.page.title"/></title>
</head>
<body>
<%@include file="include/header.jsp" %>

<h2 align="center"><fmt:message key="addFlight.page.content.header"/></h2>
<div style=" text-align: center; align-items: center; align-content: center;alignment: center"/>
<form method="post" action="${pageContext.request.contextPath}/addFlight">

    <select name="airportOrigin">

        <c:forEach items="${allStartedAirports}" var="airport">
            <option value="${airport.code}">${airport}</option>
        </c:forEach>

    </select>
    <input style="align-content: center" type="submit" value="<fmt:message key="addFlight.page.submit"/>"/>
</form>
</div>

<%@include file="include/footer.jsp" %>
</body>
</html>
