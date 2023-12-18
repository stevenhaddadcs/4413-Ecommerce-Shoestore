<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<meta name="viewport" content="width=device-width, initial-scale=1" />
			<link rel="stylesheet" href="css/shoestore.css" type="text/css" />
			<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
		</head>

		<body>

			<div id="centered">
				<jsp:include page="header.jsp" flush="true" />
				<br />
				<jsp:include page="adminLeftColumn.jsp" flush="true" />
				<span class="label" style="margin-left: 15px;">Manage Shoes</span>
			</div>
			<div id="content">
				<h2>Manage Users</h2>
				<form action="admin" method="post">
					<!-- Adjust the 'AdminServlet' to the actual servlet or controller handling user deletion -->
					<table>
						<tr>
							<th>Username</th>
							<th>Admin Status</th>
							<th>Address</th>
							<th>Credit Card Number</th>
							<th>Delete</th>
						</tr>
						<c:forEach var="user" items="${users}">
							<tr>
								<td>${user.username}</td>
								<td>${user.isAdmin}</td>
								<td>${user.address}</td>
								<td>${user.cc_number}</td>
								<td><input type="submit" name="deleteUser" value="Delete"
										onclick="return confirm('Are you sure you want to delete this user?');" />
									<input type="hidden" name="username" value="${user.username}" />
								</td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</div>
		</body>

		</html>