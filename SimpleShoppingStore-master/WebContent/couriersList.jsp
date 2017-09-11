<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
ul {
	
}

li {
	height: 22px;
	background-color: #000;
	list-style: none;
	float: left;
	padding: 10px;
	margin-top: 2px;
}

li a {
	text-decoration: none;
	color: #fff;
}

ul li {
	color: black;
}

li a:hover {
	background-color: blue;
	height: 20px;
}
</style>
</head>
<body>
	<ul>
		<c:forEach var="list" items="${herosList}">
			<c:set var="heroId" value="${list.heroID}" />
			<c:url var="url" value="/setDetails">
				<c:param name="heroId" value="${heroId}" />
			</c:url>
			<li><a href="${url}"><strong>${list.heroName}</strong></a></li>
		</c:forEach>
	</ul>
</body>
</html>