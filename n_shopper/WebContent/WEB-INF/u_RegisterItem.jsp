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

List<MainItem> mainItem_list_db = (ArrayList<MainItem>) request.getAttribute("mainItem_list");
List<SubItem> subItem_list_db = (ArrayList<SubItem>) request.getAttribute("subItem_list");
List<Item> item_list_db = (ArrayList<Item>) request.getAttribute("item_list");
List<Store> store_list_db = (ArrayList<Store>) request.getAttribute("store_list");

List<Region> region_List = (List<Region>) request.getAttribute("regionList");
List<Prefecture> prefecture_List = (List<Prefecture>) request.getAttribute("prefectureList");
List<Area> area_List = (List<Area>) request.getAttribute("areaList");

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

	<div class="container">


		<header>
			<h1>商品登録ページ</h1>
		</header>

		<%
		if (errorMsg != null) {
		%>
		<p><%=errorMsg%></p>
		<%
		}
		%>

		<form action="U_RegisterItem" method="post">
			<div class="row mb-2">
				<label for="today" class="form-label">購入日</label>
				<div class="col">
					<input type="date" name="date" id="today" required="required"
						oninput="date_limit()">
				</div>
				<div class="col">
					<button type="button" onclick="today_select()"
						class="btn btn-outline-primary col mx-2">今日の日付</button>
				</div>
			</div>
			<div class="row mb-2">
				<label for="areas" class="form-label">購入エリア</label>

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
						disabled="disabled"
						onchange="CheckSelectedArea(this)">

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
				<div class="col-4">
					<select id="store_id"  name="store_id" class="form-select"
					aria-label="Default select example" disabled="disabled"
					required="required">

						<option value="">店舗を選択してください</option>
						<%
						for (Store storeList : store_list_db) {
							//if文のための型変換
							int storeID = storeList.getStore_id();
							System.out.println(storeID);
							System.out.println("check_idの取得" + check_id.getStore_id());
						%>
						<option data-area_id="<%=storeList.getArea_id()%>"
						value="<%=storeID%>"
							<%if (storeID == check_id.getStore_id()) {
	System.out.println("Check_idとstoreIDが一致した");%>
							selected="selected" <%}%>>

							<%=storeList.getStoreName()%>
						</option>
						<%
						}
						%>



					</select>
				</div>
				<div class="col-4">
					<button type="button" class="btn btn-outline-primary col mx-2"
						onclick="location.href='{% url 'index'%}'">新規登録</button>

				</div>
			</div>

			<div class="row mb-2">
				<label for="category" class="form-label">品目</label>
				<div class="col" id="category">
					<select id="mainitem" class="form-select"
						aria-label="Default select example" name="mainitem"
						onchange="CheckSelectedMainItem(this)"
						required="required">
						<option value="">大品目を選択してください</option>
						<%
						for (MainItem mainItem : mainItem_list_db) {
						%>
						<option
							value="<%=mainItem.getMainItem_id()%>"
							<%if (mainItem.getMainItem_id() == check_id.getMainItem_id()) {%>
							selected <%}%>>
							<%=mainItem.getMainItem()%>
						</option>
						<%
						}
						%>
					</select>
				</div>
				<div class="col">
					<select id="subitem" class="form-select"
						aria-label="Default select example" name="subitem"
						onchange="CheckSelectedSubItem(this)" disabled="disabled"
						required="required">
						<option value="">中品目を選択してください</option>
						<%
						for (SubItem subItem : subItem_list_db) {
						%>
						<option data-main_item_id="<%=subItem.getMainItem_id()%>"
							value="<%=subItem.getSubItem_id()%>"
							<%if (subItem.getSubItem_id() == check_id.getSubItem_id()) {%>
							selected <%}%>>
							<%=subItem.getSubItem()%>
						</option>
						<%
						}
						%>
					</select>
				</div>
				<div class="col">
					<select id="item" class="form-select"
						aria-label="Default select example" name="item" disabled="disabled"
						required="required">
						<option value="">小品目を選択してください</option>
						<%
						for (Item item : item_list_db) {
						%>
						<option data-sub_item_id="<%=item.getSubItem_id()%>"
							value="<%=item.getItem_id()%>"
							<%if (item.getItem_id() == check_id.getItem_id()) {%> selected
							<%}%>>
							<%=item.getItem()%>
						</option>
						<%
						}
						%>
					</select>
				</div>
			</div>
			<div class="row mb-2">
				<label for="detail" class="form-label">商品詳細名</label>

				<%
				System.out.println("test 入力");
				System.out.println(u_p_s.getItemDetail_s());
				%>
				<div class="col">
					<input type="text" id="detail" class="form-control" name="detail"
					<%if(u_p_s.getItemDetail_s()!=null){ %>

						value="<%=u_p_s.getItemDetail_s()%>"

						<%}else{ %>
						value=""
						<%} %>

						 placeholder="詳細を入力してください"
						required="required">
				</div>
			</div>
			<div class="row mb-2">
				<label for="detail" class="form-label">価格、数量、割引有無</label>

				<div class="col">
					<input type="number" id="price" class="form-control" name="price"
						value="<%=u_p_s.getPrice_s()%>" required="required"
						placeholder="価格を入力してください" oninput="not0orless_price()">
					<%
					if (errorMsg_price != null) {
					%>
					<br>
					<%=errorMsg_price%>
					<%
					}
					%>
				</div>

				<div class="col">
					<input type="number" id="amount" class="form-control" name="amount"
						value="<%=u_p_s.getAmount_s()%>" required="required"
						placeholder="数量を入力してください" oninput="not0orless_amount()">
						<span id="error_amount"></span>
					<%
					if (errorMsg_amount != null) {
					%>
					<br>
					<%=errorMsg_amount%>
					<%
					}
					%>
				</div>
				<div class="col">
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
				</div>
			</div>
			<div class="row mb-2">
				<label for="comment" class="form-label">メモ</label>

				<div class="col">
					<input type="text" id="detail" class="form-control" name="comment"
						<%if (u_p_s.getComment_s() != null) {%>
						value="<%=u_p_s.getComment_s()%>" <%} else {%> value="" <%}%>
						placeholder="メモを入力してください" required="required">
				</div>
			</div>
			<br>
			<div class="row justify-content-center">
				<button type="button" class="btn btn-secondary col-3 mx-2"
					onclick="history.back(-1);return false;">戻る</button>
				<button type="submit" name="" id="u_add_list"
					class="btn btn-primary col-3 mx-2">登録</button>
			</div>
		</form>
	</div>


	<jsp:include page="/common_js.jsp" />
	<script type="text/javascript" src="js/reg_product.js"></script>
	<script type="text/javascript">
		function today_select() {
			//alert("aaaaaaaaaaa");
			var today = new Date();
			today.setDate(today.getDate());
			var yyyy = today.getFullYear();
			var mm = ("0" + (today.getMonth() + 1)).slice(-2);
			var dd = ("0" + today.getDate()).slice(-2);
			document.getElementById("today").value = yyyy + '-' + mm + '-' + dd;
		}
		function date_limit() {
			//alert("日付が入力されました");

			//今日の日付を取得する
			var today = new Date();
			today.setDate(today.getDate());

			var yyyy = today.getFullYear();
			var mm = ("0" + (today.getMonth() + 1)).slice(-2);
			var dd = ("0" + today.getDate()).slice(-2);


			var val_today = yyyy + '-' + mm + '-' + dd;

			//入力された値を取得する
			var date = document.getElementById("today").value;


			if (date != "" && val_today != "") {
				//alert("date isnt null && val_today isnt null");

				// 日付オブジェクトを生成
				var fromDate = new Date(date);
				var today = new Date(val_today);

				// 当日の日付と入力された日付の差を計算
				var judge = ( today-fromDate);

				if (judge < 0) {
					alert("今日までの日付を指定してください。");
					//TODO ：内容をリセットさせる方法を考える。
					document.getElementById("today").value='';

					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}

		}

		function not0orless_price(){
			//入力された値を取得する
			var price = document.getElementById("price").value;

			if(price!=null){
				if(price<=0){
					alert("１以上の数字を入力してください。");
					document.getElementById("price").value='';
					return false;
				}else{
					document.getElementById("error_price").value='';
					return true;
				}
			}
		}


		function not0orless_amount(){
			//入力された値を取得する
			var amount = document.getElementById("amount").value;

			if(amount!=null){
				if(amount<=0){
					alert("１以上の数字を入力してください。");
					document.getElementById("amount").value='';
				}
			}
		}
	</script>
</body>
</html>


