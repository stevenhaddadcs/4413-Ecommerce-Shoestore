<%@page language="java" contentType="text/html"%>


<%-- <%@page import="java.util.Enumeration"%> --%>
<%-- <%@page import="java.util.Hashtable"%> --%>
<%-- <%@page import="java.util.List"%> --%>
<%-- <%@page import="java.util.ArrayList"%> --%>
<%-- <%@page import="java.util.Iterator"%> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


 

<link rel="stylesheet" href="css/shoestore.css" type="text/css" />
<script src="js/shoestore.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
</head>

<div class="leftbar">
    <ul id="menu">
        <li><div>
                <a class="link1" href="${initParam.param1}"> <span class="label"
                    style="margin-left: 15px;">Home</span>
                </a>
            </div></li>
        <li><div>
                <a class="link1" href="${initParam.param1}?action=allShoes"><span
                    style="margin-left: 15px;" class="label">All Shoes</span></a>
            </div></li>
        <li><div>
                <span class="label" style="margin-left: 15px;">Brands</span>
            </div>
            <ul>
                
              <c:forEach items="${brandList}" var="item">
     
                             
                <li><a class="label"
                    href="${initParam.param1}?action=brand&brand=${item}">
                    <span  class="label" style="margin-left: 30px;">
                        ${item} </span></a>
                </li>
     
              </c:forEach>
                
                
                
            </ul></li>
    </ul>
    
    <form class="search">
        Search: <input type="hidden" name="action" value="search" /> <input id="text"
            type="text" name="keyWord" size="12" /> <span
            class="tooltip_message">?</span>
        <p />
        <input id="submit" type="submit" value="Search" />
    </form>


</div>






