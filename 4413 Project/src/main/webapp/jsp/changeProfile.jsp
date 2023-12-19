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
         <span class="label" style="margin-left: 15px;"> Changing Profile
         </span>
    </div>
    <br>
    <c:choose>
    	<c:when test="${requestScope.addressInvalid == 'true'}">
 		<span style="margin-left: 15px; font-size:30px"> NEW ADDRESS DOES NOT MATCH </span><br>
    	</c:when>
    	<c:when test="${requestScope.ccInvalid == 'true'}">
 		<span style="margin-left: 15px; font-size:30px"> NEW CREDIT CARD DOES NOT MATCH </span><br>
    	</c:when>
    	<c:when test="${requestScope.usernameInvalid == 'true'}">
 		<span style="margin-left: 15px; font-size:30px"> NEW USERNAME DOES NOT MATCH </span><br>
    	</c:when>
    	<c:when test="${requestScope.passwordInvalid == 'true'}">
 		<span style="margin-left: 15px; font-size:30px"> NEW PASSWORD DOES NOT MATCH </span><br>
    	</c:when>
    	<c:when test="${requestScope.ccInvalid2 == 'true'}">
 		<span style="margin-left: 15px; font-size:30px"> NEW CREDIT CARD IS INVALID</span><br>
    	</c:when>
    </c:choose>

    <br>
    <c:choose>
    	<c:when test="${requestScope.changeA == 'true'}">
			<span style="margin-left: 15px"> Current Address: ${sessionScope.user.address} </span><br><br>
			<span style="margin-left: 15px"> New Address: </span>
			<input type = "text" style="width: 200px;" name="newAddress" id="newAddress"/><br><br>
			<span style="margin-left: 15px"> Confirm New Address: </span>
			<input type = "text" style="width: 200px; margin-left: 15px" name="newAddressCheck" id="newAddressCheck"/><br><br>
			<input type ="submit" style="margin-left: 15px" value = "Change Address" name="changeNewAddress"/>
    	</c:when>
    	<c:when test="${requestScope.changeC == 'true'}">
    		<span style="margin-left: 15px"> Current Credit Card: ${sessionScope.user.cc_number} </span><br><br>
			<span style="margin-left: 15px"> New Credit Card: </span>
			<input type = "text" style="width: 200px;" name="newCC" id="newCC"/><br><br>
			<span style="margin-left: 15px"> Confirm New Credit Card: </span>
			<input type = "text" style="width: 200px; margin-left: 15px" name="newCCCheck" id="newCCCheck"/><br><br>
			<input type ="submit" style="margin-left: 15px" value = "Change Credit Card" name="changeNewCC"/>
    	</c:when>
    	<c:when test="${requestScope.changeU == 'true'}">
			<span style="margin-left: 15px"> Current Username: ${sessionScope.user.username} </span><br><br>
			<span style="margin-left: 15px"> New Username: </span>
			<input type = "text" style="width: 200px;" name="newUsername" id="newUsername"/><br><br>
			<span style="margin-left: 15px"> Confirm New Username: </span>
			<input type = "text" style="width: 200px; margin-left: 15px" name="newUsernameCheck" id="newUsernameCheck"/><br><br>
			<input type ="submit" style="margin-left: 15px" value = "Change Username" name="changeNewUsername"/>
    	</c:when>
    	<c:when test="${requestScope.changeP == 'true'}">
			<span style="margin-left: 15px"> Current Password: ${sessionScope.user.password} </span><br><br>
			<span style="margin-left: 15px"> New Password: </span>
			<input type = "text" style="width: 200px;" name="newPassword" id="newPassword"/><br><br>
			<span style="margin-left: 15px"> Confirm New Password: </span>
			<input type = "text" style="width: 200px; margin-left: 15px" name="newPasswordCheck" id="newPasswordCheck"/><br><br>
			<input type ="submit" style="margin-left: 15px" value = "Change Password" name="changeNewPassword"/>
    	</c:when>
    </c:choose>
    <br><br>
    
	</form>
</body>
</html>