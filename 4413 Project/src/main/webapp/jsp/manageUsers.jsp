<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="css/adminStyle.css" type="text/css" />
</head>
<body>
    <div id="centered">
        <h2>Manage Users</h2>
        <form action="admin?action=deleteUsers" method="post">
            <table>
                <tr>
                    <th>User ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>
                            <input type="submit" name="deleteUser" value="Delete" onclick="return confirm('Are you sure you want to delete this user?');" />
                            <input type="hidden" name="userId" value="${user.id}" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
</body>
</html>
