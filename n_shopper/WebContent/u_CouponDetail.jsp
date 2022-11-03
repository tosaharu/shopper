//テスト
<%@page import="model.CouponList"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
CouponList c = (CouponList) session.getAttribute("coupon");
%>
<%
CouponList deteal = (CouponList) session.getAttribute("coupondeteal");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>クーポン詳細</title>
</head>
<body>
	<%
	String couponActive = (String) request.getAttribute("couponActive");
	%>

	<form action="UseCouponS" method="post">

		<p>
			クーポン名 : <%=deteal.getName()%></p>
		<br>

		<p>
			<%SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
	String stop_date = simpleDateFormat.format(deteal.getEnd_date());%>
	有効期限 : <%=stop_date%>まで</p>
		<br>
		<p>
			店舗名 : <%=deteal.getStorename()%></p>
		<br>
		<p>
			クーポン詳細 : <%=deteal.getComment()%></p>
		<br> <input type="hidden" name="id"
			value="<%=deteal.getCoupon_id()%>">
			<input type="submit"
			<%if (couponActive != null) {%> value="<%=couponActive%>" <%}%>
			 />
			 </form>
</body>
</html>