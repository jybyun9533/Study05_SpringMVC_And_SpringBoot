<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var xhr;
	function startRequest() {
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = callback;
		xhr.open("get", "asynch");
		xhr.send(null);
	}

	function callback() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var data =JSON.parse(xhr.responseText);
				alert(data.person.name + "님이 사는 곳은 "
						+ data.person.address);

			}
		}
	}
</script>
</head>
<body>
	<h3>${info }</h3>
	<p></p>
	<input type="button" value="비동기통신 " onclick="startRequest()">
</body>
</html>