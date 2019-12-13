<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale scope="session" value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${param.lang}">

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | <fmt:message key="home.page.title" /></title>
</head>

<body>

<%@include file="include/header.jsp" %>



<%@include file="include/footer.jsp" %>

</body>

</html>