<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="stylesheet" href="css/shoestore.css" type="text/css" />
            <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
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

                <!-- Display shoes in a table -->
                <table border="1">
                    <caption>Shoe Types Manager</caption>
                    <tr>
                        <th>Shoe ID</th>
                        <th>Model</th>
                        <th>Colourway</th>
                        <th>Brand</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>

                    <!-- Loop through shoes and display each row in the table -->
                    <c:forEach var="shoe" items="${shoetypes}">
                        <tr>
                            <!-- Display shoe information in each column -->
                            <td>${shoe.shoe_id}</td>
                            <td>${shoe.model}</td>
                            <td>${shoe.colourway}</td>
                            <td>${shoe.brand}</td>
                            <td>${shoe.price}</td>
                            <td>
                                <!-- Form to update the shoe price -->
                                <form action="admin" method="post">
                                    <input type="hidden" name="action" value="updatePrice" />
                                    <input type="hidden" name="shoe_id" value="${shoe.shoe_id}" />
                                    <label for="newPrice">New Price:</label>
                                    <input type="number" step="0.01" name="newPrice" value="${shoe.price}" />
                                    <input type="submit" value="Update Price" />
                                </form>

                                <!-- Form to delete the shoe -->
                                <form action="admin" method="post">
                                    <input type="hidden" name="action" value="deleteShoe" />
                                    <input type="hidden" name="shoe_id" value="${shoe.shoe_id}" />
                                    <p>Are you sure you want to delete the shoe?</p>
                                    <input type="submit" value="Delete Shoe" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </body>

        </html>