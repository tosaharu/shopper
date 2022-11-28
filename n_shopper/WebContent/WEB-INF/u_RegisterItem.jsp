<%@page import="model.U_Product"%>
<%@page import="model.U_User"%>
<%@page import="model.Store"%>
<%@page import="model.Area"%>
<%@page import="model.Prefecture"%>
<%@page import="model.Region"%>
<%@page import="model.Item"%>
<%@page import="model.SubItem"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.U_Product_String"%>
<%@page import="model.MainItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//セッションスコープから入力後の変換を失敗した場合のインスタンスを取得する。
//もしセッションスコープの有無がが空じゃないときにセッションスコープを取り出す。
U_User user = (U_User) session.getAttribute("loginUser");
U_User guest = (U_User) request.getAttribute("guest");
U_Product_String u_p_s = (U_Product_String) request.getAttribute("u_p_s");
U_Product check_id = (U_Product) request.getAttribute("check_id");

List<MainItem> mainItemList = (ArrayList<MainItem>) request.getAttribute("mainItem_list");
List<SubItem> subItemList = (ArrayList<SubItem>) request.getAttribute("subItem_list");
List<Item> itemList = (ArrayList<Item>) request.getAttribute("item_list");
List<Store> storeList = (ArrayList<Store>) request.getAttribute("store_list");

List<Region> regionList = (List<Region>) request.getAttribute("regionList");
List<Prefecture> prefectureList = (List<Prefecture>) request.getAttribute("prefectureList");
List<Area> areaList = (List<Area>) request.getAttribute("areaList");

// 直近の投稿をセッションに保存したデータ　入力支援として「直前の投稿を呼び出す」ボタンを押下した際の自動入力に使用する
U_Product latestPost = (U_Product) request.getAttribute("latestPost");

// 以前の名残、使用しない
String errorMsg = (String) request.getAttribute("errorMsg");
String errorMsg_price = (String) request.getAttribute("errorMsg_price");
String errorMsg_amount = (String) request.getAttribute("errorMsg_amount");
%>

<!DOCTYPE html>

