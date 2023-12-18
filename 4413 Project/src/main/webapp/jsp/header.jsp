<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<div class="header">
			<h2>
				<span style="margin-left: 15px; margin-top: 15px; " class="label">SHOE
					<span style="color: white;">STORE</span></span>
				<a href="${initParam.param2}?action=viewCart"><span
						style="float:right; margin:20px; margin-right:30px; font-size:30px"
						class="label">Cart</span></a>

				<c:if test="${sessionScope.loginStatus == 'false'}">
					<a href="${initParam.param3}?action=loginRegister"><span
							style="float:right; margin:20px; margin-right:100px; font-size:30px"
							class="label">Login/Register</span></a>
				</c:if>
				<c:if test="${sessionScope.loginStatus == 'true'}">
					
					<a href="${initParam.param3}?action=logout"><span
							style="float:right; margin:20px; margin-right:100px; font-size:30px"
							class="label">Logout</span></a>
					<a href="${initParam.param3}?action=profile"><span
							style="float:right; margin:20px; margin-right:120px; font-size:30px"
							class="label">Profile</span></a>
							<c:if test="${sessionScope.adminStatus == 'true'}">
						<a href="${initParam.param4}?action=admin"><span
								style="float:right; margin:20px; margin-right:100px; font-size:30px" class="label">Admin
								View</span></a>
						<a href="${initParam.param1}?action=shoes"><span
								style="float:right; margin:20px; margin-right:100px; font-size:30px"
								class="label">Customer View</span></a>
					</c:if>
				</c:if>

			</h2>
		</div>