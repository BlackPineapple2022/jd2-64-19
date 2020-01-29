<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${param.lang != null}">
    <% session.setAttribute("lang",request.getParameter("lang")); %>
</c:if>

<c:if test="${sessionScope.lang != null}">
    <fmt:setLocale value="${sessionScope.lang}"/>
</c:if>

<fmt:setBundle basename="messages"/>

<div style="background:#FFFCFC; color: #000000; float: right; padding: 5px; padding-right: 30px; text-align: right;">

    <c:if test="${user != null && user.role eq 'ADMIN'}">
        <a href="${pageContext.request.contextPath}/admin">[<img src="${pageContext.request.contextPath}/resources/pic/smallBlackPineApple.png" width="18px">]</a>

    </c:if>

    <c:if test="${user != null}"> Добро пожаловать  <b>${user.userName}</b>

        <a href="${pageContext.request.contextPath}/logout">Выход</a>

    </c:if>

</div>

<div style="background:#FFFCFC; text-align: right; font-size: 14px; padding: 40px;" >
    <a href="${pageContext.request.contextPath}/login"> Войти </a> |
    <a href="${pageContext.request.contextPath}/signup"> Регистрация </a>|
    <%--<a href="?lang=en"> en </a> |
    <a href="?lang=ru"> ru </a>--%>

</div>

<div style="background: #FFFCFC; text-align: center; height: 350px">
    <a href="${pageContext.request.contextPath}/home">
        <img src="${pageContext.request.contextPath}/resources/pic/logo.png">
    </a>
</div>


<div style="background: #5371ff; height:2px"></div>