<html lang="ja">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<title>商品登録</title>
<jsp:include page="/common_css.jsp" />
</head>
<body>
	<jsp:include page="/header.jsp" />

	<div class="container" style="max-width: 720px;">
		<div
			class="d-flex flex-column justify-content-center align-items-center mx-auto">
			<br>
			<h2>商品投稿</h2>
			<br>
			<%
			if (errorMsg != null) {
			%>
			<p><%=errorMsg%></p>
			<%
			}
			%>
			<form action="U_RegisterItem" method="post" id="main_form">
				<div class="row mb-2">
					<label for="date" class="form-label">購入日</label>
					<div class="col" id="date_outline" style="max-width: 160px;">
						<input class="form-control" type="date" name="date" id="date" required="required">
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">日付を入力してください</div>
					</div>
					<div class="col">
						<button type="button" onclick="today_select()"
							class="btn btn-outline-primary col mx-2">今日の日付</button>
					</div>
				</div>
				<div class="row mb-2">
					<label for="areas" class="form-label">購入エリア</label>

					<div class="col" id="region_outline">
						<select id="region" class="form-select"
							aria-label="Default select example"
							onchange="CheckSelectedRegion(this)" required="required">
							<option value=""  selected="selected">地方を選択してください</option>
							<% for (Region region : regionList) { %>
							<option value="<%=region.getRegion_id()%>"><%=region.getRegion()%></option>
							<% } %>
						</select>
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">地方を入力してください</div>
					</div>
					<div class="col" id="prefecture_outline">
						<select id="prefecture" class="form-select"
							aria-label="Default select example"
							onchange="CheckSelectedPrefecture(this)" required="required">
							<option value=""  selected="selected" >都道府県を選択してください</option>
							<% for (Prefecture prefecture : prefectureList) { %>
							<option data-region_id="<%=prefecture.getRegion_id()%>"
								value="<%=prefecture.getPrefecture_id()%>"><%=prefecture.getPrefecture()%></option>
							<% } %>
						</select>
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">都道府県を入力してください</div>
					</div>
					<div class="col" id="area_outline">
						<select id="area" class="form-select"
							aria-label="Default select example" name="area"
							onchange="CheckSelectedArea(this)" required="required">

							<option value=""  selected="selected">エリアを選択してください</option>
							<% for (Area area : areaList) { %>
							<option data-prefecture_id="<%=area.getPrefecture_id()%>"
								value="<%=area.getArea_id()%>"><%=area.getArea()%>
							</option>
							<% } %>
						</select>
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">エリアを入力してください</div>
					</div>
				</div>
				<div class="row mb-2">
					<div class="col-4" id="store_outline">
						<select id="store" name="store_id" class="form-select"
							aria-label="Default select example"
							required="required">

							<option value="">店舗を選択してください</option>
							<% for (Store store : storeList) {
								//if文のための型変換
								int storeID = store.getStore_id();
								System.out.println(storeID);
								System.out.println("check_idの取得" + check_id.getStore_id());
							%>
							<option data-area_id="<%=store.getArea_id()%>"
								value="<%=storeID%>"
								<%if (storeID == check_id.getStore_id()) {%>
								selected="selected"
								<%}%>>
								<%=store.getStoreName()%>
							</option>
							<% } %>
						</select>
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">店舗を入力してください</div>
					</div>
					<div class="col-8">
						<button type="button" id="subm2" class="btn btn-outline-primary col mx-2"
							data-bs-toggle="modal" data-bs-target="#modal">新規登録</button>
					</div>
				</div>

				<div class="row mb-2">
					<label for="category" class="form-label">品目</label>
					<div class="col" id="mainitem_outline">
						<select id="mainitem" class="form-select"
							aria-label="Default select example" name="mainitem"
							onchange="CheckSelectedMainItem(this)" required="required">
							<option value="">大品目を選択してください</option>
							<% for (MainItem mainItem : mainItemList) { %>
							<option value="<%=mainItem.getMainItem_id()%>"
								<%if (mainItem.getMainItem_id() == check_id.getMainItem_id()) {%>
								selected <%}%>>
								<%=mainItem.getMainItem()%>
							</option>
							<% } %>
						</select>
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">大品目を入力してください</div>
					</div>
					<div class="col" id="subitem_outline">
						<select id="subitem" class="form-select"
							aria-label="Default select example" name="subitem"
							onchange="CheckSelectedSubItem(this)" disabled="disabled"
							required="required">
							<option value="">中品目を選択してください</option>
							<% for (SubItem subItem : subItemList) { %>
							<option data-main_item_id="<%=subItem.getMainItem_id()%>"
								value="<%=subItem.getSubItem_id()%>"
								<%if (subItem.getSubItem_id() == check_id.getSubItem_id()) {%>
								selected <%}%>>
								<%=subItem.getSubItem()%>
							</option>
							<% } %>
						</select>
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">中品目を入力してください</div>
					</div>
					<div class="col" id="item_outline">
						<select id="item" class="form-select"
							aria-label="Default select example" name="item"
							disabled="disabled" required="required">
							<option value="">小品目を選択してください</option>
							<% for (Item item : itemList) { %>
							<option data-sub_item_id="<%=item.getSubItem_id()%>"
								value="<%=item.getItem_id()%>"
								<%if (item.getItem_id() == check_id.getItem_id()) {%> selected
								<%}%>>
								<%=item.getItem()%>
							</option>
							<% } %>
						</select>
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">小品目を入力してください</div>
					</div>
				</div>
				<div class="row mb-2">
					<label for="detail" class="form-label">商品名</label>

					<%
					System.out.println("test 入力");
					System.out.println(u_p_s.getItemDetail_s());
					%>
					<div class="col" id="detail_outline" >
						<input type="text" id="detail" class="form-control" name="detail"
							<%if (u_p_s.getItemDetail_s() != null) {%>
							value="<%=u_p_s.getItemDetail_s()%>" <%} else {%> value="" <%}%>
							placeholder="例：カルビーポテトチップスうすしお" required="required">
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">商品名を入力してください</div>
					</div>
				</div>
				<div class="row mb-2">
					<label for="price" class="form-label">価格、数量、割引有無</label>

					<div class="col" id="price_outline" >
						<input type="number" id="price" class="form-control" name="price"
							value="<%=u_p_s.getPrice_s()%>" required="required"
							placeholder="例：85円の場合「85」" min="1">
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">価格を入力してください</div>
						<% if (errorMsg_price != null) { %>
						<br>
						<%=errorMsg_price%>
						<% } %>
					</div>

					<div class="col" id="amount_outline" >
						<input type="number" id="amount" class="form-control" name="amount"
							value="<%=u_p_s.getAmount_s()%>" required="required"
							placeholder="例：60gの場合「60」" min="1">
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">数量を入力してください</div>
						<% if (errorMsg_amount != null) { %>
						<br>
						<%=errorMsg_amount%>
						<% } %>
					</div>
					<div class="col" id="discount_outline" >
						<select name="discount" id="discount" class="form-select"
							required="required">

							<option value=""
								<%if (check_id.getDiscount() != 0 && check_id.getDiscount() != 1) {%>
								selected="selected" <%}%>>割引の有無を選択してください</option>
							<option value="0" <%if (check_id.getDiscount() == 0) {%>
								selected="selected" <%}%>>割引なし</option>
							<option value="1" <%if (check_id.getDiscount() == 1) {%>
								selected="selected" <%}%>>割引あり</option>
						</select>
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">割引の有無を入力してください</div>
					</div>
				</div>
				<div class="row mb-2" id="comment_outline">
					<label for="comment" class="form-label">メモ</label>

					<div class="col">
						<input type="text" id="comment" class="form-control" name="comment"
							<%if (u_p_s.getComment_s() != null) {%>
							value="<%=u_p_s.getComment_s()%>" <%} else {%> value="" <%}%>
							placeholder="例：お店のクーポンを使用して15%引きで購入しました" required="required">
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">メモを入力してください</div>
					</div>
				</div>
				<br>
				<div class="row justify-content-center">
					<button type="button" class="btn btn-secondary col-3 mx-2"
						onclick="history.back(-1);return false;">戻る</button>
					<button type="submit" name="" id="subm1"
						class="btn btn-primary col-3 mx-2" disabled>登録</button>
				</div>
		</form>
		</div>
			<!-- RegisterStoreInfoModal -->
		<div class="modal fade" id="modal" tabindex="-1"
			aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="sub_form">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">店舗情報新規登録</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div class="row mb-2">
								<label for="detail" class="form-label"> 店舗名 </label>
								<div class="col" id="store_name_outline">
									<input type="text" class="form-control" id="store_name"  name="store_name" placeholder="店舗名を入力してください" required="required">
									<div id="validationServerUsernameFeedback"
										class="invalid-feedback">店舗名を入力してください</div>
								</div>
							</div>


							<div class="row mb-2">
								<label for="areas" class="form-label">店舗のエリア</label>

								<div class="col" id="store_region_outline">
									<select id="store_region" name="store_region" class="form-select"
										aria-label="Default select example"
										onchange="CheckSelectedRegion(this)" required="required">
										<option value="" selected="selected">地方を選択してください</option>
										<%
										for (Region region : regionList) {
										%>
										<option value="<%=region.getRegion_id()%>"><%=region.getRegion()%></option>
										<%
										}
										%>
									</select>
									<div id="validationServerUsernameFeedback"
										class="invalid-feedback">地方を入力してください</div>
								</div>
								<div class="col" id="store_prefecture_outline">
									<select id="store_prefecture" name="store_prefecture" class="form-select"
										aria-label="Default select example"
										onchange="CheckSelectedPrefecture(this)" required="required">
										<option value="" selected="selected">都道府県を選択してください</option>
										<%
										for (Prefecture prefecture : prefectureList) {
										%>
										<option data-region_id="<%=prefecture.getRegion_id()%>"
											value="<%=prefecture.getPrefecture_id()%>"><%=prefecture.getPrefecture()%></option>
										<%
										}
										%>
									</select>
									<div id="validationServerUsernameFeedback"
										class="invalid-feedback">都道府県を入力してください</div>
								</div>
								<div class="col" id="store_area_outline">
									<select id="store_area" name="store_area" class="form-select"
										aria-label="Default select example" name="store_area"
										required="required">
										<option value="" selected="selected">エリアを選択してください</option>
										<%
										for (Area area : areaList) {
										%>
										<option data-prefecture_id="<%=area.getPrefecture_id()%>"
											value="<%=area.getArea_id()%>"><%=area.getArea()%>
										</option>
										<%
										}
										%>
									</select>
									<div id="validationServerUsernameFeedback"
										class="invalid-feedback">エリアを入力してください</div>
								</div>
							</div>

							<div class="row mb-2">
								<label class="form-label"> 詳細住所 </label>

								<div class="col" id="store_address_outline">
									<input type="text" class="form-control" id="store_address" name="store_address" placeholder="詳細住所を都道府県から入力してください" required="required">
									<div id="validationServerUsernameFeedback"
										class="invalid-feedback">詳細住所を入力してください</div>
								</div>
							</div>
							<div class="row mb-2">
								<label class="form-label"> 営業時間 </label>

								<div class="row mb-2">

								<div class="col" id="store_start_hour_outline">
									<input type="time" class="form-control" id="store_start_hour" name="store_start_hour">
								</div>
								～
								<div class="col" id="store_end_hour_outline">
									<input type="time" class="form-control" id="store_end_hour" name="store_end_hour">
								</div>

							</div>
							</div>

							<div class="row mb-2">
								<label for="detail" class="form-label"> HP(URL) </label>
								<div class="col" id="store_URL_outline">
									<input type="url" class="form-control" id="store_URL" name="store_URL" placeholder="URLを入力してください">
									<div id="validationServerUsernameFeedback"
										class="invalid-feedback">URLを入力してください</div>
								</div>
							</div>

							<div class="row mb-2">
								<label for="detail" class="form-label"> 電話番号 </label>
								<div class="col" id="store_tel_outline">
									<input type="tel" class="form-control" id="store_tel" name="store_tel" placeholder="電話番号をハイフン(-)なしで入力してください">
									<div id="validationServerUsernameFeedback"
										class="invalid-feedback">電話番号を入力してください</div>
								</div>
							</div>

							<div class="row mb-2">
								<label for="detail" class="form-label"> 支払方法 </label>
								<div class="col" id="store_payment_outline">
									<input type="text" class="form-control" id="store_payment" name="store_payment" placeholder="現金/クレジット/各種電子決済 など入力してください">
								</div>
							</div>

							<div class="row mb-2">
								<label for="detail" class="form-label"> 施設情報 </label>
								<div class="col" id="store_facility_outline">
									<input type="text" class="form-control" id="store_facility" name="store_facility" placeholder="喫煙室/サービスカウンター/地下駐車場/お手洗いの有無 など入力してください">
								</div>
							</div>

							<div class="row mb-2">
								<label for="detail" class="form-label"> 実施サービス </label>
								<div class="col" id="store_service_outline">
									<input type="text" class="form-control" id="store_service" name="store_service" placeholder="試食会/レシピ説明会/各種イベント など入力してください">
								</div>
							</div>
						</div>
						<div class="modal-footer justify-content-center">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">閉じる</button>
							<button type="button" class="btn btn-primary" id="subm3" disabled>登録</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="/common_js.jsp" />
	<script type="text/javascript" src="js/register_product.js"></script>
	<script type="text/javascript" src="js/validation.js"></script>
	<script type="text/javascript" src="js/validation_forRegisterItem.js"></script>
</body>
</html>