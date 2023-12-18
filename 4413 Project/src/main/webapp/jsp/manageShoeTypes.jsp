<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="stylesheet" href="css/shoestore.css" type="text/css" />
            <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
            <script src="js/shoestore.js"></script>

            <title>Manage Shoes</title>
        </head>

        <body>
            <div id="centered">
                <jsp:include page="header.jsp" flush="true" />
                <br />
                <jsp:include page="adminLeftColumn.jsp" flush="true" />
                <h2><span class="label" style="margin-left: 15px;">Update and Delete Shoe</span></h2>
            </div>

            <div id="content">

                <!-- Display shoes in a table -->
                <table id="grid">
                    <thead>
                        <tr>
                            <th>Shoe ID</th>
                            <th>Model</th>
                            <th>Colourway</th>
                            <th>Brand</th>
                            <th>Price</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Loop through shoes and display each row in the table -->
                        <c:forEach var="shoe" items="${shoetypes}">
                            <tr>
                                <!-- Display shoe information in each column -->
                                <td>${shoe.shoe_id}</td>
                                <td>${shoe.model}</td>
                                <td>${shoe.colourway}</td>
                                <td>${shoe.brand}</td>
                                <td>
                                    <!-- Form to update the shoe price -->
                                    <form action="admin" method="post">
                                        <input type="hidden" name="action" value="updateShoePrice" />
                                        <input type="hidden" name="shoe_id" value="${shoe.shoe_id}" />
                                        <input type="number" step="1.00" name="price" value="${shoe.price}" />
                                        <input type="submit" value="Update Price" />
                                    </form>
                                </td>
                                <td>

                                    <!-- Form to delete the shoe -->
                                    <form action="admin" method="post">
                                        <input type="submit" value="Delete Shoe" />
                                        <input type="hidden" name="action" value="deleteShoe" />
                                        <input type="hidden" name="shoe_id" value="${shoe.shoe_id}" />
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>