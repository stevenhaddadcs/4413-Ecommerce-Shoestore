<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="stylesheet" href="css/adminStyle.css" type="text/css" />
            <title>Manage Shoes</title>
        </head>

        <body>
            <div id="centered">
                <jsp:include page="header.jsp" flush="true" />
                <br />
                <jsp:include page="adminLeftColumn.jsp" flush="true" />
                <span class="label" style="margin-left: 15px;">Manage Shoes</span>
            </div>

            <div id="content">
                <h2>Update and Delete Shoe</h2>

                <!-- Loop through shoes and display update/delete options -->
                <c:forEach var="shoe" items="${shoes}">
                    <div>
                        <!-- Display shoe information -->
                        <p>Model: ${shoe.model}</p>
                        <p>Colourway: ${shoe.colourway}</p>
                        <p>Brand: ${shoe.brand}</p>
                        <p>Price: ${shoe.price}</p>

                        <!-- Form to update the shoe price -->
                        <form action="admin" method="post">
                            <input type="hidden" name="action" value="updatePrice" />
                            <input type="hidden" name="shoe_id" value="${shoe.id}" />
                            <label for="newPrice">New Price:</label>
                            <input type="number" step="0.01" name="newPrice" value="${shoe.price}" />
                            <input type="submit" value="Update Price" />
                        </form>

                        <!-- Form to delete the shoe -->
                        <form action="admin" method="post">
                            <input type="hidden" name="action" value="deleteShoe" />
                            <input type="hidden" name="shoe_id" value="${shoe.id}" />
                            <p>Are you sure you want to delete the shoe?</p>
                            <input type="submit" value="Delete Shoe" />
                        </form>
                    </div>
                </c:forEach>
            </div>
        </body>

        </html>