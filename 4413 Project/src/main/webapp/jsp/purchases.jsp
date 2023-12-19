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
         <span class="label" style="margin-left: 15px;"> Purchase History
         </span>
    <table id="grid">
        <thead>
            <tr>
                <th id="th-items">Items</th>
                <th id="th-address">Address</th>
                 <th id="th-cc">Credit Card</th>
                <th id="th-date">Date</th>
                
            </tr>
        </thead>


        <tbody>
        
            
         <c:forEach items="${requestScope.purchaseHistory}" var="e">
            <tr>
             <td> ${e.items_ids}</td>
             <td> ${e.address} </td>
              <td> ${e.ccNumber} </td>
             <td> ${e.purchase_date} </td>
            </tr>
            </c:forEach>
     

        </tbody>

    </table>
    </div>
    <br><br><br>
	<input type ="submit" style="margin-left: 15px" value = "Back"/>
	</form>
</body>
</html>