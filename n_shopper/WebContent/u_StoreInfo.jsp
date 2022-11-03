<%@page import="model.Flyer"%>
<%@page import="model.Area"%>
<%@page import="model.Prefecture"%>
<%@page import="model.Region"%>
<%@page import="model.Recipe"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="model.CouponList"%>
<%@page import="model.StoreUpdate"%>
<%@page import="model.Store"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String notUpdates = "※更新はありません※";
System.out.println("★★★現在地★★★：u_StoreInfo.jsp :jsp");
Store store = (Store) request.getAttribute("store");
StoreUpdate store_update = (StoreUpdate) request.getAttribute("store_update");
int store_id = (int) request.getAttribute("store_id");
List<CouponList> couponList = (List<CouponList>) request.getAttribute("couponList");
List<Recipe> recipeList = (List<Recipe>) request.getAttribute("recipeList");
List<Flyer> flyerList = (List<Flyer>) request.getAttribute("flyerList");
List<Region> region_List = (List<Region>) request.getAttribute("regionList");
List<Prefecture> prefecture_List = (List<Prefecture>) request.getAttribute("prefectureList");
List<Area> area_List = (List<Area>) request.getAttribute("areaList");
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta charset="UTF-8">
<title>店舗情報画面</title>
<jsp:include page="/common_css.jsp" />
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container">
		<div class="d-flex justify-content-center align-items-center">
			<div class="">
				<br>
				<h2>店舗情報</h2>
				<br>
				<section>
					<!-- 個人的な目印 -->
					<h5><%=store.getStoreName()%></h5>
					<p>
						店舗住所：<%=store.getSubPostcode()%>
					</p>
					<p>
						営業時間：<%=store.getBusinessHours()%>
					</p>
					<p>
						URL：<%=store.getHP()%>
					</p>
					<p>
						TEL：<%=store.getTel()%>
					</p>
					<p>
						支払方法：<%=store.getPayment()%>
					</p>
					<p>
						施設情報：<%=store.getInformation()%>
					</p>
					<p>
						実施サービス：<%=store.getService()%>
					</p>
				</section>

				<section>
					<h5>クーポン</h5>
					<table border="1">
						<tr>
							<th>品目</th>
							<th>クーポン名</th>
							<th>有効期限</th>
							<th>クーポン画面へ</th>

						</tr>

						<!-- ここを繰り返す。 -->
						<%
						if (couponList != null && couponList.size() != 0) {//中身があるとき
							for (int i = 0; i < couponList.size(); i++) {
						%>
						<tr>
							<td><%=couponList.get(i).getSubItem_id()%></td>
							<td><%=couponList.get(i).getName()%></td>
							<td>
								<%
								// TimeStamp型をString型に変換
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
								String stop_date = simpleDateFormat.format(couponList.get(i).getEnd_date());
								%><br> 使用終了：<%=stop_date%>まで
							</td>
							<td><a
								href="/shopper/UseCouponS?id=<%=couponList.get(i).getCoupon_id()%>">
									<button type="button">詳細へ</button>
							</a></td>
						</tr>
					</table>
					<%
					}
					} else {//中身がないとき
					%>
					<tr>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
					</tr>
					</table>
					<%=notUpdates%>
					<%
					}
					%>

				</section>
				<br>

				<section>
					<h5>チラシ</h5>
					<%
					if (flyerList != null && flyerList.size() != 0) {//中身があるとき
						for (int i = 0; i < flyerList.size(); i++) {
					%>
					<ul>
						<li>チラシ名:<%=flyerList.get(i).getName()%> <%
 if (flyerList.get(i).getMain_image() != null) {
 %> <br>img:<img src="<%=flyerList.get(i).getMain_image()%>">画像<%=flyerList.get(i).getMain_image()%>
							<%
							}
							if (flyerList.get(i).getSub_image() != null) {
							%> <br>img:<img src="<%=flyerList.get(i).getSub_image()%>">画像<%=flyerList.get(i).getSub_image()%>
							<%
							}
							%>
						</li>
					</ul>
					<%
					}
					} else {//中身がないとき
					%>
					<%=notUpdates%>

					<%
					}
					%>


				</section>
				<br>

				<section>
					<h5>レシピ</h5>

					<%
					if (recipeList != null && recipeList.size() != 0) {//中身があるとき
						for (int i = 0; i < recipeList.size(); i++) {
					%>
					<ul>
						<li>レシピ名:<%=recipeList.get(i).getName()%> <%
 if (recipeList.get(i).getImage1() != null) {
 %> <br>img:<img src="<%=recipeList.get(i).getImage1()%>">画像<%=recipeList.get(i).getImage1()%>
							<%
							}
							if (recipeList.get(i).getImage2() != null) {
							%> <br>img:<img src="<%=recipeList.get(i).getImage2()%>">画像<%=recipeList.get(i).getImage2()%>
							<%
							}
							if (recipeList.get(i).getImage3() != null) {
							%> <br>img:<img src="<%=recipeList.get(i).getImage3()%>">画像<%=recipeList.get(i).getImage3()%>
							<%
							}
							%>

						</li>
					</ul>
					<%
					}
					} else {//中身がないとき
					%>
					<%=notUpdates%>
					<%
					}
					%>
				</section>
				<br>

				<form action="/shopper/X_StoreInfoEdit" method="get">
					<button type="submit" class="btn btn-primary col-10 ">編集</button>
					<input type="hidden" name="id" value="<%=store_id%>">
				</form>
				<br />
			</div>
		</div>
	</div>

	<jsp:include page="/common_js.jsp" />
	<script type="text/javascript" src="js/additional.js"></script>
</body>
</html>