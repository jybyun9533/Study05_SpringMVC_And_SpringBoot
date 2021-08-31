<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		h1 {
			text-align: center;
		}
		a {
			display: block;
			width: 900px;
			margin: auto;
			text-align: right;
			margin-bottom: 10px;
		}
		table {
			width: 900px;
			margin: auto;
			border: 2px solid black;
		}
		thead, tfoot {
			background: orange;
		}
		tfoot {
			text-align: right;
		}
		tbody td {
			text-align: center;
		}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		var amount = 1;
		var totalPrice = 0;
		$(function() {
			refreshPage();
			
			$('[name=up]').on('click', function() {
				$(this).next().html(eval($(this).next().html())+1);
				amount =  $(this).next().html();
				totalPrice += eval($(this).parent().prev().html());
				$('tfoot tr td span').html(totalPrice);
			});
			
			$('[name=down]').on('click', function() {
				if(amount>1) {
					$(this).prev().html(eval($(this).prev().html())-1);
					amount =  $(this).prev().html();
					totalPrice -= eval($(this).parent().prev().html());
					$('tfoot tr td span').html(totalPrice);
				}
			});

		});
		function refreshPage() {
			var html='';
			var count = 0;
			totalPrice = 0;
			amount = 1;
			for(var key in localStorage) {
				if(key=='length') break;
				var data = localStorage.getItem(key).split(',');
				html += '<tr>'+
					'<td>'+ ++count +'</td>'+
					'<td><img src=' + data[0] + ' width=150 height=150></td>'+
					'<td>' + data[1] + '</td>'+
					'<td>' + data[2] + '</td>'+
					'<td><img src=img/up.jpg width=10 height=10 style=cursor:pointer; name=up><div>' + amount + '</div><img name=down src=img/down.jpg width=10 height=10 style=cursor:pointer;></td>'+
					'<td><input value='+key+' type=checkbox name=deleteItem></td>'+
					'</tr>';
				totalPrice += eval(data[2]);
			}
			$('tbody').append(html);
			$('tfoot tr td span').html(totalPrice);
		}
		
		function deleteStorage() {
			var check = document.getElementsByName("deleteItem");
			for(var i=0; i < check.length; i++) {
				if(check[i].checked==true) {
					//var price = check[i].parentNode.previousSibling.previousSibling.innerHTML;
					//var mount = check[i].parentNode.previousSibling.childNodes[1].innerHTML;
					//totalPrice -= eval(price)*eval(mount);
					
					localStorage.removeItem(check[i].value);
					location.reload();
					//$('tbody').html("");
					
					//refreshPage();
					//$('tfoot tr td span').html(totalPrice);
				}
			}
			
		}
	</script>
</head>
<body>
	<h1>장바구니</h1>
	<a href="itemList.do">쇼핑 계속하기</a>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>상품이미지</th>
				<th>상품명</th>
				<th>상품가격</th>
				<th>수량</th>
				<th><button onclick="deleteStorage()">삭제</button></th>
			</tr>
		</thead>
		<tbody></tbody>
		<tfoot>
			<tr>
				<td colspan="6">총 결제금액 : <span id="resultTotal"></span></td>
			</tr>
		</tfoot>
	</table>
</body>
</html>