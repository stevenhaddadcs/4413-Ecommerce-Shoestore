<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
 
 	<jsp:include page="brand.jsp" flush="true" />
 
    <div>
        <c:if test="${param.model != null}" > 
         <span class="label" style="margin-left: 15px;"> List of ${param.model} Sizes and Stock
         </span>
        </c:if>
    </div>

     
    <table id="grid">
        <thead>
            <tr>
                <th id="th-name">Model</th>
                <th id="th-colour">Colour</th>
                <th id="th-price">Price</th>
                <th id="th-size">Size</th>
                <th id="th-stock">Stock</th>
                
            </tr>
        </thead>


        <tbody>
        
            
         <c:forEach items="${requestScope.shoeStockList}" var="e">
            <tr>
             <td> ${e.model}</td>
             <td> ${e.colourway} </td>
             <td> ${e.price} </td>
             <td> ${e.size} </td>
             <td> ${e.stock} </td>
            </tr>
            </c:forEach>
     

        </tbody>

    </table>
</body>
</html>