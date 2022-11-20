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
			<form method="post" class="container my-4" id="main_form">
				<h3 class="my-2">基本情報変更</h3>
				<div class="form-outline mb-1" id="email_outline">
					<label for="email"  class="form-label">メールアドレス</label>
					<input type="email"  class="form-control" id="email" name="email" value="<%=user.getMail()%>" required="required" placeholder="abc12345@example.co.jp"/>
			    	<div id="validationServerUsernameFeedback" class="invalid-feedback">有効なメールアドレスを入力してください</div>
				</div>
				<div class="form-outline mb-1" id="name_outline">
					<label class="form-label" for="name">ユーザー名</label>
					<input	type="text" id="name" class="form-control" name="name" value="<%=user.getName()%>" required="required" placeholder="ユーザー名"/>
			    	<div id="validationServerUsernameFeedback" class="invalid-feedback">ユーザー名を入力してください</div>
				</div>
				<div class="row mb-1">
					<div class="form-outline col-lg" id="gender_outline">
						<label class="form-label" for="flexRadioDefault">性別</label>
						<div id="gender">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender" id="male" value="1" required
									<%if(user.getGender()==1){ %>
									checked="checked"
									<%} %>
								>
								<label	class="form-check-label" for="male"> 男性 </label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender" id="female" value="2" required
									<%if(user.getGender()==2){ %>
									checked="checked"
									<%} %>
								>
								<label class="form-check-label"  for="female"> 女性 </label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender" id="other" value="3" required
									<%if(user.getGender()==3){ %>
									checked="checked"
									<%} %>
								>
								<label class="form-check-label"  for="other"> 回答しない </label>
							</div>
			    			<div id="invalid-feedback-gender" class="invalid-feedback">性別を入力してください</div>
						</div>
					</div>
					<div class="form-outline col-lg">
						<label for="exampleInputEmail1" class="form-label">生年月日</label>
						<div class="row">
							<div class="col" id="year-select_outline">
								<select id="year-select" class="form-select"
									name="year" required="required"
									onchange="CheckSelectedMonth()" aria-label="誕生年" disabled>
								</select>
						    	<div id="validationServerUsernameFeedback" class="invalid-feedback">年を入力してください</div>
							</div>
							<div class="col" id="month-select_outline">
								<select id="month-select" class="form-select"
									name="month" required="required"
									onchange="CheckSelectedMonth()" aria-label="誕生月" disabled>
								</select>
						    	<div id="validationServerUsernameFeedback" class="invalid-feedback">月を入力してください</div>
							</div>
							<div class="col" id="day-select_outline">
								<select id="day-select" class="form-select"
									name="day" required="required" aria-label="誕生日" disabled>
								</select>
						    	<div id="validationServerUsernameFeedback" class="invalid-feedback">日を入力してください</div>
							</div>

						</div>
					</div>
				</div>
				<label for="areas" class="form-label">エリア選択</label>
				<div class="row">
					<div class="col" id="region_outline">
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
				    	<div id="validationServerUsernameFeedback" class="invalid-feedback">地方を入力してください</div>
					</div>
					<div class="col" id="prefecture_outline">
						<select id="prefecture" class="form-select"
							aria-label="Default select example"
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
				    	<div id="validationServerUsernameFeedback" class="invalid-feedback">都道府県を入力してください</div>
					</div>
					<div class="col" id="area_outline">
						<select id="area" class="form-select"
							aria-label="Default select example" name="area"
							required="required">
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
				    	<div id="validationServerUsernameFeedback" class="invalid-feedback">エリアを入力してください</div>
					</div>
				</div>
				<br>
				<div class="row justify-content-center">
					<button type="button" id="subm1"  class="btn btn-primary col-3" disabled onclick="changeInfo()">変更する</button>
				</div>

					<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 5">
					  <div
					    id="changeInfoToast"
					    class="toast fade hide"
					    role="alert"
					    aria-live="assertive"
					    aria-atomic="true"
					  >
					    <div class="toast-header">
					      <img src="" width="20" height="20" class="rounded me-2" alt="result" />
					      <strong class="me-auto">
					      </strong>
					      <small>たったいま</small>
					      <button
					        type="button"
					        class="btn-close"
					        data-bs-dismiss="toast"
					        aria-label="Close"
					      ></button>
					    </div>
					  </div>
					  <div
					    id="changePassToast"
					    class="toast fade hide"
					    role="alert"
					    aria-live="assertive"
					    aria-atomic="true"
					  >
					    <div class="toast-header">
					      <img src="" width="20" height="20" class="rounded me-2" alt="result" />
					      <strong class="me-auto">
					      </strong>
					      <small>たったいま</small>
					      <button
					        type="button"
					        class="btn-close"
					        data-bs-dismiss="toast"
					        aria-label="Close"
					      ></button>
					    </div>
					  </div>
					</div>
			</form>


			<form class="container my-2" id="sub_form">
				<h3 class="my-2">パスワード変更</h3>
					<div class="form-outline mb-1" id="pass_outline">
						<label class="form-label" for="password">現在のパスワード</label>
						<input	type="password" id="password" class="form-control"  name="password"  required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="半角の英小文字・英大文字・数字を含む8文字以上"/>
				    	<div id="validationServerUsernameFeedback" class="invalid-feedback">パスワードを入力してください</div>
					</div>
					<div class="form-outline mb-1" id="newpass_outline">
						<label class="form-label" for="newpassword1">新しいパスワード</label>
						<input	type="password" id="newpassword1" class="form-control" required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="半角の英小文字・英大文字・数字を含む8文字以上"/>
				    	<div id="validationServerUsernameFeedback" class="invalid-feedback">パスワードを入力してください</div>
					</div>
					<div class="form-outline mb-1" id="newpass2_outline">
						<label class="form-label" for="newpassword2">新しいパスワード（再入力）</label>
						<input 	type="password" id="newpassword2" class="form-control"  name="newpassword" required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="半角の英小文字・英大文字・数字を含む8文字以上"/>
				    	<div id="validationServerUsernameFeedback" class="invalid-feedback">パスワードが一致しません</div>
					</div>


				<input type="hidden" name="pass" value="<%=user.getPass()%>">
				<!-- 現在のパスワードをinfoPass.jspに送る -->
				<input type="hidden" name="id" value="<%=user.getUser_id()%>">
				<!-- 現在のパスワードをinfoPass.jspに送る -->
				<div class="row justify-content-center">
					<button type="button" id="subm2"  class="btn btn-primary col-3" disabled onclick="changePass()">変更する</button>
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
	<script type="text/javascript" src="js/validation.js"></script>
	<script type="text/javascript" src="js/validation_forChangeInfo.js"></script>
	<script type="text/javascript" src="js/changeinfo.js"></script>
</body>
</html>