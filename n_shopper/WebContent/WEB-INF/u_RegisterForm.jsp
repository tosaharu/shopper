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
%>
<!DOCTYPE html>

<html lang="ja">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<title>新規会員登録</title>
<jsp:include page="/common_css.jsp" />
<!-- 新規登録画面用CSS -->
<link rel="stylesheet" href="css/u_registerform.css" />
</head>

<body>
	<jsp:include page="/header.jsp" />

	<div class="container">
		<div
			class="d-flex flex-column justify-content-center align-items-center mx-auto" style="width:100%;max-width:720px;" >
			<div class="">
				<br>
				<h2>新規会員登録</h2>
				<br>
				<form action="/shopper/U_RegisterUser" method="post" id="main_form">
					<div class="form-outline mb-1" id="email_outline">
						<label for="email"  class="form-label">メールアドレス</label>
						<input type="email"  class="form-control" id="email" name="email" required="required" placeholder="abc12345@example.co.jp"/>
				    	<div id="validationServerUsernameFeedback" class="invalid-feedback">有効なメールアドレスを入力してください</div>
					</div>
					<div class="form-outline mb-1" id="pass_outline">
						<label class="form-label" for="password1">パスワード</label>
						<input	type="password" id="password1" class="form-control" required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="半角の英小文字・英大文字・数字を含む8文字以上"/>
				    	<div id="validationServerUsernameFeedback" class="invalid-feedback">パスワードを入力してください</div>
					</div>

					<div class="form-outline mb-1" id="pass2_outline">
						<label class="form-label" for="password2">パスワード（再入力）</label>
						<input 	type="password" id="password2" class="form-control"  name="password" required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="半角の英小文字・英大文字・数字を含む8文字以上"/>
				    	<div id="validationServerUsernameFeedback" class="invalid-feedback">パスワードが一致しません</div>
					</div>
					<div class="form-outline mb-1" id="name_outline">
						<label class="form-label" for="name">ユーザー名</label>
						<input	type="text" id="name" class="form-control" name="name" required="required" placeholder="ユーザー名"/>
				    	<div id="validationServerUsernameFeedback" class="invalid-feedback">ユーザー名を入力してください</div>
					</div>
					<div class="row mb-1">
						<div class="form-outline col-lg-5" id="gender_outline">
							<label class="form-label" for="flexRadioDefault">性別</label>
							<div id="gender">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="gender" id="male" value="1" required>
									<label	class="form-check-label" for="male"> 男性 </label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="gender" id="female" value="2" required>
									<label class="form-check-label"  for="female"> 女性 </label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="gender" id="other" value="3" required>
									<label class="form-check-label"  for="other"> 回答しない </label>
								</div>
				    			<div id="invalid-feedback-gender" class="invalid-feedback">性別を入力してください</div>
							</div>
						</div>
						<div class="form-outline col-lg-7">
							<label for="exampleInputEmail1" class="form-label">生年月日</label>
							<div class="row">
								<div class="col" id="year-select_outline">
									<select id="year-select" class="form-select"
										aria-label="Default select example" name="year" required="required"
										onchange="CheckSelectedMonth()">
									</select>
							    	<div id="validationServerUsernameFeedback" class="invalid-feedback">年を入力してください</div>
								</div>
								<div class="col" id="month-select_outline">
									<select id="month-select" class="form-select"
										aria-label="Default select example" name="month" required="required"
										onchange="CheckSelectedMonth()">
									</select>
							    	<div id="validationServerUsernameFeedback" class="invalid-feedback">月を入力してください</div>
								</div>
								<div class="col" id="day-select_outline">
									<select id="day-select" class="form-select"
										aria-label="Default select example" name="day" required="required">
									</select>
							    	<div id="validationServerUsernameFeedback" class="invalid-feedback">日を入力してください</div>
								</div>

							</div>
						</div>
					</div>
					<label for="exampleInputEmail1" class="form-label">エリア選択</label>
					<div class="row">
						<div class="col" id="region_outline">
							<select id="region"  class="form-select" aria-label="Default select example"
								onchange="CheckSelectedRegion(this)" required="required">
								<option value="" selected="selected">地方を選択してください</option>
								<%
								for (Region region : region_List) {
								%>
								<option value="<%=region.getRegion_id()%>"><%=region.getRegion()%></option>
								<%
								}
								%>
							</select>
					    	<div id="validationServerUsernameFeedback" class="invalid-feedback">地方を入力してください</div>
						</div>
						<div class="col" id="prefecture_outline">
							<select id="prefecture" class="form-select" aria-label="Default select example"
								disabled="disabled" onchange="CheckSelectedPrefecture(this)" required="required">
								<option value="" selected="selected">都道府県を選択してください</option>
								<%
								for (Prefecture prefecture : prefecture_List) {
								%>
								<option data-region_id="<%=prefecture.getRegion_id()%>"
									value="<%=prefecture.getPrefecture_id()%>"><%=prefecture.getPrefecture()%></option>
								<%
								}
								%>
							</select>
					    	<div id="validationServerUsernameFeedback" class="invalid-feedback">都道府県を入力してください</div>
						</div>
						<div class="col" id="area_outline">
							<select id="area" class="form-select" aria-label="Default select example" name="area"
								disabled="disabled" required="required">
								<option value="" selected="selected">エリアを選択してください</option>
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
					    	<div id="validationServerUsernameFeedback" class="invalid-feedback">エリアを入力してください</div>
						</div>

					</div>
					<!-- Button trigger modal -->
					<br> <br>
					<div class="row justify-content-center">

						<button type="button" class="btn btn-secondary col-3 mx-2"
							onclick="history.back(-1);return false;">戻る</button>
						<button type="button" id="subm1" class="btn btn-primary col-3 mx-2"
							data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="sendFormData()" disabled>
							登録</button>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">登録内容確認</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<table class="table">
										<tbody>
											<tr>
												<th scope="row" style="border-style: none;">メールアドレス</th>
												<td id="modal_email" style="border-style: none;"></td>
											</tr>
											<tr>
												<th scope="row" style="border-style: none;">パスワード</th>
												<td id="modal_password"  style="border-style: none;"></td>
											</tr>
											<tr>
												<th scope="row" style="border-style: none;">ニックネーム</th>
												<td id="modal_name" style="border-style: none;"></td>
											</tr>
											<tr>
												<th scope="row" style="border-style: none;">性別</th>
												<td id="modal_gender" style="border-style: none;"></td>
											</tr>
											<tr>
												<th scope="row" style="border-style: none;">生年月日</th>
												<td id="modal_birthday" style="border-style: none;"></td>
											</tr>
											<tr>
												<th scope="row" style="border-style: none;">エリア</th>
												<td id="modal_area"  style="border-style: none;"></td>
											</tr>
										</tbody>
									</table>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">閉じる</button>
									<button type="submit" class="btn btn-primary">登録</button>
								</div>
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
	<jsp:include page="/common_js.jsp" />
	<script type="text/javascript" src="js/additional.js"></script>
	<script type="text/javascript" src="js/validation.js"></script>
	<script type="text/javascript" src="js/validation_forNewRegister.js"></script>
</body>

</html>