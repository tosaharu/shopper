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
<title>パスワード変更画面</title>
<jsp:include page="/common_css.jsp" />

</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container">
		<div
			class="d-flex flex-column justify-content-center align-items-center">
			<br> <br>
			<!-- エラーメッセージが存在するときだけ表示する -->
			<%
			if (errorMessage != null) {
			%>
			<%=errorMessage%>
			<%
			}
			%>
			<h2>メールアドレスと生年月日を入力してください</h2>
			<br>
			<form action="U_ChangePassword" method="post">
				<div class="form-outline mb-1">
					<label for="mail" class="form-label">メールアドレス</label> <input
						type="text" id="mail" name="mail" class="form-control">
				</div>
				<div class="form-outline mb-1">
					<label for="birthday" class="form-label">生年月日</label> <input
						type="text" id="birthday" name="birthday" class="form-control">
					<div id="birthdayHelp" class="form-text">（例）2000年1月1日→20000101</div>
				</div>
				<br>
				<button type="submit" class="btn btn-primary">送信</button>
			</form>
		</div>
	</div>
	<jsp:include page="/common_js.jsp" />
</body>
</html>