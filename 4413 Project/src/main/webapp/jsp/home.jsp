<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="css/shoestore.css" type="text/css" />
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script src="js/shoestore.js"></script>
</head>
<body>
    <div id="centered">
        <jsp:include page="header.jsp" flush="true" />
        <br />
        <jsp:include page="leftColumn.jsp" flush="true" />
        <span class="label" style="margin-left: 15px;">Shoes</span>
        <table>
            <c:forEach var="shoe" items="${shoeTypes}" varStatus="loopStatus">
                <c:if test="${loopStatus.index % 5 == 0}">
                    <tr>
                </c:if>
                <td>
                    <a href="${initParam.param1}?action=stock&model=${shoe.model}&colour=${shoe.colourway}">
                        <img src="${initParam.imageURL}/${shoe.imageString}" id="shoeImage"/>
                    </a>
                    <p id="caption">${shoe.brand} ${shoe.model} ${shoe.colourway}</p>
                </td>
                <c:if test="${loopStatus.index % 5 == 4 or loopStatus.last}">
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</body>
</html>
