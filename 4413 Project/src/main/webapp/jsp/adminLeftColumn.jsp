<%@page language="java" contentType="text/html" %>


	<%-- <%@page import="java.util.Enumeration" %> --%>
		<%-- <%@page import="java.util.Hashtable" %> --%>
			<%-- <%@page import="java.util.List" %> --%>
				<%-- <%@page import="java.util.ArrayList" %> --%>
					<%-- <%@page import="java.util.Iterator" %> --%>
						<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




							<link rel="stylesheet" href="css/shoestore.css" type="text/css" />
							<script src="js/shoestore.js" type="text/javascript"></script>
							<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
							</head>

							<div class="leftbar">
								<ul id="menu">
									<li><a class="link1" href="admin?action=manageUsers">
											<span class="label" style="margin-left: 15px;">Manage Users</span>
										</a></li>
									<li><a class="link1" href="admin?action=manageShoeTypes"><span class="label"
												style="margin-left: 15px;">Manage Shoe Types</span></a></li>
									<li><a class="link1" href="admin?action=manageShoeStock"><span class="label"
												style="margin-left: 15px;">Manage Shoe Stock</span></a></li>
									<li><a class="link1" href="admin?action=managePurchaseOrders"><span class="label"
												style="margin-left: 15px;">Manage Purchase Orders</span></a></li>

								</ul>
							</div>
							</body>

							</html>