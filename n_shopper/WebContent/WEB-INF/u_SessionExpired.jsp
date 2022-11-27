<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ja">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<title>セッションの有効期限切れ</title>
<jsp:include page="/common_css.jsp" />
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container" style="max-width: 540px;">
		<div
			class="d-flex flex-column justify-content-center align-items-center">
			<br>
			<h2>セッションの有効期限が切れました</h2>
			<br>
			<p>お手数ですが、最初の画面からやり直してください。</p>
			<br>
			<button type="button" class="btn btn-outline-primary col-10"
				onclick="location.href='/shopper/U_Main'">トップへ戻る</button>
		</div>
	</div>



	<jsp:include page="/common_js.jsp" />

</body>
</html>
