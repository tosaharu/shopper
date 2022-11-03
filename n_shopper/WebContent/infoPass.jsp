<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/shopper/InfoPassChange" method="post">
<% 	System.out.println("値の受け取り");
String pass= request.getParameter("pass");
String id = request.getParameter("id");
System.out.println(pass+id);
%>
<input type="hidden" name ="pass" value="<%=pass%>">
<input type="hidden" name ="id" value="<%=id%>">
現在のパスワードを入力してください<br>
パスワード：<input type="password" name ="oldpass"><br>

新しいパスワードを入力してください<br>

パスワード:<input type = "text" name = "pass1"><br>
確認用:<input type = "text" name = "pass2"><br>
<input type="submit" value="送信">
</form>

</body>
</html>