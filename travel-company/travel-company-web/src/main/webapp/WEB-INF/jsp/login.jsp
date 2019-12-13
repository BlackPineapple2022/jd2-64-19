<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale scope="session" value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${param.lang}">
<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | <fmt:message key="login.page.title"/></title>
</head>
<body>
<%@include file="include/header.jsp" %>
<form method="POST" action="${pageContext.request.contextPath}/login">
    <p align="center"><fmt:message key="login.page.title"/>:</p>
    <table align="center" border="0" bord >
        <tr>
            <td><fmt:message key="login.page.user.name"/></td>
            <td><input type="text" name="userName" value="${user.userName}"/></td>
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


<%@include file="include/footer.jsp" %>
</body>
</html>
