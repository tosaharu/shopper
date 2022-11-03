//テスト
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.CouponList"%>
<%@page import="java.util.List"%>




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


            <%
    List<CouponList> clist =(List<CouponList>)session.getAttribute("couponList");

    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>クーポンリスト一覧</title>
</head>
<body>

<p>クーポン一覧</p>
<!-- クーポン情報を描画 -->
<form action="CouponListS" method="post">

<table border="1">
<tr>
<th>品目</th>
<th>クーポン名</th>
<th>有効期限</th>
<th>店舗</th>
<th>詳細</th>
<th>使用状況</th>
</tr>

<%for(int i=0;i<clist.size();i++){
	CouponList c =clist.get(i);
	%>
	<tr>
	<td><%=c.getSubItem_id()%></td>
	<td><a href="/shopper/UseCouponS?id=<%=c.getCoupon_id() %>"><%=c.getName()%></a></td>
	<td><%SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
	String stop_date = simpleDateFormat.format(c.getEnd_date());%>
	<%=stop_date%>まで</td>
	<td><%=c.getStorename()%></td>
	<td><%=c.getComment() %></td>
	<td><%=c.getUsed_flag2()%></td>

	</tr>


<%} %>
</table>
</form>
</body>
</html>