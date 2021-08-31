<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardDetail</title>
</head>
<body>
	<h1>${vo.no}${vo.title}</h1>
	<h2>${vo.id}</h2>
	<p>${vo.contents}</p>

	<img src="upload/${vo.filename}" onerror="this.style.display='none'" width="300" height="300">
	<c:if test="${vo.filename!=null}">
		<p>
			<a href="fileDown.do?filename=${vo.filename}">${vo.filename}</a>
		</p>
	</c:if>

</body>
</html>