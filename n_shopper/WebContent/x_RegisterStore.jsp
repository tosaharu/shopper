<%@page import="model.Store"%>
<%@page import="model.Area"%>
<%@page import="model.Prefecture"%>
<%@page import="model.Region"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
System.out.println("★★★現在地★★★：x_Register.store.jsp :jsp");
Store store = (Store) request.getAttribute("store");

List<Region> region_List = (List<Region>) request.getAttribute("regionList");
List<Prefecture> prefecture_List = (List<Prefecture>) request.getAttribute("prefectureList");
List<Area> area_List = (List<Area>) request.getAttribute("areaList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<title>店舗情報・新規登録</title>
<jsp:include page="/common_css.jsp" />
</head>
<body>
	<jsp:include page="/header.jsp" />

	<div class="container">
		<h1>店舗情報・新規登録</h1>

		<form action="/shopper/X_StoreInfoRegistration" method="post">

			<div class="row mb-2">
				<label for="detail" class="form-label"> 店舗名 </label>
				<div class="col">
					<input type="text" class="form-control" name="store" placeholder="店舗名を入力してください" required="required"><br>
				</div>
			</div>


			<div class="row mb-2">
				<label for="areas" class="form-label">店舗のエリア</label>

				<div class="col">
					<select id="region" class="form-select"
						aria-label="Default select example"
						onchange="CheckSelectedRegion(this)">
						<option selected="selected">地方を選択してください</option>
						<%
						for (Region region : region_List) {
						%>
						<option value="<%=region.getRegion_id()%>"><%=region.getRegion()%></option>
						<%
						}
						%>
					</select>
				</div>
				<div class="col">
					<select id="prefecture" class="form-select"
						aria-label="Default select example" disabled="disabled"
						onchange="CheckSelectedPrefecture(this)">
						<option selected="selected">都道府県を選択してください</option>
						<%
						for (Prefecture prefecture : prefecture_List) {
						%>
						<option data-region_id="<%=prefecture.getRegion_id()%>"
							value="<%=prefecture.getPrefecture_id()%>"><%=prefecture.getPrefecture()%></option>
						<%
						}
						%>
					</select>
				</div>
				<div class="col">
					<select id="area" class="form-select"
						aria-label="Default select example" name="area"
						disabled="disabled">
						<option selected="selected">エリアを選択してください</option>
						<%
						for (Area area : area_List) {
						%>
						<option data-prefecture_id="<%=area.getPrefecture_id()%>"
							value="<%=area.getArea_id()%>"><%=area.getArea()%>
						</option>
						<%
						}
						%>
					</select>
				</div>
			</div>

			<div class="row mb-2">
				<label class="form-label"> 詳細住所 </label>

				<div class="col">
					<input type="text" class="form-control" name="address" placeholder="詳細住所を都道府県から入力してください" required="required"><br>
				</div>
			</div>


			<div class="row mb-2">
				<label class="form-label"> 営業時間 </label>

				<div class="row mb-2">

				<div class="col">
					<input type="time" class="form-control" name="start_hours" required="required">
				</div>
				～
				<div class="col">
					<input type="time" class="form-control" name="end_hour" required="required">
				</div>

			</div>
			</div>

			<div class="row mb-2">
				<label for="detail" class="form-label"> HP(URL) </label>
				<div class="col">
					<input type="url" class="form-control" name="hp" placeholder="URLを入力してください" required="required"><br>
				</div>
			</div>

			<div class="row mb-2">
				<label for="detail" class="form-label"> 電話番号 </label>
				<div class="col">
					<input type="number" class="form-control" name="tel" placeholder="電話番号をハイフン(-)なしで入力してください" required="required"><br>
				</div>
			</div>

			<div class="row mb-2">
				<label for="detail" class="form-label"> 支払方法 </label>
				<div class="col">
					<input type="text" class="form-control" name="payment" placeholder="現金/クレジット/各種電子決済 など入力してください" required="required"><br>
				</div>
			</div>

			<div class="row mb-2">
				<label for="detail" class="form-label"> 施設情報 </label>
				<div class="col">
					<input type="text" class="form-control" name="info" placeholder="喫煙室/サービスカウンター/地下駐車場/お手洗いの有無 など入力してください" required="required"><br>
				</div>
			</div>

			<div class="row mb-2">
				<label for="detail" class="form-label"> 実施サービス </label>
				<div class="col">
					<input type="text" class="form-control" name="service" placeholder="試食会/レシピ説明会/各種イベント など入力してください" required="required"><br>
				</div>
			</div>

			<div class="row justify-content-center">
				<input type="submit" class="btn btn-primary col-3 mx-2" value="登録する">店舗画面へ移動する。
			</div>

		</form>

	</div>

	<jsp:include page="/common_js.jsp" />
	<script type="text/javascript" src="js/additional.js"></script>

</body>
</html>