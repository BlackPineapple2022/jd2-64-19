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

    <!-- User store in session with attribute: loginedUser -->
        <c:if test="${user != null}"> <fmt:message key="label.welcome"/>  <b>${user.userName}</b>

        <a href="${pageContext.request.contextPath}/logout">logout</a>
    </c:if>
</div>

<c:if test="${user != null && user.role eq 'admin'}">
    <div style="background:pink;height: 40px; color:black; text-align: center; padding: 15px; margin-top: 10px;">

        <a href="${pageContext.request.contextPath}/addFlight">Добавить произвольный перелёт</a> |
        <a href="${pageContext.request.contextPath}/addFlightTo">Добавить стартовый перелёт</a> |
        <a href="${pageContext.request.contextPath}/addFlightFrom">Добавить обратный перелёт</a> |

    </div>

</c:if>

<div style="background:#FFFCFC; text-align: right; font-size: 14px; padding: 30px;" >
    <a href="${pageContext.request.contextPath}/login"> <fmt:message key="login.login"/> </a> |
    <a href="${pageContext.request.contextPath}/signup"> <fmt:message key="sign-up.sign-up"/> </a>|
    <a href="?lang=en"> en </a> |
    <a href="?lang=ru"> ru </a>

</div>

<div style="background: #FFFCFC; text-align: center; height: 350px">
    <a href="${pageContext.request.contextPath}/home">
        <img src="${pageContext.request.contextPath}/resources/pic/logo.png">
    </a>
</div>

<div style="background: #5371ff; height:5px"></div>






