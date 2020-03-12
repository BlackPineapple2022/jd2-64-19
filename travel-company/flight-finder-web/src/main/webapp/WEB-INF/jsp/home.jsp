<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WELCOME TO FLIGHT FINDER WEB MODULE</title>
</head>
<body>
WELCOME TO FLIGHT FINDER WEB MODULE<br/>

<form action="${pageContext.request.contextPath}/startRY" method="post">
    day count <input type="text" name="dayCount"/><br/>
    timeout millisec <input type="text" name="timeout"/><br/>
    timeout multiplier {1+random[0..1]*multiplier} <input type="text" name="multiplier"/><br/>
    <input type="submit" value="START RYANAIR SCANNER">
</form>

<form action="${pageContext.request.contextPath}/stopRY" method="post">
    <input type="submit" value="STOP RYANAIR SCANNER">
</form>

<form action="${pageContext.request.contextPath}/startWIZZ" method="post">
    day count <input type="text" name="dayCount"/><br/>
    timeout millisec <input type="text" name="timeout"/><br/>
    timeout multiplier {1+random[0..1]*multiplier} <input type="text" name="multiplier"/><br/>
    <input type="submit" value="START WIZZAIR SCANNER">
</form>

<form action="${pageContext.request.contextPath}/stopWIZZ" method="post">
    <input type="submit" value="STOP WIZZAIR SCANNER">
</form>

<form action="${pageContext.request.contextPath}/wizzapi" method="post">
    wizz api<input type="text" name="apiAddress">
    <input type="submit" value="update WIZZAIR api">
</form>

</body>

</html>
