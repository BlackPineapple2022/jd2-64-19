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
            border: solid #303030 1px;
            font-family: Arial, serif;

            color: #303030;
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
            /*background: #1E96FA;*/
            background: #303030
        }

        .a-log {
            padding: 16px 100px 16px 100px;
        }

        .my_button3 {
             outline: none;
             border: solid #ffffff 1px;
             border-radius: 5px;
             font-family: 'Google Sans', Roboto, Arial, sans-serif;
             color: #ffffff;
             font-size: 22px;
             background: #66A33B;
             padding: 6px 7px 6px 7px;
             text-decoration: none;
             margin-top: 20px;
             margin-bottom: 20px;
         }

        .my_button3:hover {
            border: solid #66A33B 1px;
        }

        .my_button4 {
            outline: none;
            border: solid #66A33B 1px;
            border-radius: 5px;
            font-family: 'Google Sans', Roboto, Arial, sans-serif;
            color: #66A33B;
            font-size: 22px;
            background: #FFFFFF;
            padding: 6px 7px 6px 7px;
            text-decoration: none;
            margin-top: 20px;
            margin-bottom: 20px;
        }



    </style>

    <script>

        function update(){
            var x = document.getElementById("button");
            x.style.display = "none";
            var y = document.getElementById("button1");
            y.style.display = "inline";
            var t = document.getElementById("warning");
            t.style.display = "inline";
        }

    </script>


</head>


<body>

<%@include file="include/header.jsp" %>
<%@include file="include/menu.jsp" %>

<div class="welcome">Обновление цен

</div>

<c:if test="${trips == null}">
    <br/>
    <br/>
    <div class="welcome" style="color: #E55747;font-size: 26px; border: #E55747 1px solid; border-radius: 5px; margin-right: 20%; margin-left: 20%; padding: 20px">
        К сожалению, список избранного пуст, нечего обновлять, воспользуйтесь <b><a
            href="${pageContext.request.contextPath}/specific" style="color: #66A33B">поиском</a></b>, чтобы это исправить
    </div>
    <br/>
    <br/>
    <br/>
</c:if>

<c:if test="${trips != null}">
<div class="welcome"
     style="color: #505050;font-size: 26px; border: #505050 1px solid; border-radius: 5px; margin-right: 20%; margin-left: 20%; padding: 20px">
    Наши роботы стараются обновлять цены на Ваши поиски как можно чаще,
    как правило все цены обновляются раз в двое суток.
<br/>
<br/>
    <a class="my_button3" id="button" href="${pageContext.request.contextPath}/updateNow" style="alignment: right" onclick="update()">
        Обновить цены сейчас (займёт дополнительное время)
    </a>

    <a class="my_button4" id="button1"  style="alignment: right; display: none"  >
        Идёт обновление цен
    </a>
    <br/>
    <br/>

    <div id="warning" style="color: #E55747; display: none">
        Обновление цен требует выхода в сеть и отправки сотен запросов. Наши сервера работают
    на ананасах и чёрных дырах, и эта задача решается мгновенно!, чего, к сожалению, не скажешь про авиакомпании, по-этому
    необходимо подождать пару минут.
        <br/>
        <br/>
        <b>НЕ ОБНОВЛЯЙТЕ СТРАНИЦУ, ПОСЛЕ ОБРАБОТКИ ВЫ БУДЕТЕ ПЕРЕНАПРАВЛЕНЫ АВТОМАТИЧЕСКИ!</b>
    </div>
</div>
<br/>
<br/>
</c:if>


<%@include file="include/footer.jsp" %>
</body>

</html>