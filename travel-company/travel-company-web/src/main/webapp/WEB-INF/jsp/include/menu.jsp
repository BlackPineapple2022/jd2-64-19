<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale scope="session" value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<div style="text-align: center">
    <br>
    <a href="${pageContext.request.contextPath}/about"><fmt:message key="menu.about"/></a> |
    <a href="${pageContext.request.contextPath}/allin"><fmt:message key="menu.allin"/></a> |
    <a href="${pageContext.request.contextPath}/spec"><fmt:message key="menu.spec"/></a>
    <br>
</div>

