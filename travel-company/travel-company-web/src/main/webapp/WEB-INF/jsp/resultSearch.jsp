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

<c:forEach items="${trips}" var="trip">
    <p>${trip}</p>
</c:forEach>





<%@include file="include/footer.jsp" %>
</body>

</html>