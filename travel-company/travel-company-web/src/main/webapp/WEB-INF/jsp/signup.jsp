<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<html >
<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | <fmt:message key="signup.page.title"/></title>
    <style>

        .my_button-reg {
            outline: none;
            border: solid #ffffff 1px;
            border-radius: 5px;
            font-family: 'Google Sans', Roboto, Arial, sans-serif;
            color: #ffffff;
            font-size: 22px;
            background: #66A33B;
            padding: 6px 50px 6px 50px;
            text-decoration: none;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        .my_button-reg:hover {
            border: solid #66A33B 1px;
        }
    </style>
</head>
<body>
<%@include file="include/header.jsp" %>
<%@include file="include/menu.jsp" %>

<c:if test ="${errorCode==1}"><p style="color: red; text-align: center"><fmt:message key="error.code.1"/></p></c:if>
<c:if test ="${errorCode==3}"><p style="color: red; text-align: center"><fmt:message key="error.code.3"/></p></c:if>
<c:if test ="${errorCode==4}"><p style="color: red; text-align: center"><fmt:message key="error.code.4"/></p></c:if>

<form method="POST" action="${pageContext.request.contextPath}/signup">
    <div align="center" style="font-size: 40px;font-family: Arial,serif; margin: 10px; padding-top: 20px">Регистрация</div>
    <table align="center" border="0" style="font-family: Arial,serif; font-size: 24px; padding: 10px; margin-bottom: 10px;margin-left: auto;margin-right: auto" >
        <tr>
            <td style="margin: 10px; padding: 10px">Имя пользователя</td>
            <td style="margin: 10px; padding: 10px"><input style="height: 30px; width: 300px; font-size: 24px; font-family: Arial,serif" type="text" name="userName" value="${userName}"/></td>
        </tr>

        <tr>
            <td style="margin: 10px; padding: 10px"><fmt:message key="signup.page.user.password"/></td>
            <td style="margin: 10px; padding: 10px"><input style="height: 30px; width: 300px; font-size: 24px; font-family: Arial,serif" type="password" name="password" value=""/></td>
        </tr>
        <tr>
            <td style="margin: 10px; padding: 10px"><fmt:message key="signup.page.user.password.repeat"/></td>
            <td style="margin: 10px; padding: 10px"><input style="height: 30px; width: 300px; font-size: 24px; font-family: Arial,serif" type="password" name="passwordRepeat" value=""/></td>
        </tr>


        <tr>
            <td colspan="2" style="text-align: center">
                <input class="my_button-reg" style="text-align: center" type="submit" value="Регистрация"/>
            </td>
        </tr>

    </table>
</form>

<%@include file="include/footer.jsp" %>
</body>
</html>
