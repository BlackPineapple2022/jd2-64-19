<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "custom" uri = "/WEB-INF/taglib/authAdmin.tld"%>

<fmt:setLocale scope="session" value="${param.lang}"/>
<fmt:setBundle basename="messages"/>


<div style="background: #000000;height: 60px; color:#8c62ff; text-align: center; padding: 15px; margin-top: 10px;">
<fmt:message key="footer.copyright"/>

</div>

<custom:authAdminTag path="/home">
<div style="background: red;height: 30px; color:black; text-align: center; padding: 15px; margin-top: 10px;">Вы вошли как Администратор</div>
</custom:authAdminTag>