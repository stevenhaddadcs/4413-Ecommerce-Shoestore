<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

    <div>
        <c:if test="${param.brand != null}" > 
         <span class="label" style="margin-left: 15px;"> List of ${param.brand}  Shoes
         </span>
         </c:if>
     <c:if test="${param.brand != null || param.keyWord != null || param.action != null}" >
    <table id="grid">
        <thead>
            <tr>
                <th id="th-image"></th>
                <th id="th-name">Model</th>
                <th id="th-colour">Colour</th>
                <th id="th-price">Price</th>
                
            </tr>
        </thead>


        <tbody>
        
            
         <c:forEach items="${requestScope.shoeList}" var="e">
            <tr>
            <td> <a href="${initParam.param1}?action=stock&model=${e.model}&colour=${e.colourway}"><img src="${initParam.imageURL}/${e.imageString}"/></a></td>
             <td> ${e.model}</td>
             <td> ${e.colourway} </td>
             <td> ${e.price} </td>
            </tr>
            </c:forEach>
     

        </tbody>

    </table>
    </c:if>
    </div>

</body>
</html>