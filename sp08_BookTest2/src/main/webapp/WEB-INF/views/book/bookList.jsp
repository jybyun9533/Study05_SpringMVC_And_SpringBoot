<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 정보</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
h1, h4 {
	text-align: center;
}

#bookTable {
	margin: auto auto;
}

body, p {
	text-align: center;
}

#bookTable th {
	text-align: right;
}

#bookTable td {
	border: 1px solid black;
	padding: 10px 0px;
}

#bookTable tr:nth-child(2) {
	text-align: center;
	background-color: lightgray;
}

#bookTable td {
	width: 100px;
}

#bookTable td:nth-child(2) {
	width: 250px;
}

#bookTable td:nth-child(1) {
	width: 150px;
}

#bookTable td:nth-child(4) {
	width: 150px;
}

#bookTable td:nth-child(3) {
	width: 200px;
}
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('.isbn').hover(
				function() {
					$.ajax({
						type : 'post',
						url : 'hover.do',
						dataType : 'json',
						data : {
							"isbn" : $(this).eq(0).text()
						},

						error : function(error) {
							console.log("error");
						},
						
						success : function(data) {
							//alert(data);
							console.log(data);
							// 특정 영역에 객체의 내용을 출력.
							$('#result').html(
								
									'<tr><td>번호</td><td>'+data.descBook.isbn+'</td></tr>'+
		    						'<tr><td>제목</td><td>'+data.descBook.title+'</td></tr>'+
		    						'<tr><td>분류</td><td>'+data.descBook.catalogue+'</td></tr>'+
		    						'<tr><td>국가</td><td>'+data.descBook.nation+'</td></tr>'+
		    						'<tr><td>날짜</td><td>'+data.descBook.publishDate+'</td></tr>'+
		    						'<tr><td>출판사</td><td>'+data.descBook.publisher+'</td></tr>'+
		    						'<tr><td>저자</td><td>'+data.descBook.author+'</td></tr>'+
		    						'<tr><td>가격</td><td>'+data.descBook.price+data.descBook.currency+'</td></tr>'+
		    						'<tr><td>설명</td><td>'+data.descBook.description+'</td></tr>');
						} // callback

					});//ajax
				}); //click
	}); //ready
</script>
</head>
<body>
	<h1>도서 목록 화면</h1>

	<c:if test="${!empty user}">
		<h4>${user.name}님
			로그인 되었습니다. <a href="memberLogout.do">로그아웃</a>
		</h4>
	</c:if>
	<c:choose>
		<c:when test="${empty list }">
			<h4>등록된 도서 목록이 없습니다.</h4>
		</c:when>
		<c:otherwise>
			<table id="bookTable">
				<tr>
					<th colspan="4">
						<form action="bookSearch.do" method="get">
							<select name="searchField" id="searchField">
								<option value="LIST">전체</option>
								<option value="TITLE">도서명</option>
								<option value="PUBLISHER">출판사</option>
								<option value="PRICE">가격</option>
							</select> <input type="text" id="searchText" name="searchText"> <input
								type="submit" value="검색">
						</form>
					</th>
				</tr>
				<tr>
					<td>도서번호</td>
					<td>도서명</td>
					<td>도서분류</td>
					<td>저자</td>
				</tr>
				<c:forEach items="${list }" var="book">
					<tr>
						<td class="isbn">${book.isbn}</td>
						<td><a href ="bookView.do?isbn=${book.isbn}">${book.title}</a></td>
						<td>${book.catalogue}</td>
						<td>${book.author}</td>
					</tr>

				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<div id="result"></div>
	<p>
		<a href='bookForm.jsp'>도서 등록</a> <a href='index.jsp'>메인페이지</a>
	</p>
</body>
</html>
 --%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 정보</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
h1, h4 {
	text-align: center;
}

#bookTable {
	margin: auto auto;
}

body, p {
	text-align: center;
}

#bookTable th {
	text-align: right;
}

#bookTable td {
	border: 1px solid black;
	padding: 10px 0px;
}

#bookTable tr:nth-child(2) {
	text-align: center;
	background-color: lightgray;
}

