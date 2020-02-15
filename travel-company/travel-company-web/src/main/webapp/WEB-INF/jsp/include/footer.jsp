<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "custom" uri = "/WEB-INF/taglib/authAdmin.tld"%>

<fmt:setBundle basename="messages"/>

<style>

    .div-footer-info{
        text-align: left;
        font-size: 16px;
        font-family: Arial, serif;
        padding: 5px;
        color: #C5c5c5;
        line-height: 1.5;

    }


    .div-register-footer {
        text-align: center;
        font-size: 50px;
        font-family: Arial, serif;
        padding-top: 20px;
        padding-bottom: 20px;
        color: #F2BD1D;
        background: #404040;
    }

    a{
        outline: none;
        /*color: #F2BD1D;*/
        text-decoration: none;
    }

    .a-reg-footer {
        outline: none;
        border-radius: 5px;
        border: solid #F2BD1D 1px;
        font-family: Arial, serif;

        color: #F2BD1D;
        font-size: 30px;
        background: #404040;
        padding: 16px 50px 16px 50px;
        text-decoration: none;
        margin-top: 20px;
        margin-bottom: 20px;
        margin-left: 20px;
        margin-right: 5px;
    }

    .a-reg-footer:hover {
        border: solid #404040 1px;
        color: #404040;
        /*background: #1E96FA;*/
        background: #F2BD1D
    }

    .a-reg-footer.a-log-footer {
        padding: 16px 100px 16px 100px;
        color: #88D94E;
        border: solid #88D94E 1px;
    }

    .a-reg-footer.a-log-footer:hover {
        border: solid #404040 1px;
        color: #404040;
        /*background: #1E96FA;*/
        background: #88D94E
    }

</style>




<%--<c:if test="${user == null}">
<div class="div-register-footer" >
    <tr>
        <td>
            <div style="padding-top: 20px">
            <a class="a-reg-footer a-log-footer" href="${pageContext.request.contextPath}/login">Вход </a>

            <a class="a-reg-footer " href="${pageContext.request.contextPath}/signup">Регистрация </a>
            </div>
        </td>
    </tr>

</div>
</c:if>--%>

<div style="background: #404040; font-size: 18px; color:#F2BD1D; text-align: center; padding: 15px; font-family: 'Google Sans', Roboto, Arial, sans-serif;">

    © Чёрный Ананас, 2019-2020



</div>

<div class="div-footer-info" style="background: #404040">
    <table style="margin-right: auto; margin-left: auto">
        <tr>
            <td style="padding: 30px; color:#C5c5c5 " width="300px" valign="top">
                <b>Чёрный ананас</b> - полностью бесплатен для использования,
                это некоммерческий проект для поиска и планирования
                авиапутешествий по Европе.
            </td>
            <td style="padding: 30px; color:#C5c5c5 " width="300px" valign="top">

            Мы <b>не</b> продаём билеты на самолёты,
                не бронируем гостиницы и не рекламируем тех,
                кто это делает.
            </td>
            <td style="padding: 30px; color:#C5c5c5 " width="300px" valign="top">

        Все риски, связанные с покупкой билетов, проживанием,
                перелётами, стыковками и другими элементами путешействий
                несёт конечный потребитель.
            </td>
        </tr>

        <tr>
            <td colspan="3" style="padding: 30px; text-align: right; color: #F2BD1D" >
                Идея, разработка и внедрение
                <a href="https://www.linkedin.com/in/duuubbb/">
                    <img src="${pageContext.request.contextPath}/resources/pic/linkedin.png" width="28px" />
                </a>
            </td>
        </tr>

    </table>

</div>
