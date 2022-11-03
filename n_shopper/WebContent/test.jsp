<%@page import="java.util.List"%>
<%@page import="model.Store"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Store> store_list = (List<Store>) request.getAttribute("store_list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	テスト用-店舗情報の指定のテスト

<%
for (Store storeList : store_list) {
%>
	<ul>
		<li>
		<a href="/shopper/U_StoreInfo?id=<%=storeList.getStore_id() %>">
		<%=storeList.getStoreName()%>
		</a>
		</li>
	</ul>
	<%} %>



</body>
</html>