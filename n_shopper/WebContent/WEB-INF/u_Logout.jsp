<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>

<html lang="ja">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<title>ログアウト画面</title>
<jsp:include page="/common_css.jsp" />

</head>

<body>
	<jsp:include page="/header.jsp" />
	<div class="container">
		<div class="d-flex flex-column justify-content-center align-items-center">
				<br> <br>
				<h2>ログアウトしました</h2>
				<br> <br>
				<button type="button" class="btn btn-primary"
					onclick="location.href='/shopper/U_Main'">ホームへ</button>
		</div>
	</div>


	<jsp:include page="/common_js.jsp" />

</body>
</html>