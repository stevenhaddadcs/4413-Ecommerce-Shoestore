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

		</head>

		<body>

			<div id="centered">
				<jsp:include page="header.jsp" flush="true" />
				<br />
				<jsp:include page="adminLeftColumn.jsp" flush="true" />
				<h2><span class="label" style="margin-left: 15px;">Manage Users</span></h2>
			</div>
			<div id="content">

				<!-- Adjust the 'AdminServlet' to the actual servlet or controller handling user deletion -->
				<table id="grid">
					<thead>
						<tr>
							<th>Username</th>
							<th>Admin Status</th>
							<th>Address</th>
							<th>Credit Card Number</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${users}">
							<tr>
								<td>${user.username}</td>
								<td>
									<c:if test="${user.isAdmin eq 1}">
										Admin
									</c:if>
									<c:if test="${user.isAdmin ne 1}">
										Customer
									</c:if>
								</td>
								<td>${user.address}</td>
								<td>${user.cc_number}</td>

								<td>
									<form action="admin" method="post">
										<input type="submit" value="Delete User" />
										<input type="hidden" name="action" value="deleteUser" />
										<input type="hidden" name="username" value="${user.username}" />
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</body>

		</html>