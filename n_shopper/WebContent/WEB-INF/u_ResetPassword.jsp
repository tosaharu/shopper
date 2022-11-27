
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

<title>パスワードリセット</title>
<jsp:include page="/common_css.jsp" />

</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container" style="max-width: 540px;">
		<div
			class="d-flex flex-column justify-content-center align-items-center mx-auto">
			<br>
			<h2>パスワード再設定</h2>
			<br>
			<!-- エラーメッセージが存在するときだけ表示する -->
			<div id="errormessage"></div>
			<div class="container">
				<form action="U_ResetPassword" method="post" id="main_form">
					<div class="form-outline mb-1" id="newpass_outline">
						<label class="form-label" for="newpassword1">新しいパスワード</label> <input
							type="password" id="newpassword1" class="form-control"
							required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							placeholder="半角の英小文字・英大文字・数字を含む8文字以上" />
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">パスワードを入力してください</div>
					</div>
					<div class="form-outline mb-1" id="newpass2_outline">
						<label class="form-label" for="newpassword2">新しいパスワード（再入力）</label>
						<input type="password" id="newpassword2" class="form-control"
							name="newpassword" required="required"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							placeholder="半角の英小文字・英大文字・数字を含む8文字以上" />
						<div id="validationServerUsernameFeedback"
							class="invalid-feedback">パスワードが一致しません</div>
					</div>
					<br>
					<div class="row justify-content-center">
						<button type="button" id="subm1"
							class="btn btn-primary col-3 mx-2" data-bs-toggle="modal"
							data-bs-target="#modal" onclick="sendFormData()" disabled>登録</button>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="modal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">パスワード確認</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<table class="table">
										<p>
											下記のパスワードを新しく設定します。<br>よろしいでしょうか？
										</p>
										<tbody>
											<tr>
												<th scope="row" style="border-style: none;">パスワード</th>
												<td id="modal_newpassword" style="border-style: none;"></td>
											</tr>
										</tbody>
									</table>

								</div>
								<div class="modal-footer row justify-content-center">
									<button type="button" class="btn btn-secondary col-3 mx-2"
										data-bs-dismiss="modal">いいえ</button>
									<button type="submit" class="btn btn-primary col-3 mx-2">はい</button>
								</div>
							</div>
						</div>
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
	<script type="text/javascript" src="js/validation_forResetPass.js"></script>
	<script type="text/javascript" src="js/resetPass.js"></script>
</body>
</html>