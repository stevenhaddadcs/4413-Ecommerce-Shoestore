<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

 

<!DOCTYPE html >

 
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
		<jsp:include page="leftColumn.jsp" flush="true" />
		<span class="label" style="margin-left: 15px;">Shoes</span>
		<table>
			<tr>
				<td><a href="${initParam.param1}?action=stock&model=Ozweego&colour=Grey"><img src="${initParam.imageURL}/Adidas - Ozweego - Grey.png" id="shoeImage"/></a><p id="caption">Adidas Ozweego Grey</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Ultraboost 1.0&colour=Black"><img src="${initParam.imageURL}/Adidas - Ultraboost 1.0 - Black.png" id="shoeImage"/></a><p id="caption">Adidas Ultraboost 1.0 Black</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Ultraboost 1.0&colour=White"><img src="${initParam.imageURL}/Adidas - Ultraboost 1.0 - White.png" id="shoeImage"/></a><p id="caption">Adidas Ultraboost 1.0 White</p></td>
				<td><a href="${initParam.param1}?action=stock&model=X_Plrphase&colour=Black"><img src="${initParam.imageURL}/Adidas - X_Plrphase - Black.png" id="shoeImage"/></a><p id="caption">Adidas X_Plrphase Black</p></td>
				<td><a href="${initParam.param1}?action=stock&model=X_Plrphase&colour=White"><img src="${initParam.imageURL}/Adidas - X_Plrphase - White.png" id="shoeImage"/></a><p id="caption">Adidas X_Plrphase White</p></td>
			</tr>
			<tr>
				<td><a href="${initParam.param1}?action=stock&model=ZG23 Golf Shoe&colour=White"><img src="${initParam.imageURL}/Adidas - ZG23 Golf Shoe - White.png" id="shoeImage"/></a><p id="caption">Adidas ZG23 Golf Shoe White</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Air Force 1&colour=Black"><img src="${initParam.imageURL}/Nike - Air Force 1 - Black.png" id="shoeImage"/></a><p id="caption">Nike Air Force 1 Black</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Air Force 1&colour=White"><img src="${initParam.imageURL}/Nike - Air Force 1 - White.png" id="shoeImage"/></a><p id="caption">Nike Air Force 1 White</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Air Max 97&colour=Black, Yellow"><img src="${initParam.imageURL}/Nike - Air Max 97 - Black, Yellow.png" id="shoeImage"/></a><p id="caption">Nike Air Max 97 Black/Yellow</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Dunks Low&colour=Black, White"><img src="${initParam.imageURL}/Nike - Dunks Low - Black, White.png" id="shoeImage"/></a><p id="caption">Nike Dunks Low Black/White</p></td>
			</tr>
			<tr>
				<td><a href="${initParam.param1}?action=stock&model=Dunks Low&colour=Green, White"><img src="${initParam.imageURL}/Nike - Dunks Low - Green, White.png" id="shoeImage"/></a><p id="caption">Nike Dunks Low Green/White</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Roshe One&colour=Black"><img src="${initParam.imageURL}/Nike - Roshe One - Black.png" id="shoeImage"/></a><p id="caption">Nike Roshe One Black</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Roshe One&colour=Grey, White"><img src="${initParam.imageURL}/Nike - Roshe One - Grey, White.png" id="shoeImage"/></a><p id="caption">Nike Roshe One Grey/White</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Roshe One&colour=Navy, White, Black"><img src="${initParam.imageURL}/Nike - Roshe One - Navy, White, Black.png" id="shoeImage"/></a><p id="caption">Nike Roshe One Navy/White/Black</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Zoom Fly 5&colour=Black"><img src="${initParam.imageURL}/Nike - Zoom Fly 5 - Black.png" id="shoeImage"/></a><p id="caption">Nike Zoom Fly 5 Black</p></td>
			</tr>
			<tr>
				<td><a href="${initParam.param1}?action=stock&model=Zoom Fly 5&colour=Blue"><img src="${initParam.imageURL}/Nike - Zoom Fly 5 - Blue.png" id="shoeImage"/></a><p id="caption">Nike Zoom Fly 5 Blue</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Zoom Fly 5&colour=Pink"><img src="${initParam.imageURL}/Nike - Zoom Fly 5 - Pink.png" id="shoeImage"/></a><p id="caption">Nike Zoom Fly 5 Pink</p></td>
				<td><a href="${initParam.param1}?action=stock&model=CA Pro Classic Sneakers&colour=Black"><img src="${initParam.imageURL}/Puma - CA Pro Classic Sneakers - Black.png" id="shoeImage"/></a><p id="caption">Puma CA Pro Classic Sneakers Black</p></td>
				<td><a href="${initParam.param1}?action=stock&model=CA Pro Classic Sneakers&colour=Green"><img src="${initParam.imageURL}/Puma - CA Pro Classic Sneakers - Green.png" id="shoeImage"/></a><p id="caption">Puma CA Pro Classic Sneakers Green</p></td>
				<td><a href="${initParam.param1}?action=stock&model=CA Pro Classic Sneakers&colour=White"><img src="${initParam.imageURL}/Puma - CA Pro Classic Sneakers - White.png" id="shoeImage"/></a><p id="caption">Puma CA Pro Classic Sneakers White</p></td>
			</tr>
			<tr>
				<td><a href="${initParam.param1}?action=stock&model=Deviate Nitro 2&colour=Black"><img src="${initParam.imageURL}/Puma - Deviate Nitro 2 - Black.png" id="shoeImage"/></a><p id="caption">Puma Deviate Nitro 2 Black</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Deviate Nitro 2&colour=White"><img src="${initParam.imageURL}/Puma - Deviate Nitro 2 - White.png" id="shoeImage"/></a><p id="caption">Puma Deviate Nitro 2 White</p></td>
				<td><a href="${initParam.param1}?action=stock&model=ForeverRun Nitro Sunset&colour=Black"><img src="${initParam.imageURL}/Puma - ForeverRun Nitro Sunset - Black.png" id="shoeImage"/></a><p id="caption">Puma ForeverRun Nitro Sunset Black</p></td>
				<td><a href="${initParam.param1}?action=stock&model=ForeverRun Nitro Sunset&colour=White"><img src="${initParam.imageURL}/Puma - ForeverRun Nitro Sunset - White.png" id="shoeImage"/></a><p id="caption">Puma ForeverRun Nitro Sunset White</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Suede Classic XXI&colour=Black"><img src="${initParam.imageURL}/Puma - Suede Classic XXI - Black.png" id="shoeImage"/></a><p id="caption">Puma Suede Classic XXI Black</p></td>
			</tr>
			<tr>
				<td><a href="${initParam.param1}?action=stock&model=Suede Classic XXI&colour=Red"><img src="${initParam.imageURL}/Puma - Suede Classic XXI - Red.png" id="shoeImage"/></a><p id="caption">Puma Suede Classic XXI Red</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Suede Classic XXI&colour=White"><img src="${initParam.imageURL}/Puma - Suede Classic XXI - White.png" id="shoeImage"/></a><p id="caption">Puma Suede Classic XXI White</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Project Rock BSR 3&colour=Black, Green"><img src="${initParam.imageURL}/Under Armour - Project Rock BSR 3 - Black, Green.png" id="shoeImage"/></a><p id="caption">Under Armour Project Rock BSR 3 Black/Green</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Project Rock BSR 3&colour=Black, White, Blue"><img src="${initParam.imageURL}/Under Armour - Project Rock BSR 3 - Black, White, Blue.png" id="shoeImage"/></a><p id="caption">Under Armour Project Rock BSR 3 Black/White/Blue</p></td>
				<td><a href="${initParam.param1}?action=stock&model=Project Rock BSR 3&colour=Black, White"><img src="${initParam.imageURL}/Under Armour - Project Rock BSR 3 - Black, White.png" id="shoeImage"/></a><p id="caption">Under Armour Project Rock BSR 3 Black/White</p></td>
			</tr>
			<tr>
				<td><a href="${initParam.param1}?action=stock&model=UA Charged Assert 10&colour=Black, White"><img src="${initParam.imageURL}/Under Armour - UA Charged Assert 10 - Black, White.png" id="shoeImage"/></a><p id="caption">Under Armour UA Charged Assert 10 Black/White</p></td>
				<td><a href="${initParam.param1}?action=stock&model=UA Charged Assert 10&colour=Blue, White"><img src="${initParam.imageURL}/Under Armour - UA Charged Assert 10 - Blue, White.png" id="shoeImage"/></a><p id="caption">Under Armour UA Charged Assert 10 Blue/White</p></td>
				<td><a href="${initParam.param1}?action=stock&model=UA Charged Assert 10&colour=Red, Black"><img src="${initParam.imageURL}/Under Armour - UA Charged Assert 10 - Red, Black.png" id="shoeImage"/></a><p id="caption">Under Armour UA Charged Assert 10 Red/Black</p></td>
				<td><a href="${initParam.param1}?action=stock&model=UA Charged Pursuit 3&colour=Black"><img src="${initParam.imageURL}/Under Armour - UA Charged Pursuit 3 - Black.png" id="shoeImage"/></a><p id="caption">Under Armour UA Charged Pursuit 3 Black</p></td>
				<td><a href="${initParam.param1}?action=stock&model=UA Charged Pursuit 3&colour=Grey"><img src="${initParam.imageURL}/Under Armour - UA Charged Pursuit 3 - Grey.png" id="shoeImage"/></a><p id="caption">Under Armour UA Charged Pursuit 3 Grey</p></td>
			</tr>
			<tr>
				<td><a href="${initParam.param1}?action=stock&model=UA Hovr Phantom 3 SE Warm&colour=Black, White, Grey"><img src="${initParam.imageURL}/Under Armour - UA Hovr Phantom 3 SE Warm - Black, White, Grey.png" id="shoeImage"/></a><p id="caption">Under Armour UA Hovr Phantom SE Warm Black/White/Grey</p></td>
				<td><a href="${initParam.param1}?action=stock&model=UA Hovr Phantom 3 SE Warm&colour=Black"><img src="${initParam.imageURL}/Under Armour - UA Hovr Phantom 3 SE Warm - Black.png" id="shoeImage"/></a><p id="caption">Under Armour UA Hover Phantom SE Warm Black</p></td>
			</tr>
		</table>
	</div>
</body>
</html>