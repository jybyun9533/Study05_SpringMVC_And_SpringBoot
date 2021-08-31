<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	width: 800px;
	margin: 0 auto;
	background-color: #d3d3d3;
}

/* table {
	border: 1px solid black;
} */
.g {
	border: 1px solid black;
}
</style>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	getPhones();
	}); //전체 리스트 출력

function getPhones(){
	$('#phone-list').empty();
	$('#status').empty();
	// 비동기 시작
	$.ajax({
		url : "phone", //로컬이기 때문에 ip생략
		type : "get",
		contentType : "application/json;charset=utf-8",
		error : function(xhr, status, message) {
			$('#status').append("조회 실패 : " + status);
		}, // error
		success : function(data) {
			//alert(data);
			$('#phone-list').empty();
			$('#phone-list').append("<table class='g'>");
			$('#phone-list').append("<tr><th class='g'>모델번호</th><th class='g'>모델이름</th><th class='g'>가격</th><th class='g'>제조사명</th></tr>");
			for (var i = 0; i < data.length; i++) {
				$('#phone-list').append(
						"<tr class='g'><td class='g' onclick=getAPhone('"+data[i].num +"')>" + data[i].num + "</td><td class='g' onclick=getAPhone('"+data[i].num +"')>"
								+ data[i].model + "</td><td class='g'>"
								+ data[i].price + "</td><td class='g'>"
								+ data[i].company.vendor + "</td></tr>"
				);
			}
			$('#book-list').append("</table>");
			$('#status').append("조회 성공");
		} // callback
	})//ajax
}// getPhones

function test(){
	alert('test');
}

function getAPhone(num){
	// 비동기 시작
	$.ajax({
		url : "phone/"+num, //로컬이기 때문에 ip생략
		type : "get",
		contentType : "application/json;charset=utf-8",
		error : function(xhr, status, message) {
			$('#status').append("해당 폰의 상세조회 실패 : " + status);
		}, // error
		success : function(data) {
			$('.phoneInfo').val('');
			$("#vcode option:eq(0)").prop("selected", true);
			$('input[name=num]').val(data.num);
			$('input[name=model]').val(data.model);
			$('input[name=price]').val(data.price);
			$("#vcode").val(data.vcode).prop("selected", true);
			
		} // callback
	})//ajax
}// getAPhone


function phoneInsert() {
	$.ajax({
		url : "phone",
		type : "post",
		data : JSON.stringify({
			'num' : $('input[name=num]').val(),
			'model' : $('input[name=model]').val(),
			'price' : $('input[name=price]').val(),
			'vcode' : $("#vcode").val()
		}), // json
		contentType : "application/json;charset=urf-8",
		error : function(xhr, status, message) {
			alert("error : " + xhr.status)
		}, // error
		success : function(data) {
			alert('추가 완료!')
			getPhones();
		}

	})// ajax
} // bookInsert

function phoneUpdate() {
	$.ajax({
		url : "phone",
		type : "put",
		data : JSON.stringify({
			'num' : $('input[name=num]').val(),
			'model' : $('input[name=model]').val(),
			'price' : $('input[name=price]').val(),
			'vcode' : $("#vcode").val()
		}), // json
		contentType : "application/json;charset=urf-8",
		error : function(xhr, status, message) {
			alert("error : " + xhr.status)
		}, // error
		success : function(data) {
			alert('수정 완료!')
			getPhones();
		}

	})// ajax
} // bookInsert


function phoneDelete() {
	$.ajax({
		url : "phone/" + $('input[name=num]').val(),
		type : "delete",
		contentType : "application/json;charset=urf-8",
		error : function(xhr, status, message) {
			alert("error : " + xhr.status)
		}, // error
		success : function(data) {
			alert('삭제 완료!')
			getPhones();
			$('.phoneInfo').val('');
			$("#vcode option:eq(0)").prop("selected", true);
		}

	})// ajax
}
</script>
</head>
<body>
	<h1>휴대전화 목록</h1>
	<div id="phone-list"></div>
	<div id="status"></div>
	<hr>
	<h1>휴대전화 정보</h1>
	<table>
		<tr>
			<td>모델번호</td>
			<td>
				<input type='text' name='num' class="phoneInfo">
			</td>
		</tr>
		<tr>
			<td>모델명</td>
			<td>
				<input type='text' name='model' class="phoneInfo">
			</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>
				<input type='text' name='price' class="phoneInfo"> 원
			</td>
		</tr>
		<tr>
			<td>제조사</td>
			<td>
				<select id='vcode'>
					<option value="0" selected>---</option>
					<option value="10">삼성</option>
					<option value="20">엘지</option>
					<option value="30">애플</option>
				</select>
			</td>
		</tr>
	</table>
	<input type="button" value="추가하기" onclick=phoneInsert()>
	<input type="button" value="수정하기" onclick=phoneUpdate()>
	<input type="button" value="삭제하기" onclick=phoneDelete()>
</body>
</html>