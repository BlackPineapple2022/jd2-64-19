<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <title>BlackPineapple.by | admin Panel</title>
</head>

<body>
<%@include file="include/adminheader.jsp" %>

<form method="POST" action="${pageContext.request.contextPath}/admin/scanner/ry/start">
    <input type="submit" value="Ry Start"/>
</form>

<form method="POST" action="${pageContext.request.contextPath}/admin/scanner/ry/deactivate">
    <input type="submit" value="Ry Deactivate"/>
</form>

<form method="POST" action="${pageContext.request.contextPath}/admin/scanner/wizz/start">
    <input type="submit" value="WIZZ Start"/>
</form>

<form method="POST" action="${pageContext.request.contextPath}/admin/scanner/wizz/deactivate">
    <input type="submit" value="WIZZ deactivate"/>
</form>

</body>

</html>