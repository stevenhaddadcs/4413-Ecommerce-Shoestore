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

            <title>Manage Purchase Orders</title>
        </head>

        <body>
            <div id="centered">
                <jsp:include page="header.jsp" flush="true" />
                <br />
                <jsp:include page="adminLeftColumn.jsp" flush="true" />
                <h2><span class="label" style="margin-left: 15px;">Manage Purchase Orders</span></h2>
            </div>

            <div id="content">
                <form action="admin" method="post">
                    <input type="hidden" name="action" value="manageUsernameOrders" />
                    Search by username: <input type="text" name="username" />
                    <input type="submit" value="Search" />
                </form>

                <table id="grid">
                    <thead>
                        <tr>
                            <th>Purchase ID</th>
                            <th>Username</th>
                            <th>Item IDs</th>
                            <th>Credit Card Number</th>
                            <th>Address</th>
                            <th>Purchase Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Loop through purchase orders and display information -->
                        <c:forEach var="purchase" items="${purchaseOrders}">
                            <tr>
                                <td>${purchase.purchaseId}</td>
                                <td>${purchase.username}</td>
                                <td>${purchase.items_ids}</td>
                                <td>${purchase.ccNumber}</td>
                                <td>${purchase.address}</td>
                                <td>${purchase.purchase_date}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>