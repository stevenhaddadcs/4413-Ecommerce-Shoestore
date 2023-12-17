<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
 	<jsp:include page="header.jsp" flush="true" />
 	<jsp:include page="leftColumn.jsp" flush="true" />
 	<form method = "post" action = "LoginController">
    <div>
      	 <input type = "hidden" name = "action" value = "login"/>
         <span class="label" style="margin-left: 15px;"> Login or Register
         </span>
    </div>
    <c:if test="${requestScope.loginFail == 'true'}">
    <br>
    <span style="margin-left: 15px; font-size:30px"> INVALID USERNAME OR PASSWORD </span>
    </c:if>
    <br><br>
	<span style="margin-left: 15px"> Username: </span>
	<input type = "text" style="width: 200px;" name="username" id="username"/>
    <br><br>
    <span style="margin-left: 15px"> Password: </span>
    <input type = "text" style="width: 200px; margin-left: 3px" name="password" id="password"/>
    <br><br><br>
    <input type ="submit" style="margin-left: 15px" value = "Login"/>
	
	<a href="${initParam.param3}?action=register"><button style="margin-left: 140px" type ="button">Register</button></a>
	</form>
</body>
</html>