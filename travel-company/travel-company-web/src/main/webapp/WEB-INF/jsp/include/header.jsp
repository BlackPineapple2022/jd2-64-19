<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale scope="session" value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<div style="text-align: right; font-size: 14px">
        <a href="${pageContext.request.contextPath}/login"> <fmt:message key="login.login"/> </a> |
        <a href="${pageContext.request.contextPath}/signup"> <fmt:message key="sign-up.sign-up"/> </a>|
        <a href="?lang=en"> en </a> |
        <a href="?lang=ru"> ru </a>

</div>

<div style="background: #FFFCFC; text-align: center; height: 350px">
       <img src="${pageContext.request.contextPath}/resources/pic/logo.png">

</div>


<div style="background:#FFFCFC; color: #8c52ff; float: right; padding: 15px; text-align: right;">

    <!-- User store in session with attribute: loginedUser -->
    <c:if test="${user != null}"> Hello <b>${user.userName}</b>
        <a href="${pageContext.request.contextPath}/logout">logout</a>
    </c:if>
</div>



