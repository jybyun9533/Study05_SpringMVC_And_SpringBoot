<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	/* var lists = [];
	$("input[name='check']:checked").each(function(i) { //jQuery로 for문 돌면서 check 된값 배열에 담는다
		lists.push($(this).val());
	}); */
	var lists = "";
	
	function startRequest() {
		alert(lists);
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = callback;
		xhr.open("get", "delPhone.do");
		xhr.send(lists);
	}
	
	function callback() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				// var data =JSON.parse(xhr.responseText);
				

			}
		}
	}
</script>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<table>
		<tr>
			<th>모델번호</th>
			<th>모델이름</th>
			<th>가격</th>
			<th>제조사명</th>
			<c:if test="${!empty loginUser}">
				<th>삭제</th>
			</c:if>
		</tr>
		<c:forEach items="${phones}" var="phone">
			<tr>
				<td><a href="detail.do?num=${phone.num}">${phone.num}</td>
				<td>${phone.model}</td>
				<td>${phone.price}</td>
				<td>${phone.company.vendor}</td>
				<c:if test="${!empty loginUser}">
					<td><input type="checkbox" value="${phone.num}" name="check"></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${!empty loginUser}">
		<a href="regPhone.do">추가 등록</a>&nbsp;
		<input type="button" id="selectedDel" value="선택항목삭제" onclick="startRequest()">
	</c:if>

</body>
</html>