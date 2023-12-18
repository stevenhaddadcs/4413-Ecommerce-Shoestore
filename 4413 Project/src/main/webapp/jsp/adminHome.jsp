<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/shoestore.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script src="js/shoestore.js"></script>
<title>Admin Home</title>
</head>

<body>
    <div id="centered">
        <jsp:include page="header.jsp" flush="true" />
        <br />
        <jsp:include page="adminLeftColumn.jsp" flush="true" /> 
        <span class="label" style="margin-left: 15px;">Admin Home</span>
    </div>
</body>
</html>
