<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<html >
<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | <fmt:message key="login.page.title"/></title>
</head>
<body>
<%@include file="include/header.jsp" %>
<c:if test ="${errorCode==1}"><p style="color: red; text-align: center"><fmt:message key="error.code.1"/></p></c:if>
<c:if test ="${errorCode==2}"><p style="color: red; text-align: center"><fmt:message key="error.code.2"/></p></c:if>
<form method="POST" action="${pageContext.request.contextPath}/login">
    <p align="center"><fmt:message key="login.page.title"/>:</p>
    <table align="center" border="0" bord >
        <tr>
            <td><fmt:message key="login.page.user.name"/></td>
            <td><input type="text" name="userName" value="${userName}"/></td>
        </tr>
        <tr>
            <td><fmt:message key="login.page.user.password"/></td>
            <td><input type="password" name="password" value=""/></td>
        </tr>
        <tr>
            <td><fmt:message key="login.page.user.remember"/></td>
            <td><input type="checkbox" name="rememberMe" value="Y"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<fmt:message key="login.page.submit"/>"/>
                <a href="${pageContext.request.contextPath}/"><fmt:message key="login.page.cancel"/>
                </a>
            </td>
        </tr>

    </table>
</form>

<p align="center">Admin - username: black ; password: 123</p>
<p align="center">Registered user - username: white ; password: 123</p>

<%@include file="include/footer.jsp" %>
</body>
</html>
