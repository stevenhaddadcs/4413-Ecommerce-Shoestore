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
       
         <span class="label" style="margin-left: 15px;"> Cart
         </span>
       
    </div>
    
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
            <td> <c:if test="${requestScope.userccard == null}" >
            	<input type = "text" id="ccard" name="ccard"/>
            </c:if>
            </td>
			<td> <c:if test="${requestScope.useraddress == null}" >
           		<input type = "text" id="address" name="address"/>
            </c:if>
			</td>
			<td></td>
			<td></td>
			<td><input type ="submit" value = "Checkout"/></td>
            </tr>
	</table>
	<input type = "hidden" name = "action" value = "checkedout"/>
	</form>
</body>
</html>