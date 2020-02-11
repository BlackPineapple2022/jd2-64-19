<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<html >
<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | <fmt:message key="signup.page.title"/></title>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
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

<c:if test ="${errorCode==1}"><p style="color: red; text-align: center">Имя пользователя и пароль не должны быть пустыми</p></c:if>
<c:if test ="${errorCode==3}"><p style="color: red; text-align: center">Пользователь с таким именем уже существует</p></c:if>
<c:if test ="${errorCode==4}"><p style="color: red; text-align: center">Пароли не совпадают</p></c:if>
<c:if test ="${errorCode==5}"><p style="color: red; text-align: center">Доступ на сайт роботам запрещён</p></c:if>
<c:if test ="${errorCode==6}"><p style="color: red; text-align: center">Имя пользователя должно быть от 3 до 20 латинских символов или цифр, пароль от 5 до 20 любых символов</p></c:if>

<form method="POST" action="${pageContext.request.contextPath}/signup">
    <div align="center" style="font-size: 40px;font-family: Arial,serif; margin: 10px; padding-top: 20px">Регистрация</div>
    <table align="center" border="0" style="font-family: Arial,serif; font-size: 24px; padding: 10px; margin-bottom: 10px;margin-left: auto;margin-right: auto" >
        <tr>
            <td style="margin: 10px; padding: 10px">Имя пользователя


            </td>
            <td style="margin: 10px; padding: 10px"><input style="height: 30px; width: 300px; font-size: 24px; font-family: Arial,serif" type="text" name="userName" value="${userName}"/></td>

        </tr>

        <tr>
            <td colspan="2">
                <div style="color: #E55747; font-size: 18px; padding-left: 10px">
                    (Строчные или прописные латинские символы или цифры, от 3-х до 20-ти)
                </div>

            </td>
        </tr>

        <tr>
            <td style="margin: 10px; padding: 10px">Пароль</td>
            <td style="margin: 10px; padding: 10px"><input style="height: 30px; width: 300px; font-size: 24px; font-family: Arial,serif" type="password" name="password" value=""/></td>
        </tr>
        <tr>
            <td style="margin: 10px; padding: 10px">Повторите пароль</td>
            <td style="margin: 10px; padding: 10px"><input style="height: 30px; width: 300px; font-size: 24px; font-family: Arial,serif" type="password" name="passwordRepeat" value=""/></td>
        </tr>

        <tr>
        <td colspan="2">
            <div style="color: #E55747; font-size: 18px; padding-left: 10px; ">
                (Любые символы или цифры, от 5-ти до 20-ти)
            </div>

        </td>
        </tr>

        <tr>
            <td colspan="2" >
                <div style="padding-top: 20px; padding-left: 10px" class="g-recaptcha" data-sitekey="6Lfjm9YUAAAAANtQ7_03wFReNkPIFxhWD1YGx3Dz"></div>
            </td>

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
