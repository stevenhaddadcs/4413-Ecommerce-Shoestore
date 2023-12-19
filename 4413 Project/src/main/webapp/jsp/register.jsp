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
      	 <input type = "hidden" name = "action" value = "registerSubmit"/>
         <span class="label" style="margin-left: 15px;">Register
         </span>
    </div>
    <c:if test="${requestScope.nameTaken == 'true'}">
    <br>
    <span style="margin-left: 15px; font-size:30px"> USERNAME ALREADY TAKEN </span>
    </c:if>
    <c:if test="${requestScope.nameEmpty == 'true'}">
    <br>
    <span style="margin-left: 15px; font-size:30px"> USERNAME CANNOT BE EMPTY OR CONTAIN SPECIAL CHARACTERS </span>
    </c:if>
    <c:if test="${requestScope.passwordEmpty == 'true'}">
    <br>
    <span style="margin-left: 15px; font-size:30px"> PASSWORD CANNOT BE EMPTY </span>
    </c:if>
    <br><br>
	<span style="margin-left: 15px"> Username: </span>
	<input type = "text" style="width: 200px;" name="usernameReg" id="usernameReg"/>
    <br><br>
    <span style="margin-left: 15px"> Password: </span>
    <input type = "password" style="width: 200px; margin-left: 3px" name="passwordReg" id="passwordReg"/>
    <br><br><br>
    <input type ="submit" style="margin-left: 15px" value = "Register"/>
	</form>
</body>
</html>