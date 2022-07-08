<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="custom.do" method="post">
<table align="center" width="550">
	<tr> 
		<td colspan="2" align="center"> 사 원 등 록 </td> 
	</tr>
	<tr>
		<td align="center">아 이 디	</td>
		<td><input type="text" name = "id"></td>
	</tr>
	<tr>
		<td align="center">비 밀 번 호	</td>
		<td><input type="password" name = "pwd"></td>
	</tr>
	<tr>
		<td align="center">이  름	</td>
		<td><input type="text" name = "name"></td>
	</tr>
	<tr>
		<td align="center">권 한	</td>
		<td>
			<select name="lev">
				<option value="A"> 운영자 </option>
				<option value="B"> 일 반 회 원 </option>
			
			</select>
		</td>
	</tr>
	
	<tr>
		<td align="center">성 별	</td>
		<td>
			<select name="gender">
				<option value="1"> 남 자 </option>
				<option value="2"> 여 자 </option>
			
			</select>
		</td>
	</tr>
	<tr>
		<td align="center">전화 번호</td>
		<td><input type="text" name = "phone"></td>
	</tr>
	
	<tr align="center">
		<td colspan="2">
			<input type="submit" value="등록">
			<input type="reset" value="취소">
			<input type="button" value="메인페이지로 이동"
			onclick="location.href='main.jsp'">
		</td>
	</tr>

</table>

</form>
</body>
</html>