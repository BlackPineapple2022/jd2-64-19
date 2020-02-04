<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setBundle basename="messages"/>
<style>

    .a-head {
        outline: none;
        border-radius: 5px;
        border: solid #FFFFFF 1px;
        font-family: 'Google Sans', Roboto, Arial, sans-serif;

        color: #FFFFFF;
        font-size: 24px;
        background: #303030;
        padding: 8px 20px 8px 20px;
        text-decoration: none;
        margin-top: 20px;
        margin-bottom: 20px;
        margin-left: 2px;
        margin-right: 2px;
    }

    .a-head:hover {
        border: solid #FFFFFF 1px;
        color: #303030;
        background: #FFFFFF;
    }
</style>
<div style="background: #303030; padding-bottom: 30px; alignment: center; text-align: center">
    <a href="${pageContext.request.contextPath}/about" class="a-head">О проекте </a> <a href="${pageContext.request.contextPath}/specific" class="a-head">Поиск авиабилетов </a> <a href="${pageContext.request.contextPath}/all" class="a-head">Всё и сразу</a>
</div>



