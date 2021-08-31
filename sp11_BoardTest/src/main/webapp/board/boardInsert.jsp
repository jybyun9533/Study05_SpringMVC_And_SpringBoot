<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.10.1.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<form method="post" action="boardInsert.do"
		enctype="multipart/form-data" name="boardForm">
		<table>
			<h3>파일 업로드 테스트</h3>
			<tr>
				<td>제목 : <input type="text" name="title"></td>
			</tr>
			<tr>
				<td>작성자 : <input type="text" name="id"></td>
			</tr>
			<tr>
				<td>내용<br /></td>
			</tr>
			<td><textarea cols="50" rows="10" name="contents"></textarea></td>
			<tr>
				<td>파일 : <input type="file" name="uploadFile"></td>
			</tr>
			<tr>
				<td><input type="submit" value="작성"> <input
					type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
</body>
</html>