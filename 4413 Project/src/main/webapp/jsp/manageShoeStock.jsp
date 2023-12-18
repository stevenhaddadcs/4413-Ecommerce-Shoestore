<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="stylesheet" href="css/adminStyle.css" type="text/css" />
            <title>Manage Shoe Stock</title>
        </head>

        <body>
            <div id="centered">
                <jsp:include page="header.jsp" flush="true" />
                <br />
                <jsp:include page="adminLeftColumn.jsp" flush="true" />
                <span class="label" style="margin-left: 15px;">Manage Shoes</span>
            </div>

            <div id="content">
                <h2>Manage Shoe Stock</h2>

                <table>
                    <thead>
                        <tr>
                            <th>Model</th>
                            <th>Colourway</th>
                            <th>Brand</th>
                            <th>Size</th>
                            <th>Stock</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Loop through shoes and display stock management options -->
                        <c:forEach var="shoe" items="${shoes}">
                            <tr>
                                <td>${shoe.model}</td>
                                <td>${shoe.colourway}</td>
                                <td>${shoe.brand}</td>
                                <td>${shoe.size}</td>
                                <td>${shoe.stock}</td>
                                <td>
                                    <form action="admin" method="post">
                                        <input type="hidden" name="action" value="updateStock" />
                                        <input type="hidden" name="shoe_id" value="${shoe.id}" />
                                        <label for="newStock">New Stock:</label>
                                        <input type="number" name="newStock" value="${shoe.stock}" />
                                        <input type="submit" value="Update Stock" />
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>