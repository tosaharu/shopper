<%@page import="model.U_User"%>
<%@page import="model.Area"%>
<%@page import="model.Prefecture"%>
<%@page import="model.Region"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Region> region_List = (List<Region>) request.getAttribute("regionList");
List<Prefecture> prefecture_List = (List<Prefecture>) request.getAttribute("prefectureList");
List<Area> area_List = (List<Area>) request.getAttribute("areaList");
U_User user = (U_User) session.getAttribute("loginUser");
%>

<%
if (user != null) {
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand" href="/shopper/U_Main">Shopper</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="navbar-text me-4">ようこそ<%=user.getName()%>さん
				</li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="/shopper/U_Main">ホーム</a></li>
				<!-- <li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> 他のユーザー </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">フォロー</a> <a
							class="dropdown-item" href="#">フォロワー</a> <a class="dropdown-item"
							href="#">人気のユーザー</a>
					</div></li>  --!>

				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="/shopper/U_ChangeInfo">会員情報変更</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/shopper/U_Logout" tabindex="-1">ログアウト</a></li>
			</ul>
			<div class="navbar-text">選択中エリア：</div>
				<form action="/shopper/U_HeaderAreaSetter" method="get">
				<div class="d-flex">
					<select id="header_region" class="form-select" name="header_region"
						aria-label="Default select example"
						onchange="CheckSelectedHeaderRegion(this)">
						<option>地方</option>
						<%
						for (Region region : region_List) {
						%>
						<option value="<%=region.getRegion_id()%>"
							<%if (region.getRegion_id() == user.getRegion_id()) {%> selected
							<%}%>>
							<%=region.getRegion()%>
						</option>
						<%
						}
						%>
					</select>
					<!--
						<%=user.getRegion_id()%>
						<%=user.getRegion_name()%>
						 -->
					<select id="header_prefecture" class="form-select"
						name="header_prefecture" aria-label="Default select example"
						disabled="disabled" onchange="CheckSelectedHeaderPrefecture(this)">
						<option>都道府県</option>
						<%
						for (Prefecture prefecture : prefecture_List) {
						%>
						<option data-header_region_id="<%=prefecture.getRegion_id()%>"
							value="<%=prefecture.getPrefecture_id()%>"
							<%if (prefecture.getPrefecture_id() == user.getPrefecture_id()) {%>
							selected <%}%>>
							<%=prefecture.getPrefecture()%>
						</option>
						<%
						}
						%>
					</select>
					<!--
						<%=user.getPrefecture_id()%>
						<%=user.getPrefecture_name()%>
						 -->
						<select id="header_area" class="form-select" name="header_area"
							aria-label="Default select example" disabled="disabled"
							onchange="submit(this.form)">
							<option>エリア</option>
							<%
							for (Area area : area_List) {
							%>
							<option data-header_prefecture_id="<%=area.getPrefecture_id()%>"
								value="<%=area.getArea_id()%>"
								<%if (area.getArea_id() == user.getArea_id()) {%> selected <%}%>>
								<%=area.getArea()%>
							</option>
							<%
							}
							%>
						</select>
				</div>
			</form>
			<!-- エリアを変更した時点で、formをpostし、セッションを更新して同ページを再呼び出ししたい -->
			<!-- 使いまわしているヘッダーから、各ページに対応するサーブレットを呼び出すのはどのようにするのがいいか -->
		</div>
	</div>
</nav>

<%
} else {
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary"" >
	<div class="container-fluid">
		<a class="navbar-brand" href="/shopper/U_Main">Shopper</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="/shopper/U_Main">ホーム</a></li>
				<li class="nav-item"><a class="nav-link active"
					href="/shopper/U_Login" tabindex="-1">会員登録/ログイン</a></li>
			</ul>
			<div class="d-flex">
				<select id="header_region" class="form-select"
					aria-label="Default select example"
					onchange="CheckSelectedHeaderRegion(this)">
					<option selected=" selected">地方</option>
					<%
					for (Region region : region_List) {
					%>
					<option value="<%=region.getRegion_id()%>"><%=region.getRegion()%></option>
					<%
					}
					%>
				</select> <select id="header_prefecture" class="form-select"
					aria-label="Default select example" disabled="disabled"
					onchange="CheckSelectedHeaderPrefecture(this)">
					<option selected="selected">都道府県</option>
					<%
					for (Prefecture prefecture : prefecture_List) {
					%>
					<option data-header_region_id="<%=prefecture.getRegion_id()%>"
						value="<%=prefecture.getPrefecture_id()%>"><%=prefecture.getPrefecture()%></option>
					<%
					}
					%>
				</select> <select id="header_area" class="form-select"
					aria-label="Default select example" name="area" disabled="disabled">
					<option selected=" selected">エリア</option>
					<%
					for (Area area : area_List) {
					%>
					<option data-header_prefecture_id="<%=area.getPrefecture_id()%>"
						value="<%=area.getArea_id()%>"><%=area.getArea()%>
					</option>
					<%
					}
					%>
				</select>
			</div>
			<!-- エリアを変更した時点で、formをpostし、セッションを更新して同ページを再呼び出ししたい -->
			<!-- 使いまわしているヘッダーから、各ページに対応するサーブレットを呼び出すのはどのようにするのがいいか -->
		</div>
	</div>
</nav>
<%
}
%>
<!-- additional -->
<script type="text/javascript" src="js/area.js"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<!-- popper.js & bootstrap.js & es-module-shims -->
    <script async src="https://cdn.jsdelivr.net/npm/es-module-shims@1/dist/es-module-shims.min.js" crossorigin="anonymous"></script>
    <script type="importmap">
    {
      "imports": {
        "@popperjs/core": "https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js",
        "bootstrap": "https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.esm.min.js"
      }
    }
    </script>