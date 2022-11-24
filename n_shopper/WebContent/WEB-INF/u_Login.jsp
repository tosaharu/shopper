<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- リクエストスコープからエラーメッセージを受け取る -->
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
<title>ログイン</title>
<jsp:include page="/common_css.jsp" />
</head>
<body>
<jsp:include page="/header.jsp" />
	<div class="container" style="max-width: 540px;">
		<div class="d-flex flex-column justify-content-center align-items-center">
				<br>
				<h2>ログイン</h2>
				<br>
				<!-- エラーメッセージが存在するときだけ表示する -->
				<%
				if (errorMessage != null) {
				%>
				<%=errorMessage%>
				<%
				}
				%>
				<form action="U_Login" method="post">
					<div class="form-outline mb-1">
						<label for="email" class="form-label">メールアドレス</label> <input
							type="email" class="form-control" id="email" name="mail">
					</div>
					<div class="form-outline mb-1">
						<label class="form-label" for="password1">パスワード</label> <input
							type="password" id="password1" class="form-control" name="pass" />
					</div>
					<br>
					<a href="/shopper//U_ResetPasswordAuth">パスワードをお忘れの方はこちら</a>
					<br>
					<button type="submit" class="btn btn-primary col-10 ">ログイン</button>
					<br>
					<br>
					<button type="button" class="btn btn-outline-primary col-10" onclick="location.href='/shopper/U_RegisterUser'">新規会員登録</button>
				</form>
			</div>
		</div>



	<jsp:include page="/common_js.jsp" />

</body>
</html>
