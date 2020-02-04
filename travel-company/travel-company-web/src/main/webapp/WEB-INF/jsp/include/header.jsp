<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${param.lang != null}">
    <% session.setAttribute("lang", request.getParameter("lang")); %>
</c:if>

<c:if test="${sessionScope.lang != null}">
    <fmt:setLocale value="${sessionScope.lang}"/>
</c:if>

<fmt:setBundle basename="messages"/>

<style>

    .a-top{
        border: #FFFFFF 1px solid;
        border-radius: 3px;
        padding: 5px;
        margin-top: 20px;
        margin-bottom: 20px;
        margin-right: 5px;
        margin-left: 5px;
        color: #FFFFFF;
        font-size: 18px;
        text-decoration: none;
        font-family: Arial,serif;
    }

    .a-top:hover{
        border: #FFFFFF 1px solid;
        color: #303030;
        background: #FFFFFF;
    }

</style>


<div style="background: #303030; color: #FFFFFF; text-align: right; padding-top: 20px; padding-right: 5px">

    <c:if test="${user == null}"><a class="a-top" href="${pageContext.request.contextPath}/login"> Войти </a>
        <a class="a-top" href="${pageContext.request.contextPath}/signup"> Регистрация </a>
    </c:if>


    <%--<a href="?lang=en"> en </a> |
    <a href="?lang=ru"> ru </a>--%>
</div>

<div style="background:#303030; text-align: right; font-size: 18px; text-decoration: none; font-family: Arial,serif; padding: 10px; color: #FFFFFF">

    <c:if test="${user != null && user.role eq 'ADMIN'}">
        <a class="a-top" href="${pageContext.request.contextPath}/admin">
            admin Panel
        </a>
    </c:if>

    <c:if test="${user != null}">Добро пожаловать, <b>${user.userName}</b>
        <a class="a-top" href="${pageContext.request.contextPath}/logout">Выход</a>
    </c:if>

</div>



<div style="background: #303030; text-align: center; padding-top: 10px; padding-bottom: 30px">
    <a href="${pageContext.request.contextPath}/home">
        <img src="${pageContext.request.contextPath}/resources/pic/logoblack.png" width="800px">
    </a>
</div>















