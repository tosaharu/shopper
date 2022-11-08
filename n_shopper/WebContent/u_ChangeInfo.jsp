<%@page import="model.Area"%>
<%@page import="model.Prefecture"%>
<%@page import="model.Region"%>
<%@page import="java.util.List"%>
<%@page import="model.U_User,java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//ユーザーデータ一覧
U_User user = (U_User) session.getAttribute("loginUser");
List<Region> region_List = (List<Region>) request.getAttribute("regionList");
List<Prefecture> prefecture_List = (List<Prefecture>) request.getAttribute("prefectureList");
List<Area> area_List = (List<Area>) request.getAttribute("areaList");
%>
<!DOCTYPE html>

<html lang="ja">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<title>会員情報変更</title>
<jsp:include page="/common_css.jsp" />
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container">
		<div
			class="d-flex flex-column justify-content-center align-items-center mx-auto" style="width:100%;max-width:720px;" >
			<br>
			<h2>会員情報変更</h2>
			<form action="/shopper/U_ChangeInfo" method="post" class="container my-4">
				<!--
		<li>メールアドレス<input type="text" name="mail"
			placeholder="</%=user.getMail()%>" /></li>
		<li>生年月日<input type="text" name="birthday"
				placeholder="</%=user.getBirthday()%>" /> <label
					for="exampleInputEmail1" class="form-label">エリア選択</label>
		 -->
				<h3 class="my-2">基本情報変更</h3>
				<div class="form-outline mb-1">
					<label for="email" class="form-label">メールアドレス</label> <input
						type="email" class="form-control" id="email" name="email"
						value="<%=user.getMail()%>" placeholder="メールアドレスを入力してください" required="required">
						<span class="label label-danger">${errorMessage}</span>
				</div>
				<div class="form-outline mb-1">
					<label class="form-label" for="name">ニックネーム</label> <input
						type="text" id="name" class="form-control" name="name"
						value="<%=user.getName()%>" placeholder="ニックネームを入力してくださいください" required="required"/>
				</div>
				<div class="row mb-1">
					<div class="form-outline col-lg">
						<label class="form-label" for="flexRadioDefault">性別</label>
						<div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender"
									id="male" value="1"
									<%if(user.getGender()==1){ %>
									checked="checked"
									<%} %>
									> <label class="form-check-label"
									for="male"> 男性 </label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender"
									id="female" value="2"
									<%if(user.getGender()==2){ %>
									checked="checked"
									<%} %>
									> <label class="form-check-label"
									for="female"> 女性 </label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender"
									id="other" value="3"
									<%if(user.getGender()==3){ %>
									checked="checked"
									<%} %>

									> <label
									class="form-check-label" for="other"> 回答しない </label>
							</div>
						</div>
						<!--
										<li>性別<%=user.getGender()%> <input type="radio" name="gender"
				value="0">男<br> <input type="radio" name="gender"
				value="1">女<br> <input type="radio" name="gender"
				value="2">不明<br>
			</li>
										 -->
					</div><!-- 依頼：生年月日が引き継げたら助かります。 -->
					<div class="form-outline col-lg">
						<label for="exampleInputEmail1" class="form-label">生年月日</label>
						<div class="row">
							<div class="col">
								<select id="year-select" class="form-select"
									aria-label="Default select example" name="year">
								</select>
							</div>
							<div class="col">
								<select id="month-select" class="form-select"
									aria-label="Default select example" name="month"
									onchange="CheckSelectedMonth(this)">
								</select>
							</div>
							<div class="col">
								<select id="day-select" class="form-select"
									aria-label="Default select example" name="day">
								</select>
							</div>

						</div>
					</div>
				</div>
				<label for="areas" class="form-label">エリア選択</label>
				<div class="row">
					<div class="col">
						<select id="region" class="form-select"
							aria-label="Default select example"
							onchange="CheckSelectedRegion(this)" required="required">
							<option value="" >地方を選択してください</option>
							<%
							for (Region region : region_List) {
							%>
							<option value="<%=region.getRegion_id()%>"
							<%if(region.getRegion_id()==user.getRegion_id()){ %>
							selected="selected"
							<%} %>
							><%=region.getRegion()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="col">
						<select id="prefecture" class="form-select"
							aria-label="Default select example" disabled="disabled"
							onchange="CheckSelectedPrefecture(this)" required="required">
							<option value="" >都道府県を選択してください</option>
							<%
							for (Prefecture prefecture : prefecture_List) {
							%>
							<option data-region_id="<%=prefecture.getRegion_id()%>"
								value="<%=prefecture.getPrefecture_id()%>"
								<%if(prefecture.getPrefecture_id()==user.getPrefecture_id()){ %>
							selected="selected"
							<%} %>
								><%=prefecture.getPrefecture()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="col">
						<select id="area" class="form-select"
							aria-label="Default select example" name="area"
							disabled="disabled" required="required">
							<option value="" >エリアを選択してください</option>
							<%
							for (Area area : area_List) {
							%>
							<option data-prefecture_id="<%=area.getPrefecture_id()%>"
								value="<%=area.getArea_id()%>"
								<%if(area.getArea_id()==user.getArea_id()){ %>
							selected="selected"
							<%} %>
								><%=area.getArea()%>
							</option>
							<%
							}
							%>
						</select>
					</div>
				</div>
				<br>
				<div class="row justify-content-center">
					<button type="submit" class="btn btn-primary col-3">変更する</button>
				</div>
			</form>


			<form action="/shopper/infoPass.jsp" class="container my-2">
				<h3 class="my-2">パスワード変更</h3>
				<input type="hidden" name="pass" value="<%=user.getPass()%>">
				<!-- 現在のパスワードをinfoPass.jspに送る -->
				<input type="hidden" name="id" value="<%=user.getUser_id()%>">
				<!-- 現在のパスワードをinfoPass.jspに送る -->
				<div class="row justify-content-center">
					<button type="submit" class="btn btn-primary col-3">変更する</button>
				</div>
			</form>


			<form action="/shopper/U_Quit" method="post" class="container my-2">
				<h3 class="my-2">退会</h3>
				<br>
				<input type="hidden" name="active" value="1">
				<div class="row justify-content-center">
					<button type="submit" class="btn btn-primary col-3">退会する</button>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/common_js.jsp" />
	<script type="text/javascript" src="js/additional.js"></script>
</body>
</html>