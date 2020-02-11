<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setBundle basename="messages"/>

<style>

    .a-head {
        outline: none;
        border-radius: 5px;
        border: solid #F2BD1D 1px;
        font-family: 'Google Sans', Roboto, Arial, sans-serif;

        color: #F2BD1D;
        font-size: 24px;
        background: #404040;
        padding: 8px 20px 8px 20px;
        text-decoration: none;
        margin-top: 20px;
        margin-bottom: 20px;
        margin-left: 2px;
        margin-right: 2px;
    }

    .a-head.green {
        color: #88D94E;
        border: solid #88D94E 1px;
    }

    .a-head:hover {
        border: solid #F2BD1D 1px;
        color: #404040;
        background: #F2BD1D;
    }

    .a-head.green:hover {
        border: solid #88D94E 1px;
        color: #404040;
        background: #88D94E;
    }

    .a-head.red {
        color: #E55747;
        border: solid #E55747 1px;
    }

    .a-head.red:hover {
        border: solid #E55747 1px;
        color: #404040;
        background: #E55747;
    }

</style>
<div style="background: #404040; padding-bottom: 30px; alignment: center; text-align: center">
    <a href="${pageContext.request.contextPath}/about" class="a-head">О проекте </a> <a
        href="${pageContext.request.contextPath}/specific" class="a-head">Поиск авиабилетов </a> <a
        href="${pageContext.request.contextPath}/user/favourite" class="a-head green">Избранное</a><a
        href="${pageContext.request.contextPath}/user/favourite/updateInfo" class="a-head red">Обновление цен</a>
</div>



