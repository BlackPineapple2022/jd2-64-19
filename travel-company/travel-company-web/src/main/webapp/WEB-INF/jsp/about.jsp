<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages"/>

<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | О проекте</title>

    <style>

        body {
            background: #FFFFFF;
        }

        .welcome {
            padding: 10px;
            font-family: Arial, serif;
            font-size: 50px;
            text-align: center;
            color: #000000;
            margin: 10px;
        }

        .div-top {
            font-family: "Times New Roman", Arial, serif;
            padding-top: 20px;
            padding-bottom: 20px;
            padding-right: 10px;
            padding-left: 10px;

            margin-right: auto;
            margin-left: auto;


        }

        .tr1 {

            padding: 10px;
            border-right: #dcdcdc 1px solid;
            border-top: #dcdcdc 1px solid;
            border-bottom: #dcdcdc 1px solid;

            border-top-right-radius: 5px;
            border-bottom-right-radius: 5px;

        }

        .tr2 {

            padding: 10px;
            border-left: #dcdcdc 1px solid;
            border-top: #dcdcdc 1px solid;
            border-bottom: #dcdcdc 1px solid;

            border-top-left-radius: 5px;
            border-bottom-left-radius: 5px;

        }

        .div-middle {
            font-family: "Times New Roman", Arial, serif;
            padding-top: 10px;
            padding-bottom: 10px;

            margin-left: 20%;
            margin-right: 20%;
        }

        .div-register {
            text-align: center;
            font-size: 50px;
            font-family: Arial, serif;
            padding-top: 20px;
            padding-bottom: 20px;
            color: #FFFFFF;
            background: #FFFFFF;
            margin-bottom: 30px;
        }


        .a-reg {
            outline: none;
            border-radius: 5px;
            border: solid #1E96FA 1px;
            font-family: Arial, serif;

            color: #1E96FA;
            font-size: 30px;
            background: #FFFFFF;
            padding: 16px 50px 16px 50px;
            text-decoration: none;
            margin-top: 20px;
            margin-bottom: 20px;
            margin-left: 20px;
            margin-right: 5px;
        }

        .a-reg:hover {
            border: solid #FFFFFF 1px;
            color: #FFFFFF;
            background: #1E96FA;
        }

        .a-log {
            padding: 16px 100px 16px 100px;
        }

    </style>

</head>


<body>

<%@include file="include/header.jsp" %>
<%@include file="include/menu.jsp" %>

<div class="welcome">Добро пожаловать на <b>BlackPineapple.by</b></div>


<div class="div-top">
    <table class="tr1" align="center">

        <tr>
            <td width="300px"><img src="${pageContext.request.contextPath}/resources/pic/What.png" width="300px">
            </td>
            <td colspan="2" width="600px" align="justify">
                <div style="font-size: 44px; font-family: Arial,serif">Что такое лоукосты?</div>

                <div style="font-size: 24px; font-family: Arial,serif; line-height: 1.5 ">
                    Лоукосты или лоукостеры - это авиакомпании, которые продают билеты на свои рейсы за суммы,
                    которые в
                    разы, а то и в десятки раз меньше, чем у основных национальных авиаперевозчиков.
                    Лоукосты существуют в Европе, Азии, Америке.
                    Как правило они продают билеты без багажа, питания и возможности выбора места.
                    Эти и другие услуги оказываются за дополнительную плату.
                    Основные лоукост компании в Европе - это <b>Ryanair</b> и <b>Wizzair</b>.
                </div>
            </td>
        </tr>
    </table>
</div>


<div class="div-middle">
    <table align="center" class="tr2">
        <tr>
            <td colspan="2" width="600px" align="justify">
                <div style="font-size: 44px; font-family: Arial,serif">Что такое Чёрный Ананас?</div>
                <br>
                <div style="font-size: 24px; line-height: 1.5; font-family: Arial,serif">
                    <b>Чёрный ананас</b> - это мощный сервис по планированию авиапутешествий по Европе с вылетом из
                    Литвы и Польши, что очень актуально для беларусов.
                    Вы можете искать дешёвые авиабилеты с отправлением и возвратом в аэропорты Вильнюса, Каунаса и
                    Варшавы: Модлина и Шопена.
                    Возможен одновременный поиск более чем по 120 направлениям.
                    Зарегистрированные пользователи могут сохранять найденные билеты и обновлять цены прямо из личного
                    кабинета.
                    Система фильтров по начальным и конечным точкам позволит планировать по настоящему увлекательные
                    путешествия!
                </div>
            </td>
            <td width="300px"><img src="${pageContext.request.contextPath}/resources/pic/pineapple_about.png"
                                   width="300px">
            </td>
        </tr>
    </table>


</div>

<div class="div-register">
    <tr>
        <td>
            <a class="a-reg" href="${pageContext.request.contextPath}/signup">Регистрация </a>
            <a class="a-reg a-log" href="${pageContext.request.contextPath}/login">Вход </a>
        </td>
    </tr>

</div>
<%@include file="include/footer.jsp" %>
</body>

</html>