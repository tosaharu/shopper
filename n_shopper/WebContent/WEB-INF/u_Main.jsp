<%@page import="java.util.ArrayList"%>
<%@page import="model.U_User"%>
<%@page import="model.U_Product"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--
★★★旧仕様★★★
①ユーザーの最安値だけを取得するリスト
List<U_Product> myCategorizedProductList = (List<U_Product>) session.getAttribute("myCategorisedProductList");
②エリアの最安値だけを取得するリスト
List<U_Product> areaLowestPriceProductList = (List<U_Product>) session.getAttribute("areaLowestPriceProductList");
 -->

<%
U_User user = (U_User) session.getAttribute("loginUser");
ArrayList<ArrayList<U_Product>> myCategorisedProductLists = (ArrayList<ArrayList<U_Product>>) session
		.getAttribute("myCategorisedProductLists");
ArrayList<ArrayList<U_Product>> areaLowestPriceProductLists = (ArrayList<ArrayList<U_Product>>) session
		.getAttribute("areaLowestPriceProductLists");
List<U_Product> productList = (List<U_Product>) session.getAttribute("productList");
%>

<!DOCTYPE html>

<html lang="ja">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<title>ログイン</title>
<jsp:include page="/common_css.jsp" />
<!-- メイン画面用CSS -->
<link rel="stylesheet" href="css/u_main.css" />
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="d-flex justify-content-center align-items-center"></div>

	<div class="container">
		<div class="">
			<br>
			<h2>お買い得情報を登録・チェック</h2>
			<br>
			<form action="U_RegisterItem" method="get">
				<div class="row justify-content-start">
					<button type="submit" class="btn btn-primary col-3 mx-2"
						value="商品登録">商品登録</button>
					<button type="button" class="btn btn-light col-3 mx-2" value="商品検索">検索する</button>
					<button type="button" class="btn btn-light col-3 mx-2"
						onclick="location.href='/shopper/CouponListS'">クーポン</button>
				</div>
			</form>
			<br>
			<nav>
				<div class="nav nav-tabs" id="nav-tab" role="tablist">
					<button class="nav-link active" id="mylist-tab"
						data-bs-toggle="tab" data-bs-target="#mylist" type="button"
						role="tab" aria-controls="mylist" aria-selected="true">
						最安購入情報</button>
					<button class="nav-link" id="history-tab" data-bs-toggle="tab"
						data-bs-target="#history" type="button" role="tab"
						aria-controls="history" aria-selected="false">自分の登録履歴</button>
					<button class="nav-link" id="favorite-tab" data-bs-toggle="tab"
						data-bs-target="#favorite" type="button" role="tab"
						aria-controls="favorite" aria-selected="false">お気に入りリスト</button>
				</div>
			</nav>
			<div class="tab-content" id="nav-tabContent">
				<div class="tab-pane fade show active overflow-auto my-5"
					id="mylist" role="tabpanel" aria-labelledby="mylist-tab">
					<%
					if (myCategorisedProductLists != null && myCategorisedProductLists.size() != 0) {
					%>
					<ul>
						<%
						for (ArrayList<U_Product> myCategorizedProductList : myCategorisedProductLists) {
						%>
						<li class="card mb-1">
							<div class="card-body">
								<h5 class="card-title"><%=myCategorizedProductList.get(0).getItem_name()%></h5>
								<div class="row mb-1">
									<div class="list-header col col-lg-2">価格</div>
									<div class="list-header col col-lg-3">商品詳細</div>
									<div class="list-header col col-lg-2">ユーザー</div>
									<div class="list-header col col-lg-3">店舗</div>
									<div class="list-header col col-lg-2">日付</div>
								</div>
								<%
								for (U_Product myCategorizedProduct : myCategorizedProductList) {
								%>
									<div class="self row mb-1">
										<div class="col col-lg-2">
											<div class="price card-text p-2"><%=myCategorizedProduct.getPrice() + "円"%></div>
										</div>
										<div class="col col-lg-3">
											<div class="card-text p-2"><%=myCategorizedProduct.getItemDetail()%></div>
										</div>
										<div class="col col-lg-2">
											<div class="card-text p-2">あなた</div>
										</div>
										<div class="col col-lg-3">
											<div class="card-text p-2">
												<a
													href="/shopper/U_StoreInfo?id=<%=myCategorizedProduct.getStore_id()%>">
													<%=myCategorizedProduct.getStore_name()%>
												</a>
											</div>
										</div>
										<div class="col col-lg-2">
											<div class="card-text p-2"><%=myCategorizedProduct.getDate()%></div>
										</div>
									</div>
								<% }%>
								<% if (areaLowestPriceProductLists != null && areaLowestPriceProductLists.size() != 0) {%>
									<% int matchcnt = 0; %>
									<% for (ArrayList<U_Product> areaLowestPriceProductList : areaLowestPriceProductLists) {%>
										<% if (myCategorizedProductList.get(0).getItem_id() == areaLowestPriceProductList.get(0).getItem_id()) {%>
											<% matchcnt++; %>
											<% for (U_Product areaLowestPriceProduct : areaLowestPriceProductList) {%>
												<div class="row mb-1">
													<div class="col col-lg-2">
														<div class="price card-text p-2"><%=areaLowestPriceProduct.getPrice() + "円"%></div>
													</div>
													<div class="col col-lg-3">
														<div class="card-text p-2"><%=areaLowestPriceProduct.getItemDetail()%></div>
													</div>
													<div class="col col-lg-2">
														<div class="card-text p-2"><%=areaLowestPriceProduct.getUser_name()%></div>
													</div>
													<div class="col col-lg-3">
														<div class="card-text p-2">
															<a
																href="/shopper/U_StoreInfo?id=<%=areaLowestPriceProduct.getStore_id()%>">
																<%=areaLowestPriceProduct.getStore_name()%>
															</a>
														</div>
													</div>
													<div class="col col-lg-2">
														<div class="card-text p-2"><%=areaLowestPriceProduct.getDate()%></div>
													</div>
												</div>
											<% }%>
										<% }%>
									<% }%>
									<% if (matchcnt == 0) {%>
									選択中のエリアに商品情報がありません
									<% }%>
								<% }else{ %>
								選択中のエリアに商品情報がありません
								<% }	%>

							</div>
						</li>
						<% }%>
					</ul>
					<% }else{%>
					<div class="my-5">
						<p>まだ商品の投稿がありません</p>
						<p>購入した商品の情報を登録しましょう</p>
					</div>
					<% }%>

					<!--検討中、最低限品目ごとにリストを表示。可能であれば品目ごとの最安値を表示しつつ、最安意外を折りたたんで下に表示-->
				</div>
				<div class="tab-pane fade overflow-auto my-5" id="history"
					role="tabpanel" aria-labelledby="history-tab">
					<%
					if (productList != null && productList.size() != 0) {
					%>
					<table class="table caption-top">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">品目</th>
								<th scope="col">詳細</th>
								<th scope="col">店舗</th>
								<th scope="col">購入価格</th>
								<th scope="col">価格(/数量)</th>
								<th scope="col">数量</th>
								<th scope="col">特別割引</th>
								<th scope="col">購入日</th>
								<th scope="col">コメント</th>
							</tr>
						</thead>
						<%
						if (user != null) {
						%>
						<tbody>
							<%
							for (int i = 0; i < productList.size(); i++) {
							%>
							<tr>
								<th scope="row"><%=i + 1%></th>
								<td><%=productList.get(i).getItem_name()%></td>
								<td><%=productList.get(i).getItemDetail()%></td>
								<td><%=productList.get(i).getStore_name()%></td>
								<td><%=productList.get(i).getPrice()%></td>
								<td><%=productList.get(i).getPrice() / productList.get(i).getAmount()%></td>
								<td><%=productList.get(i).getAmount()%></td>
								<td><%=productList.get(i).getDiscount()%></td>
								<td><%=productList.get(i).getDate()%></td>
								<td><%=productList.get(i).getComment()%></td>
							</tr>
							<%
							}
							%>
						</tbody>
						<%
						} else {
						%>
						<%
						}
						%>
					</table>

					<%
					} else {
					%>
					<div class="my-5">
						<p>まだ商品の投稿がありません</p>
						<p>購入した商品の情報を登録しましょう</p>
					</div>

					<%
					}
					%>

				</div>
				<div class="tab-pane fade overflow-auto my-5" id="favorite"
					role="tabpanel" aria-labelledby="favorite-tab">
					<table class="table caption-top">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">品目</th>
								<th scope="col">詳細</th>
								<th scope="col">店舗</th>
								<th scope="col">購入価格</th>
								<th scope="col">価格(/数量)</th>
								<th scope="col">数量</th>
								<th scope="col">特別割引</th>
								<th scope="col">購入日</th>
								<th scope="col">コメント</th>
								<th scope="col">エリア最安店舗</th>
								<th scope="col">エリア最安価格(/数量)</th>
								<th scope="col">エリア最安投稿者</th>
								<th scope="col">エリア最安購入日</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">1</th>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
								<td>サンプル</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/common_js.jsp" />
</body>
</html>