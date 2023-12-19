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
 	<form method = "post" action = "CartController">
    <div>
       
         <span class="label" style="margin-left: 15px;"> Checkout
         </span>
       
    </div>
    <input type = "hidden" name = "action" value = "checkedout"/>
    <c:if test="${requestScope.noCC == 'true'}">
    <br>
    <span style="margin-left: 15px; font-size:30px"> PLEASE ENTER A CREDIT CARD </span>
    </c:if>
    <c:if test="${requestScope.noAddress == 'true'}">
    <br>
    <span style="margin-left: 15px; font-size:30px"> PLEASE ENTER AN ADDRESS </span>
    </c:if>
    
	<table>
	     <thead>
            <tr>
           		<th id="th-image"></th>
                <th id="th-name">Brand and Model</th>
                <th id="th-colour">Colour</th>
                <th id="th-size">Size</th>
                <th id="th-price">Price</th>
            </tr>
        </thead>
         <c:forEach items="${requestScope.shoeList}" var="e">
            <tr>
            <td> <img src="${initParam.imageURL}/${e.imageString}"/></td>
             <td>${e.brand} ${e.model}</td>
             <td> ${e.colourway} </td>
             <td> ${e.size} </td>
             <td> ${e.price} </td>
            </tr>
            </c:forEach>
            <tr>
            <td> <c:if test="${sessionScope.user.cc_number == null}" >
            	<span style="margin-left: 15px"> Credit Card: </span>
            	<input type = "text" id="ccard" name="ccard" value="${requestScope.ccard}"/>
            </c:if>
             <c:if test="${sessionScope.user.cc_number != null}" >
            	<input type = "hidden" id="ccard" name="ccard" value="${sessionScope.user.cc_number}"/>
            </c:if>
            </td>
			<td> <c:if test="${sessionScope.user.address == null}" >
				<span style="margin-left: 15px"> Address: </span>
           		<input type = "text" id="address" name="address" value="${requestScope.address}"/>
            </c:if>
             <c:if test="${sessionScope.user.address != null}" >
           		<input type = "hidden" id="address" name="address" value="${sessionScope.user.address}"/>
            </c:if>
			</td>
			<td></td>
			<td></td>
			<td><input type ="submit" value = "Checkout"/></td>
            </tr>
	</table>
	</form>
</body>
</html>