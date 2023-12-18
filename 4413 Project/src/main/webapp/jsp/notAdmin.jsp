<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Not an Admin</title>
        <link rel="stylesheet" href="css/shoestore.css" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script src="js/shoestore.js"></script>
    </head>

    <body>
        <div id="centered">
            <jsp:include page="header.jsp" flush="true" />
            <jsp:include page="leftColumn.jsp" flush="true" />
        </div>
        <div id="content">
            <h2><span class="label" style="margin-left: 15px;">Not an Admin</span></h2>
            <p>You do not have admin privileges.</p>
            <a href="${initParam.param1}?action=shoes">Go back to home</a>
        </div>
    </body>

    </html>