#bookTable td {
	width: 100px;
}

#bookTable td:nth-child(2) {
	width: 250px;
}

#bookTable td:nth-child(1) {
	width: 150px;
}

#bookTable td:nth-child(4) {
	width: 150px;
}

#bookTable td:nth-child(3) {
	width: 200px;
}
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('.isbn').hover(
				function() {
					var isbn=$(this).attr('id');
					$.ajax({
						type : 'get',
						url : 'hover.do?isbn='+isbn,
						dataType : 'json',

						error : function(error) {
							console.log("error");
						},
						
						success : function(data) {
							//alert(data);
							console.log(data);
							// 특정 영역에 객체의 내용을 출력.
							$('#result').html(
								"<h5><font color=red>" +
								'<tr><td>번호 : </td><td>'+data['descBook'].isbn+'</td><br>'+
	    						'<td>제목 : </td><td>'+data['descBook'].title+'</td><br>'+
	    						'<td>분류 : </td><td>'+data['descBook'].catalogue+'</td><br>'+
	    						'<td>국가 : </td><td>'+data['descBook'].nation+'</td><br>'+
	    						'<td>날짜 : </td><td>'+data['descBook'].publishDate+'</td><br>'+
	    						'<td>출판사 : </td><td>'+data['descBook'].publisher+'</td><br>'+
	    						'<td>저자 : </td><td>'+data['descBook'].author+'</td><br>'+
	    						'<td>가격 : </td><td>'+data['descBook'].price+data['descBook'].currency+'</td><br>'+
	    						'<td>설명 : </td><td>'+data['descBook'].description+'</td></tr><br></font></h5>');	    
									/* '<tr><td>번호</td><td>'+data.descBook.isbn+'</td></tr>'+
		    						'<tr><td>제목</td><td>'+data.descBook.title+'</td></tr>'+
		    						'<tr><td>분류</td><td>'+data.descBook.catalogue+'</td></tr>'+
		    						'<tr><td>국가</td><td>'+data.descBook.nation+'</td></tr>'+
		    						'<tr><td>날짜</td><td>'+data.descBook.publishDate+'</td></tr>'+
		    						'<tr><td>출판사</td><td>'+data.descBook.publisher+'</td></tr>'+
		    						'<tr><td>저자</td><td>'+data.descBook.author+'</td></tr>'+
		    						'<tr><td>가격</td><td>'+data.descBook.price+data.descBook.currency+'</td></tr>'+
		    						'<tr><td>설명</td><td>'+data.descBook.description+'</td></tr>'); */
						} // callback

					});//ajax
				}); //click
	}); //ready
</script>
</head>
<body>
	<h1>도서 목록 화면</h1>

	<c:if test="${!empty user}">
		<h4>${user.name}님
			로그인 되었습니다. <a href="memberLogout.do">로그아웃</a>
		</h4>
	</c:if>
	<c:choose>
		<c:when test="${empty list }">
			<h4>등록된 도서 목록이 없습니다.</h4>
		</c:when>
		<c:otherwise>
			<table id="bookTable">
				<tr>
					<th colspan="4">
						<form action="bookSearch.do" method="get">
							<select name="searchField" id="searchField">
								<option value="LIST">전체</option>
								<option value="TITLE">도서명</option>
								<option value="PUBLISHER">출판사</option>
								<option value="PRICE">가격</option>
							</select> <input type="text" id="searchText" name="searchText"> <input
								type="submit" value="검색">
						</form>
					</th>
				</tr>
				<tr>
					<td>도서번호</td>
					<td>도서명</td>
					<td>도서분류</td>
					<td>저자</td>
				</tr>
				<c:forEach items="${list }" var="book">
					<tr>
						<td>${book.isbn}</td>
						<td class="isbn" id="${book.isbn}"><a
							href="bookView.do?isbn=${book.isbn}">${book.title}</a></td>
						<td>${book.catalogue}</td>
						<td>${book.author}</td>
					</tr>

				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<div id="result"></div>
	<p>
		<a href='bookForm.jsp'>도서 등록</a> <a href='index.jsp'>메인페이지</a>
	</p>
</body>
</html>
