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
 	<form method = "post" action = "ProfileController">
    <div>
         <span class="label" style="margin-left: 15px;"> Profile
         </span>
    </div>
    <br>
    <c:choose>
    	<c:when test="${requestScope.addressEmpty == 'true'}">
 		<span style="margin-left: 15px; font-size:30px"> ADDRESS IS EMPTY </span><br><br>
    	</c:when>
    	<c:when test="${requestScope.ccEmpty == 'true'}">
 		<span style="margin-left: 15px; font-size:30px"> CREDIT CARD IS EMPTY </span><br><br>
    	</c:when>
    </c:choose>
    <span style="margin-left: 15px"> Current Username: ${sessionScope.user.username} </span>
    <br><br>
    <c:choose>
    	<c:when test="${sessionScope.user.address == null}">
    		<input type = "text" style="width: 200px;margin-left: 15px" name="addressP" id="addressP"/>
    		<input type ="submit" style="margin-left: 15px" value = "Add Address" name="addAddress"/>
    	</c:when>
    	<c:when test="${sessionScope.user.address != null}">
    		<span style="margin-left: 15px"> Current Address: ${sessionScope.user.address} </span>
    		<br><br>
    		<input type ="submit" style="margin-left: 15px" value = "Change Address" name="changeAddress"/>
    	</c:when>
    </c:choose>
    <br><br>
    <c:choose>
    	<c:when test="${sessionScope.user.cc_number == null}">
    		<input type = "text" style="width: 200px;margin-left: 15px" name="creditCard" id="creditCard"/>
    		<input type ="submit" style="margin-left: 15px" value = "Add Credit Card" name="addCC"/>
    	</c:when>
    	<c:when test="${sessionScope.user.cc_number != null}">
    		<input type ="submit" style="margin-left: 15px" value = "Change Credit Card" name="changeCC"/>
    	</c:when>
    </c:choose>
	<br><br>
	<input type ="submit" style="margin-left: 15px" value = "Change Username" name="changeUser"/>
	<br><br>
    <input type ="submit" style="margin-left: 15px" value = "Change Password" name="changePass"/>
	<br><br><br><br>
    <input type ="submit" style="margin-left: 15px" value = "View Purchase History" name="viewHistory"/>
    
	</form>
</body>
</html>