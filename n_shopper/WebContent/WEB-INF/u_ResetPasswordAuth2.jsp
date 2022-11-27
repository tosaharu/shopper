
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>

<html lang="ja">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />

<title>パスワードリセット認証画面2</title>
<jsp:include page="/common_css.jsp" />

</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container" style="max-width: 540px;">
		<div
			class="d-flex flex-column justify-content-center align-items-center mx-auto">
			<br>
			<h2>ワンタイムパスワード入力</h2>
			<br>
			<!-- エラーメッセージが存在するときだけ表示する -->
			<div id="errormessage"></div>
			<div class="container">
				<form id="main_form">
					<div class="form-outline mb-1" id="one-time-pass_outline">
						<label for="one-time-pass" class="form-label">パスワード</label> <input
							type="text" class="form-control" id="one-time-pass" name="one-time-pass" pattern="\d{6}"
							required="required" placeholder="6桁の数字を入力してください" />
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">有効なパスワードを入力してください</div>
					</div>
					<br>
				<div class="row justify-content-center">
					<button type="button" id="subm1" class="btn btn-primary col-3 mx-2"
						disabled>送信</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 5">
		<div id="errorToast" class="toast fade hide" role="alert"
			aria-live="assertive" aria-atomic="true">
			<div class="toast-header">
				<img src="" width="20" height="20" class="rounded me-2" alt="result" />
				<strong class="me-auto"> </strong> <small>たったいま</small>
				<button type="button" class="btn-close" data-bs-dismiss="toast"
					aria-label="Close"></button>
			</div>
		</div>
	</div>
	<jsp:include page="/common_js.jsp" />
	<script type="text/javascript" src="js/additional.js"></script>
	<script type="text/javascript" src="js/validation.js"></script>
	<script type="text/javascript" src="js/validation_forResetPassAuth2.js"></script>
	<script type="text/javascript" src="js/resetPassAuth2.js"></script>
</body>
</html>