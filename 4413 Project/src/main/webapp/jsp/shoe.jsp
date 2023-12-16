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
 	<form method = "get" action = "CartController">
    <div>
        <c:if test="${param.model != null}" > 
         <span class="label" style="margin-left: 15px;"> List of ${param.model} Sizes and Stock
         </span>
        </c:if>
    </div>
    
	<table>
	     <thead>
            <tr>
                <th id="th-name">Model</th>
                <th id="th-colour">Colour</th>
                <th id="th-price">Price</th>
                <th id="th-size">Size</th>
                <th id="th-stock">Stock</th>
            </tr>
        </thead>
		<tr>
			<td><p id = "caption">${requestScope.shoeStockList[0].getModel()}</p></td>
			<td><p id = "caption">${requestScope.shoeStockList[0].getColourway()}</p></td>
			<td><p id = "caption">$${requestScope.shoeStockList[0].getPrice()}</p></td>
			<td>
				<select name = "sizeSelect" id="sizeSelect" onchange="changeStock(this.selectedIndex, this.value);">
					<c:forEach items="${requestScope.shoeStockList}" var="e">
						<option value ="${e.size}">${e.size}</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<input type = "text" id="stock" value = "${requestScope.shoeStockList[0].getStock()}"/>
			</td>
		</tr>
		<tr>
			<td><img src = "${initParam.imageURL}/${requestScope.shoeStockList[0].getImageString()}"/></td>
		</tr>
	</table>
	<input type = "hidden" name = "action" value = "add"/>
	<input type = "hidden" name = "model" value = "${requestScope.shoeStockList[0].getModel()}"/>
	<input type = "hidden" name = "colour" value = "${requestScope.shoeStockList[0].getColourway()}"/>
	<input type = "hidden" id = "sizeSelected" name ="size" value = ""/>
	<script type="text/javascript">
			//get the stock value based on the selected option from the sizeSelect drop-down list
			function changeStock(index,value){
					document.getElementById('sizeSelected').value=value;
					switch (index){
					case 0:
						document.getElementById('stock').value=${requestScope.shoeStockList[0].getStock()};
						break;
					case 1:
						document.getElementById('stock').value=${requestScope.shoeStockList[1].getStock()};
						break;
					case 2:
						document.getElementById('stock').value=${requestScope.shoeStockList[2].getStock()};
						break;
					case 3:
						document.getElementById('stock').value=${requestScope.shoeStockList[3].getStock()};
						break;
					case 4:
						document.getElementById('stock').value=${requestScope.shoeStockList[4].getStock()};
						break;
					case 5:
						document.getElementById('stock').value=${requestScope.shoeStockList[5].getStock()};
						break;
					case 6:
						document.getElementById('stock').value=${requestScope.shoeStockList[6].getStock()};
						break;
					case 7:
						document.getElementById('stock').value=${requestScope.shoeStockList[7].getStock()};
						break;
					case 8:
						document.getElementById('stock').value=${requestScope.shoeStockList[8].getStock()};
						break;
					case 9:
						document.getElementById('stock').value=${requestScope.shoeStockList[9].getStock()};
						break;
					case 10:
						document.getElementById('stock').value=${requestScope.shoeStockList[10].getStock()};
						break;
					case 11:
						document.getElementById('stock').value=${requestScope.shoeStockList[11].getStock()};
						break;
					case 12:
						document.getElementById('stock').value=${requestScope.shoeStockList[12].getStock()};
						break;
						} 
					}
				
		</script>
	<input type ="submit" value = "Add to Cart"/>
	</form>
</body>
</html>