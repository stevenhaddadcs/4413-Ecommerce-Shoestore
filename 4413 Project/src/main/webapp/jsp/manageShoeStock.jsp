<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="css/adminStyle.css" type="text/css" />
    <title>Manage Shoe Stock</title>
</head>
<body>
    <div id="leftColumn">
        <ul>
            <li><a href="admin?action=manageUsers">Manage Users</a></li>
            <li><a href="admin?action=manageShoes">Manage Shoes</a></li>
            <li><a href="admin?action=manageShoeStock">Manage Shoe Stock</a></li>
        </ul>
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
