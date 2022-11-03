<%@page import="model.Store"%>
<%@page import="model.U_Product_String"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
System.out.println("★★★現在地★★★：x_EditStore.jsp :jsp");
int store_id = (int) request.getAttribute("store_id");
Store store = (Store) request.getAttribute("store");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta charset="UTF-8">
<title>編集画面</title>
	<jsp:include page="/common_css.jsp" />
</head>
<body>

		<jsp:include page="/header.jsp" />
	<div class="container">
		<div class="d-flex justify-content-center align-items-center">
			<div class="">

				<br>
				<h2>店舗情報編集</h2>
				<br>

				<form action="/shopper/X_StoreInfoEdit" method="post">


					店舗名： <input type="text" name="name"
						value="<%=store.getStoreName()%>"> <br> 店舗住所： <input
						type="text" name="address" value="<%=store.getSubPostcode()%>">
					<br> 営業時間： <input type="text" name="business_h"
						value="<%=store.getBusinessHours()%>"> <br> URL： <input
						type="text" name="hp" value="<%=store.getHP()%>"> <br>
					TEL： <input type="text" name="tel" value="<%=store.getTel()%>">
					<br> 支払方法： <input type="text" name="payment"
						value="<%=store.getPayment()%>"> <br> 施設情報： <input
						type="text" name="info" value="<%=store.getInformation()%>">
					<br> 実施サービス： <input type="text" name="service"
						value="<%=store.getService()%>"> <br> <input
						type="hidden" value="<%=store_id%>">
						<br />
						<button type="submit" class="btn btn-primary col-10 ">登録
						</button>
						<br>
				</form>

			</div>
		</div>
	</div>
	<jsp:include page="/common_js.jsp" />
	<script type="text/javascript" src="js/additional.js"></script>

</body>
</html>